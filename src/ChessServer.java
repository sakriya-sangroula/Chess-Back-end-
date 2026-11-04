import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Connection layer between your Java chess code and the browser frontend.
 *
 * GET  /api/game returns the board as JSON.
 * POST /api/move sends a move using form values named "from" and "to".
 * All other GET requests serve files from src/frontend.
 */
public class ChessServer {
    private final HttpServer server;
    // This is the one game shared by the API and the browser page.
    private final ChessGame game = createGame();
    private final Path frontend = Path.of("src", "frontend").toAbsolutePath().normalize();
    public ChessServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/game", e -> sendJson(e, 200, game.boardAsJson()));
        server.createContext("/api/valid-moves", this::validMoves);
        server.createContext("/api/move", this::move);
        server.createContext("/", this::staticFile);
    }
    // Uses your existing setup method rather than changing how the board is initialized.
    private static ChessGame createGame() {
        ChessGame game = new ChessGame();
        game.chessGame();
        return game;
    }
    public void start() { server.start(); }
    // Receives a browser move, sends it through your existing game methods, then returns the updated board.
    private void move(HttpExchange e) throws IOException {
        if (!"POST".equals(e.getRequestMethod())) { sendJson(e, 405, "{\"error\":\"POST required\"}"); return; }
        Map<String, String> values = parseForm(new String(e.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
        String error = game.makeMove(values.getOrDefault("from", ""), values.getOrDefault("to", ""));
        String response = "{\"game\":" + game.boardAsJson() + (error == null ? "" : ",\"error\":\"" + error + "\"") + "}";
        sendJson(e, error == null ? 200 : 400, response);
    }
    // Returns the available destinations for one selected square without changing the game.
    private void validMoves(HttpExchange e) throws IOException {
        if (!"GET".equals(e.getRequestMethod())) { sendJson(e, 405, "{\"error\":\"GET required\"}"); return; }
        String query = e.getRequestURI().getRawQuery();
        String from = query == null ? "" : parseForm(query).getOrDefault("from", "");
        sendJson(e, 200, game.validMovesAsJson(from));
    }
    // Serves index.html, app.js, and styles.css to the browser.
    private void staticFile(HttpExchange e) throws IOException {
        if (!"GET".equals(e.getRequestMethod())) { e.sendResponseHeaders(405, -1); return; }
        String requested = e.getRequestURI().getPath();
        if (requested.equals("/")) requested = "/index.html";
        Path file = frontend.resolve(requested.substring(1)).normalize();
        if (!file.startsWith(frontend) || !Files.isRegularFile(file)) { e.sendResponseHeaders(404, -1); return; }
        String type = file.toString().endsWith(".css") ? "text/css; charset=utf-8" : file.toString().endsWith(".js") ? "application/javascript; charset=utf-8" : "text/html; charset=utf-8";
        byte[] body = Files.readAllBytes(file);
        e.getResponseHeaders().set("Content-Type", type); e.sendResponseHeaders(200, body.length); e.getResponseBody().write(body); e.close();
    }
    // Converts a request such as "from=e2&to=e4" into values the game can use.
    private Map<String, String> parseForm(String form) {
        java.util.HashMap<String, String> values = new java.util.HashMap<>();
        for (String item : form.split("&")) { String[] pair = item.split("=", 2); if (pair.length == 2) values.put(URLDecoder.decode(pair[0], StandardCharsets.UTF_8), URLDecoder.decode(pair[1], StandardCharsets.UTF_8)); }
        return values;
    }
    // Sends JavaScript-readable data back to the frontend.
    private void sendJson(HttpExchange e, int status, String json) throws IOException {
        byte[] body = json.getBytes(StandardCharsets.UTF_8); e.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8"); e.sendResponseHeaders(status, body.length); e.getResponseBody().write(body); e.close();
    }
}
