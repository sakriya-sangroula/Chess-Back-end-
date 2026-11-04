import java.util.Arrays;

enum pieceColor {white, black}

abstract class Pieces {
    int[][] possibleMoves;
    pieceColor color;

    abstract boolean validMove(Moves moves, Board board);

    Pieces(pieceColor color) {

        this.color = color;
    }

    // Connection layer: supplies the piece name (for example, "pawn") to the frontend.
    String type() {
        return getClass().getSimpleName();
    }
};

class king extends Pieces {
    int[][] possibleMoves = {
            {1, 0},
            {0, 1},
            {1, 1},
            {-1, -1},
            {1, -1},
            {0, -1},
            {-1, 1},
            {-1, 0}
    };


    @Override
    boolean validMove(Moves moves, Board board) {
        for (int[] possibleMove : possibleMoves) {
            if (possibleMove[0] == moves.moveChangeIndex[0][0] && possibleMove[1] == moves.moveChangeIndex[0][1]) {
                return true;
            }
        }
        System.out.println("Invalid King Move");
        return false;
    }

    king(pieceColor color) {
        super(color);
    }
}

class knight extends Pieces {
    int[][] possibleMoves = {
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2}
    };

    @Override
    boolean validMove(Moves moves, Board board) {
        for (int[] possibleMove : possibleMoves) {
            if (possibleMove[0] == moves.moveChangeIndex[0][0] && possibleMove[1] == moves.moveChangeIndex[0][1]) {
                return true;
            }
        }
        System.out.println("Invalid Knight Move");
        return false;
    }

    knight(pieceColor color) {
        super(color);
    }
}

class bishop extends Pieces {
    int[][] possibleMoves = {
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1}
    };

    @Override
    boolean validMove(Moves moves, Board board) {
        System.out.println("Invalid Bishop Move");
        return false;
    }

    bishop(pieceColor color) {
        super(color);
    }
}

class rook extends Pieces {
    int[][] possibleMoves = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    @Override
    boolean validMove(Moves moves, Board board) {
        System.out.println("Invalid Rook Move");
        return false;
    }

    rook(pieceColor color) {
        super(color);
    }
}

class queen extends Pieces {
    int[][] possibleMoves = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1}

    };

    @Override
    boolean validMove(Moves moves, Board board) {
        System.out.println("Invalid Queen Move");
        return false;
    }

    queen(pieceColor color) {
        super(color);
    }
}

class pawn extends Pieces {
    int[][] possibleWhiteMoves = {
            {1, 0},
            {2, 0},
            {1, 1},
            {1, -1},
    };
    int[][] possibleBlackMoves = {
            {-1, 0},
            {-2, 0},
            {-1, 1},
            {-1, -1}
    };

    @Override
    boolean validMove(Moves moves, Board board) {
        boolean movePossible = false;
        if (color == pieceColor.white) {

            for (int[] possibleWhiteMove : possibleWhiteMoves) {
                if (possibleWhiteMove[0] == moves.moveChangeIndex[0][0] && possibleWhiteMove[1] == moves.moveChangeIndex[0][1]) {
                    movePossible = true;
                    break;
                }
            }
            if (movePossible) {
                if (Arrays.equals(moves.moveChangeIndex[0], possibleWhiteMoves[0]) ) {
                     if(board.board[moves.moveLocationNumberIndex][moves.moveLocationLetterIndex] == null){
                         return true;
                     }
                     else {
                         System.out.println("Invalid Pawn Move");
                         return false;
                     }
                } else if (Arrays.equals(moves.moveChangeIndex[0], possibleWhiteMoves[1])){
                    if(moves.currentLocationNumberIndex==1){
                        if( board.board[moves.moveLocationNumberIndex][moves.moveLocationLetterIndex] == null){
                            return true;
                        }
                        else {
                            System.out.println("Invalid Pawn Move");
                            return false;

                    }}
                    else{
                        return false;
                    }
                }
                else if (Arrays.equals(moves.moveChangeIndex[0], possibleWhiteMoves[2]) || Arrays.equals(moves.moveChangeIndex[0], possibleWhiteMoves[3])) {
                    if( board.board[moves.moveLocationNumberIndex][moves.moveLocationLetterIndex] != null){
                        return true;
                    }
                    else {
                        System.out.println("Invalid Pawn Move");
                        return false;
                    }
                }

            } else {
                return movePossible;
            }

        } else if (color == pieceColor.black) {
            for (int[] possibleBlackMove : possibleBlackMoves) {
                if (possibleBlackMove[0] == moves.moveChangeIndex[0][0] && possibleBlackMove[1] == moves.moveChangeIndex[0][1]) {
                    movePossible = true;
                    break;
                }
            }
            if (movePossible) {
                if (Arrays.equals(moves.moveChangeIndex[0], possibleBlackMoves[0]) ) {
                    return board.board[moves.moveLocationNumberIndex][moves.moveLocationLetterIndex] == null;

                } else if (Arrays.equals(moves.moveChangeIndex[0], possibleBlackMoves[1])){
                    if(moves.currentLocationNumberIndex==6){
                        return board.board[moves.moveLocationNumberIndex][moves.moveLocationLetterIndex] == null;
                    }
                    else{
                        return false;
                    }
                }
                else if (Arrays.equals(moves.moveChangeIndex[0], possibleBlackMoves[2]) || Arrays.equals(moves.moveChangeIndex[0], possibleBlackMoves[3])) {
                    return board.board[moves.moveLocationNumberIndex][moves.moveLocationLetterIndex] != null;
                }
            } else {
                return movePossible;
            }
        }
        System.out.println("Invalid Pawn Move");
        return false;
    }

    pawn(pieceColor color) {
        super(color);
    }
}


