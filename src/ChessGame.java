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


}
