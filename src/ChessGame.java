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
        String piece = this.board.board[move.currentLocationNumberIndex][move.currentLocationLetterIndex];
        String movePieceTo = this.board.board[move.moveLocationNumberIndex][move.moveLocationLetterIndex];
        if (piece.length() > 1) {
            return false;
        }
        if (Objects.equals(turn, "W") && Character.isLowerCase(piece.charAt(0))) {
            return false;
        }
        if (Objects.equals(turn, "B") && Character.isUpperCase(piece.charAt(0))) {
            return false;
        }
        boolean isWhite = Character.isUpperCase(piece.charAt(0));
        if (isWhite && Objects.equals(turn, "W")) {
            if (Objects.equals(movePieceTo, "Empty")) {
                return true;
            } else {
                return Character.isLowerCase((movePieceTo.charAt(0)));
            }
        }
        if (!isWhite && Objects.equals(turn, "B")) {
            if (Objects.equals(movePieceTo, "Empty")) {
                return true;
            } else {
                return Character.isUpperCase(movePieceTo.charAt(0));
            }

        }
        return false;
    }
void nextMove(){
        this.board.changeBoardAfterMove(move.currentLocationLetterIndex,move.currentLocationNumberIndex,move.moveLocationLetterIndex,move.moveLocationNumberIndex);
        totalMove++;
}
}
