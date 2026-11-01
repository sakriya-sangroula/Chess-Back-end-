import java.util.Objects;

public class ChessGame {

    int totalMove = 0;
    Board board = new Board();

    public void chessGame() {
        board.initializeBoard();

    }

    String turn() {
        if (totalMove % 2 == 0) {
            return "W";
        } else {
            return "B";
        }
    }

    Moves move = new Moves();

    public boolean BasicValidation() {
        String turn = this.turn();
        Pieces piece = this.board.board[move.currentLocationNumberIndex][move.currentLocationLetterIndex];
        Pieces movePieceTo = this.board.board[move.moveLocationNumberIndex][move.moveLocationLetterIndex];
        if (piece==null) {
            System.out.println("Invalid");
            return false;
        }
        if (Objects.equals(turn, "W") && piece.color==pieceColor.black) {
            System.out.println("Invalid");
            return false;
        }
        if (Objects.equals(turn, "B") && piece.color==pieceColor.white) {
            System.out.println("Invalid");
            return false;
        }
        if (piece.color==pieceColor.white && Objects.equals(turn, "W")) {
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
        if (piece.color==pieceColor.black && Objects.equals(turn, "B")) {
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
}
