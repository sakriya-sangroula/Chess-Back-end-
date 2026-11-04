import java.util.Arrays;

public class Board {
    Pieces[][] board = new Pieces[8][8];

    void initializeBoard() {
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[1][j] = new pawn(pieceColor.white);
                board[i][j] = null;
                board[6][j] = new pawn(pieceColor.black);
            }
            board[0][0] = new rook(pieceColor.white);
            board[0][1] = new knight(pieceColor.white);
            board[0][2] = new bishop(pieceColor.white);
            board[0][3] = new queen(pieceColor.white);
            board[0][4] = new king(pieceColor.white);
            board[0][5] = new bishop(pieceColor.white);
            board[0][6] = new knight(pieceColor.white);
            board[0][7] = new rook(pieceColor.white);
            board[7][0] = new rook(pieceColor.black);
            board[7][1] = new knight(pieceColor.black);
            board[7][2] = new bishop(pieceColor.black);
            board[7][3] = new queen(pieceColor.black);
            board[7][4] = new king(pieceColor.black);
            board[7][5] = new bishop(pieceColor.black);
            board[7][6] = new knight(pieceColor.black);
            board[7][7] = new knight(pieceColor.black);
        }
        System.out.println(Arrays.deepToString(board));
    }
    // Connection layer: turns the internal board into data that JavaScript can read.
    // It does not change any chess rules or the board itself.
    String toJson(playerTurn turn) {
        StringBuilder json = new StringBuilder("{\"turn\":\"").append(turn).append("\",\"board\":[");
        for (int row = 7; row >= 0; row--) {
            if (row < 7) json.append(',');
            json.append('[');
            for (int col = 0; col < 8; col++) {
                if (col > 0) json.append(',');
                Pieces piece = board[row][col];
                if (piece == null) json.append("null");
                else json.append("{\"type\":\"").append(piece.type()).append("\",\"color\":\"").append(piece.color).append("\"}");
            }
            json.append(']');
        }
        return json.append("]}").toString();
    }
    void changeBoardAfterMove(int currL,int currN,int changeL,int changeN){
        board[changeN][changeL]=board[currN][currL];
        board[currN][currL]=null;

    }
}
