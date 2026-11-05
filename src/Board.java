import java.util.Arrays;

public class Board {
    // Board coordinates are always [letter][number]: board[file][rank].
    // For example, board[0][0] is a1 and board[4][1] is e2.
    Pieces[][] board = new Pieces[8][8];

    void initializeBoard() {
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[j][1] = new pawn(pieceColor.white);
                board[j][i] = null;
                board[j][6] = new pawn(pieceColor.black);
            }
            board[0][0] = new rook(pieceColor.white);
            board[1][0] = new knight(pieceColor.white);
            board[2][0] = new bishop(pieceColor.white);
            board[3][0] = new queen(pieceColor.white);
            board[4][0] = new king(pieceColor.white);
            board[5][0] = new bishop(pieceColor.white);
            board[6][0] = new knight(pieceColor.white);
            board[7][0] = new rook(pieceColor.white);
            board[0][7] = new rook(pieceColor.black);
            board[1][7] = new knight(pieceColor.black);
            board[2][7] = new bishop(pieceColor.black);
            board[3][7] = new queen(pieceColor.black);
            board[4][7] = new king(pieceColor.black);
            board[5][7] = new bishop(pieceColor.black);
            board[6][7] = new knight(pieceColor.black);
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
                Pieces piece = board[col][row];
                if (piece == null) json.append("null");
                else json.append("{\"type\":\"").append(piece.type()).append("\",\"color\":\"").append(piece.color).append("\"}");
            }
            json.append(']');
        }
        return json.append("]}").toString();
    }
    void changeBoardAfterMove(int currL,int currN,int changeL,int changeN){
        board[changeL][changeN]=board[currL][currN];
        board[currL][currN]=null;

    }
}
