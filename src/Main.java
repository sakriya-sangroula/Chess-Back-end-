public class Main {
    public static void main(String[] args) throws Exception {
        ChessServer server = new ChessServer(8080);
        server.start();
        System.out.println("Chess is running at http://localhost:8080");
    }
}

