import java.util.Objects;
enum playerTurn{w,b}
public class ChessGame {

    int totalMove = 0;
    Board board = new Board();

    public void chessGame() {
        board.initializeBoard();

    }

    playerTurn turn() {
        if (totalMove % 2 == 0) {
            return playerTurn.w;
        } else {
            return playerTurn.b;
        }
    }
    Moves move = new Moves();

    public boolean BasicValidation() {
        playerTurn turn = this.turn();
        Pieces piece = this.board.board[move.currentLocationLetterIndex][move.currentLocationNumberIndex];
        Pieces movePieceTo = this.board.board[move.moveLocationLetterIndex][move.moveLocationNumberIndex];
        if (piece==null) {
            System.out.println("Invalid");
            return false;
        }
        if (turn==playerTurn.w && piece.color==pieceColor.black) {
            System.out.println("Invalid");
            return false;
        }
        if (turn==playerTurn.b && piece.color==pieceColor.white) {
            System.out.println("Invalid");
            return false;
        }
        if (piece.color==pieceColor.white && turn==playerTurn.w) {
            if (movePieceTo==null) {
                return true;
            } else {
                if (movePieceTo.color==pieceColor.black) {
                    return true;
                } else {
                    System.out.println("Invalid");
                    return false;
                }
            }
        }
        if (piece.color==pieceColor.black && turn==playerTurn.b) {
            if (movePieceTo==null) {
                return true;
            } else {

                if (movePieceTo.color==pieceColor.white) {
                    return true;
                } else {
                    System.out.println("Invalid");
                    return false;
                }
            }

        }
        System.out.println("Invalid");
        return false;
    }

    void nextMove() {
        this.board.changeBoardAfterMove(move.currentLocationLetterIndex, move.currentLocationNumberIndex, move.moveLocationLetterIndex, move.moveLocationNumberIndex);
        totalMove++;
    }

    /**
     * Connection layer: lets the frontend ask the existing chess game to make a move.
     * Keep your chess rules in BasicValidation(), validMove(), and nextMove().
     */
    public synchronized String makeMove(String from, String to) {
        if (!move.moveRequest(from, to)) return "Use squares from a1 to h8.";
        if (!BasicValidation()) return "That piece cannot be moved on this turn.";
        int pieceLetterIndex = move.currentLocationLetterIndex;
        int pieceNumberIndex = move.currentLocationNumberIndex;
        if (!board.board[pieceLetterIndex][pieceNumberIndex].validMove(move, board)) {
            return "That piece cannot move to that square.";
        }
        nextMove();
        return null;
    }

    // Connection layer: returns the current game state for GET /api/game.
    public synchronized String boardAsJson() { return board.toJson(turn()); }

    /**
     * Connection layer: exposes the destinations allowed by the current piece rules.
     * It checks moves without changing the board or the current player's turn.
     */
    public synchronized String validMovesAsJson(String from) {
        Moves savedMove = move;
        StringBuilder movesJson = new StringBuilder("{\"moves\":[");
        boolean first = true;
        try {
            for (char file = 'a'; file <= 'h'; file++) {
                for (char rank = '1'; rank <= '8'; rank++) {
                    Moves candidate = new Moves();
                    if (!candidate.moveRequest(from, "" + file + rank)) continue;
                    Pieces piece = board.board[candidate.currentLocationLetterIndex][candidate.currentLocationNumberIndex];
                    Pieces destination = board.board[candidate.moveLocationLetterIndex][candidate.moveLocationNumberIndex];
                    if (piece == null || piece.color != (turn() == playerTurn.w ? pieceColor.white : pieceColor.black)) continue;
                    if (destination != null && destination.color == piece.color) continue;
                    move = candidate;
                    if (piece.validMove(candidate, board)) {
                        if (!first) movesJson.append(',');
                        movesJson.append('"').append(file).append(rank).append('"');
                        first = false;
                    }
                }
            }
        } finally {
            move = savedMove;
        }
        return movesJson.append("]}").toString();
    }
}
