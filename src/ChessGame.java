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
        Pieces piece = this.board.board[move.currentLocationNumberIndex][move.currentLocationLetterIndex];
        Pieces movePieceTo = this.board.board[move.moveLocationNumberIndex][move.moveLocationLetterIndex];
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
}
