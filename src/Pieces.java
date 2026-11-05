import java.util.Arrays;

enum pieceColor {white, black}

abstract class Pieces {
    // Every move offset is stored as [letter change][number change].
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
    // [letter change][number change]
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
    // [letter change][number change]
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
    // Four diagonal directions, each storing a letter change and a number change.
    int[][] possibleMoves = new int[4][2];


    @Override
    boolean validMove(Moves moves, Board board) {
        if (Math.abs(moves.moveChangeIndex[0][0]) == Math.abs(moves.moveChangeIndex[0][1])) {
            // Moving in Positive X and Positive Y
            int currPositionX = moves.currentLocationLetterIndex;
            int currPositionY = moves.currentLocationNumberIndex;

            while (currPositionX < 7 && currPositionY < 7 && board.board[currPositionX + 1][currPositionY + 1] == null) {
                currPositionY++;
                currPositionX++;
            }
            if (color == pieceColor.white) {
                if (currPositionX == 7 || currPositionY == 7) {
                    possibleMoves[0][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = currPositionY - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX + 1][currPositionY + 1] != null && board.board[currPositionX + 1][currPositionY + 1].color == pieceColor.black) {
                    possibleMoves[0][0] = (currPositionX + 1) - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = (currPositionY + 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX + 1][currPositionY + 1] != null && board.board[currPositionX + 1][currPositionY + 1].color == pieceColor.white) {
                    possibleMoves[0][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            } else {
                if (currPositionX == 7 || currPositionY == 7) {
                    possibleMoves[0][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = currPositionY - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX + 1][currPositionY + 1] != null && board.board[currPositionX + 1][currPositionY + 1].color == pieceColor.white) {
                    possibleMoves[0][0] = (currPositionX + 1) - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = (currPositionY + 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX + 1][currPositionY + 1] != null && board.board[currPositionX + 1][currPositionY + 1].color == pieceColor.black) {
                    possibleMoves[0][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            }

            currPositionX = moves.currentLocationLetterIndex;
            currPositionY = moves.currentLocationNumberIndex;
            //moving in negative X positive Y
            while (currPositionY < 7 && currPositionX > 0 && board.board[currPositionX - 1][currPositionY + 1] == null) {
                currPositionY++;
                currPositionX--;
            }

            if (currPositionX == 0 || currPositionY == 7) {
                possibleMoves[1][0] = currPositionX - moves.currentLocationLetterIndex;
                possibleMoves[1][1] = currPositionY - moves.currentLocationNumberIndex;

            }
            if (color == pieceColor.white && currPositionX > 0 && currPositionY < 7) {
                if (board.board[currPositionX - 1][currPositionY + 1] != null && board.board[currPositionX - 1][currPositionY + 1].color == pieceColor.black) {
                    possibleMoves[1][0] = (currPositionX - 1) - moves.currentLocationLetterIndex;
                    possibleMoves[1][1] = (currPositionY + 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX - 1][currPositionY + 1] != null && board.board[currPositionX - 1][currPositionY + 1].color == pieceColor.white) {
                    possibleMoves[1][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[1][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            } else if (color == pieceColor.black && currPositionX > 0 && currPositionY < 7) {
                if (board.board[currPositionX - 1][currPositionY + 1] != null && board.board[currPositionX - 1][currPositionY + 1].color == pieceColor.white) {
                    possibleMoves[1][0] = (currPositionX - 1) - moves.currentLocationLetterIndex;
                    possibleMoves[1][1] = (currPositionY + 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX - 1][currPositionY + 1] != null && board.board[currPositionX - 1][currPositionY + 1].color == pieceColor.black) {
                    possibleMoves[1][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[1][1] = currPositionY - moves.currentLocationNumberIndex;
                }

            }
            // moving in Positive x and negative y
            currPositionX = moves.currentLocationLetterIndex;
            currPositionY = moves.currentLocationNumberIndex;
            while (currPositionX < 7 && currPositionY > 0 && board.board[currPositionX + 1][currPositionY - 1] == null) {
                currPositionY--;
                currPositionX++;
            }
            if (currPositionX == 7 || currPositionY == 0) {
                possibleMoves[2][0] = currPositionX - moves.currentLocationLetterIndex;
                possibleMoves[2][1] = currPositionY - moves.currentLocationNumberIndex;
            }
            if (color == pieceColor.white && currPositionX < 7 && currPositionY > 0) {
                if (board.board[currPositionX + 1][currPositionY - 1] != null && board.board[currPositionX + 1][currPositionY - 1].color == pieceColor.black) {
                    possibleMoves[2][0] = (currPositionX + 1) - moves.currentLocationLetterIndex;
                    possibleMoves[2][1] = (currPositionY - 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX + 1][currPositionY - 1] != null && board.board[currPositionX + 1][currPositionY - 1].color == pieceColor.white) {
                    possibleMoves[2][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[2][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            } else if (currPositionX < 7 && currPositionY > 0) {
                if (board.board[currPositionX + 1][currPositionY - 1] != null && board.board[currPositionX + 1][currPositionY - 1].color == pieceColor.white) {
                    possibleMoves[2][0] = (currPositionX + 1) - moves.currentLocationLetterIndex;
                    possibleMoves[2][1] = (currPositionY - 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX + 1][currPositionY - 1] != null && board.board[currPositionX + 1][currPositionY - 1].color == pieceColor.black) {
                    possibleMoves[2][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[2][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            }
            // moving in negative X and negative Y
            currPositionX = moves.currentLocationLetterIndex;
            currPositionY = moves.currentLocationNumberIndex;
            while (currPositionY > 0 && currPositionX > 0 && board.board[currPositionX - 1][currPositionY - 1] == null) {
                currPositionY--;
                currPositionX--;
            }
            if (currPositionX == 0 || currPositionY == 0) {
                possibleMoves[3][0] = currPositionX - moves.currentLocationLetterIndex;
                possibleMoves[3][1] = currPositionY - moves.currentLocationNumberIndex;
            }
            if (color == pieceColor.white && currPositionX > 0 && currPositionY > 0) {
                if (board.board[currPositionX - 1][currPositionY - 1] != null && board.board[currPositionX - 1][currPositionY - 1].color == pieceColor.black) {
                    possibleMoves[3][0] = (currPositionX - 1) - moves.currentLocationLetterIndex;
                    possibleMoves[3][1] = (currPositionY - 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX - 1][currPositionY - 1] != null && board.board[currPositionX - 1][currPositionY - 1].color == pieceColor.white) {
                    possibleMoves[3][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[3][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            } else if (currPositionX > 0 && currPositionY > 0) {
                if (board.board[currPositionX - 1][currPositionY - 1] != null && board.board[currPositionX - 1][currPositionY - 1].color == pieceColor.white) {
                    possibleMoves[3][0] = (currPositionX - 1) - moves.currentLocationLetterIndex;
                    possibleMoves[3][1] = (currPositionY - 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX - 1][currPositionY - 1] != null && board.board[currPositionX - 1][currPositionY - 1].color == pieceColor.black) {
                    possibleMoves[3][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[3][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            }
            // Check whether the requested move is within the clear distance in each direction.
            // Both moveChangeIndex and possibleMoves are [letter change][number change].
            // Moving +letter, +number
            if (moves.moveChangeIndex[0][0] > 0 && moves.moveChangeIndex[0][1] > 0) {
                if (possibleMoves[0][0] >= moves.moveChangeIndex[0][0] && possibleMoves[0][1] >= moves.moveChangeIndex[0][1]) {
                    return true;
                }
                System.out.println("Invalid Bishop Moves");
                return false;
            }
            // Moving -letter, +number
            else if (moves.moveChangeIndex[0][0] < 0 && moves.moveChangeIndex[0][1] > 0) {
                if (possibleMoves[1][0] <= moves.moveChangeIndex[0][0] && possibleMoves[1][1] >= moves.moveChangeIndex[0][1]) {
                    return true;
                }
                System.out.println("Invalid Bishop Move");
                return false;
            }
            // Moving +letter, -number
            else if (moves.moveChangeIndex[0][0] > 0 && moves.moveChangeIndex[0][1] < 0) {
                if (possibleMoves[2][0] >= moves.moveChangeIndex[0][0] && possibleMoves[2][1] <= moves.moveChangeIndex[0][1]) {
                    return true;
                }
                System.out.println("Invalid Bishop Move");
                return false;
            }
            // Moving -letter, -number
            else if (moves.moveChangeIndex[0][0] < 0 && moves.moveChangeIndex[0][1] < 0) {
                if (possibleMoves[3][0] <= moves.moveChangeIndex[0][0] && possibleMoves[3][1] <= moves.moveChangeIndex[0][1]) {
                    return true;
                }
                System.out.println("Invalid Bishop Move");
                return false;
            }
        }

        System.out.println("Invalid Bishop Move");
        return false;
    }

    bishop(pieceColor color) {
        super(color);
    }
}

class rook extends Pieces {
    int[][] possibleMoves = new int[4][2];

    //[letter][number]
    @Override
    boolean validMove(Moves moves, Board board) {
        if ((Math.abs(moves.moveChangeIndex[0][0]) > 0 & Math.abs(moves.moveChangeIndex[0][1]) == 0) || (Math.abs(moves.moveChangeIndex[0][0]) == 0 & Math.abs(moves.moveChangeIndex[0][1]) > 0)) {
            int currPositionX = moves.currentLocationLetterIndex;
            int currPositionY = moves.currentLocationNumberIndex;
            //Moving in Positive X
            while (currPositionX < 7 && board.board[currPositionX + 1][currPositionY] == null) {
                currPositionX++;
            }
            if (currPositionX == 7) {
                possibleMoves[0][0] = currPositionX - moves.currentLocationLetterIndex;
                possibleMoves[0][1] = 0;
            } else if (currPositionX < 7 & color == pieceColor.white) {
                if (board.board[currPositionX + 1][currPositionY] != null && board.board[currPositionX + 1][currPositionY].color == pieceColor.black) {
                    possibleMoves[0][0] = (currPositionX + 1) - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = 0;
                } else if (board.board[currPositionX + 1][currPositionY] != null && board.board[currPositionX + 1][currPositionY].color == pieceColor.white) {
                    possibleMoves[0][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = 0;
                }
            } else if (currPositionX < 7 && color == pieceColor.black) {
                if (board.board[currPositionX + 1][currPositionY] != null && board.board[currPositionX + 1][currPositionY].color == pieceColor.white) {
                    possibleMoves[0][0] = (currPositionX + 1) - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = 0;
                } else if (board.board[currPositionX + 1][currPositionY] != null && board.board[currPositionX + 1][currPositionY].color == pieceColor.black) {
                    possibleMoves[0][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[0][1] = 0;

                }
            }
             currPositionX = moves.currentLocationLetterIndex;
            //moving in Negative X
            while (currPositionX > 0 && board.board[currPositionX - 1][currPositionY] == null) {
                currPositionX--;
            }
            if (currPositionX == 0) {
                possibleMoves[1][0] = currPositionX - moves.currentLocationLetterIndex;
                possibleMoves[1][1] = 0;
            } else if (currPositionX > 0 & color == pieceColor.white) {
                if (board.board[currPositionX - 1][currPositionY] != null && board.board[currPositionX - 1][currPositionY].color == pieceColor.black) {
                    possibleMoves[1][0] = (currPositionX - 1) - moves.currentLocationLetterIndex;
                    possibleMoves[1][1] = 0;
                } else if (board.board[currPositionX - 1][currPositionY] != null && board.board[currPositionX - 1][currPositionY].color == pieceColor.white) {
                    possibleMoves[1][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[1][1] = 0;
                }
            } else if (currPositionX > 0 & color == pieceColor.black) {
                if (board.board[currPositionX - 1][currPositionY] != null && board.board[currPositionX - 1][currPositionY].color == pieceColor.white) {
                    possibleMoves[1][0] = (currPositionX - 1) - moves.currentLocationLetterIndex;
                    possibleMoves[1][1] = 0;
                } else if (board.board[currPositionX - 1][currPositionY] != null && board.board[currPositionX - 1][currPositionY].color == pieceColor.black) {
                    possibleMoves[1][0] = currPositionX - moves.currentLocationLetterIndex;
                    possibleMoves[1][1] = 0;

                }
            }
             currPositionX = moves.currentLocationLetterIndex;
            // moving in positiveY
            while (currPositionY < 7 && board.board[currPositionX][currPositionY + 1] == null) {
                currPositionY++;
            }
            if (currPositionY == 7) {
                possibleMoves[2][0] = 0;
                possibleMoves[2][1] = currPositionY - moves.currentLocationNumberIndex;
            } else if (currPositionY < 7 & color == pieceColor.white) {
                if (board.board[currPositionX][currPositionY + 1] != null && board.board[currPositionX][currPositionY + 1].color == pieceColor.black) {
                    possibleMoves[2][0] = 0;
                    possibleMoves[2][1] = (currPositionY + 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX][currPositionY + 1] != null && board.board[currPositionX][currPositionY + 1].color == pieceColor.white) {
                    possibleMoves[2][0] = 0;
                    possibleMoves[2][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            } else if (currPositionY < 7 & color == pieceColor.black) {
                if (board.board[currPositionX][currPositionY + 1] != null && board.board[currPositionX][currPositionY + 1].color == pieceColor.white) {
                    possibleMoves[2][0] = 0;
                    possibleMoves[2][1] = (currPositionY + 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX][currPositionY + 1] != null && board.board[currPositionX ][currPositionY+1].color == pieceColor.black) {
                    possibleMoves[2][0] = 0;
                    possibleMoves[2][1] = currPositionY - moves.currentLocationNumberIndex;
                }

            }//moving in Negative Y
            currPositionY = moves.currentLocationNumberIndex;
            while (currPositionY > 0 && board.board[currPositionX][currPositionY - 1] == null) {
                currPositionY--;
            }
            if (currPositionY == 0) {
                possibleMoves[3][0] = 0;
                possibleMoves[3][1] = currPositionY - moves.currentLocationNumberIndex;
            } else if (currPositionY > 0 & color == pieceColor.white) {
                if (board.board[currPositionX][currPositionY - 1] != null && board.board[currPositionX][currPositionY -1].color == pieceColor.black) {
                    possibleMoves[3][0] = 0;
                    possibleMoves[3][1] = (currPositionY -1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX][currPositionY - 1] != null && board.board[currPositionX][currPositionY - 1].color == pieceColor.white) {
                    possibleMoves[3][0] = 0;
                    possibleMoves[3][1] = currPositionY - moves.currentLocationNumberIndex;
                }
            } else if (currPositionY > 0 & color == pieceColor.black) {
                if (board.board[currPositionX][currPositionY - 1] != null && board.board[currPositionX][currPositionY - 1].color == pieceColor.white) {
                    possibleMoves[3][0] = 0;
                    possibleMoves[3][1] = (currPositionY - 1) - moves.currentLocationNumberIndex;
                } else if (board.board[currPositionX][currPositionY - 1] != null && board.board[currPositionX ][currPositionY-1].color == pieceColor.black) {
                    possibleMoves[3][0] = 0;
                    possibleMoves[3][1] = currPositionY - moves.currentLocationNumberIndex;

                }
            }
            // Verifying move
            // moving in +ve X
            if(moves.moveChangeIndex[0][0]>0){
                if(possibleMoves[0][0]>=moves.moveChangeIndex[0][1]){
                    return true;
                }
                else{
                    System.out.println("Invalid Rook Move");
                    return false;
                }
            }
            //moving in -veX
            else if(moves.moveChangeIndex[0][0]<0){
                if(possibleMoves[1][0]<=moves.moveChangeIndex[0][0]){
                    return true;
                }
                else{
                    System.out.println("Invalid Rook Move");
                    return false;
                }

            }//moving in +ve Y
            else if (moves.moveChangeIndex[0][1]>0){
                if (possibleMoves[2][1]>=moves.moveChangeIndex[0][1]){
                    return true;
                }
                else{
                    System.out.println("Invalid Rook Move");
                    return false;
                }

            }
            // moving in -ve Y
            else if (moves.moveChangeIndex[0][1]<0) {
                if (possibleMoves[3][1]<=moves.moveChangeIndex[0][1]){
                    return true;
                }
                else{
                    System.out.println("Invalid Rook Move");
                }

            }
        }


        System.out.println("Invalid Rook Move");
        return false;
    }

    rook(pieceColor color) {
        super(color);
    }
}

class queen extends Pieces {
    bishop asBishop;
    rook asRook;

    queen(pieceColor color) {
        super(color);
        asBishop = new bishop(color);
        asRook = new rook(color);
    }

    @Override
    boolean validMove(Moves moves, Board board) {
        return asBishop.validMove(moves, board) || asRook.validMove(moves, board);
    }
}

class pawn extends Pieces {
    // [letter change][number change]
    int[][] possibleWhiteMoves = {
            {0, 1},
            {0, 2},
            {1, 1},
            {-1, 1},
    };
    int[][] possibleBlackMoves = {
            {0, -1},
            {0, -2},
            {1, -1},
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
                if (Arrays.equals(moves.moveChangeIndex[0], possibleWhiteMoves[0])) {
                    if (board.board[moves.moveLocationLetterIndex][moves.moveLocationNumberIndex] == null) {
                        return true;
                    } else {
                        System.out.println("Invalid Pawn Move");
                        return false;
                    }
                } else if (Arrays.equals(moves.moveChangeIndex[0], possibleWhiteMoves[1])) {
                    if (moves.currentLocationNumberIndex == 1) {
                        if (board.board[moves.moveLocationLetterIndex][moves.moveLocationNumberIndex] == null && board.board[moves.moveLocationLetterIndex][2] == null) {
                            return true;
                        } else {
                            System.out.println("Invalid Pawn Move");
                            return false;

                        }
                    } else {
                        return false;
                    }
                } else if (Arrays.equals(moves.moveChangeIndex[0], possibleWhiteMoves[2]) || Arrays.equals(moves.moveChangeIndex[0], possibleWhiteMoves[3])) {
                    if (board.board[moves.moveLocationLetterIndex][moves.moveLocationNumberIndex] != null) {
                        return true;
                    } else {
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
                if (Arrays.equals(moves.moveChangeIndex[0], possibleBlackMoves[0])) {
                    return board.board[moves.moveLocationLetterIndex][moves.moveLocationNumberIndex] == null;

                } else if (Arrays.equals(moves.moveChangeIndex[0], possibleBlackMoves[1])) {
                    if (moves.currentLocationNumberIndex == 6) {
                        if (board.board[moves.moveLocationLetterIndex][moves.moveLocationNumberIndex] == null && board.board[moves.moveLocationLetterIndex][5] == null) {
                            return true;
                        } else {
                            System.out.println("Invalid Pawn Move");
                        }
                    } else {
                        return false;
                    }
                } else if (Arrays.equals(moves.moveChangeIndex[0], possibleBlackMoves[2]) || Arrays.equals(moves.moveChangeIndex[0], possibleBlackMoves[3])) {
                    return board.board[moves.moveLocationLetterIndex][moves.moveLocationNumberIndex] != null;
                }
            } else {
                return movePossible;
            }
        }
        return false;
    }

    pawn(pieceColor color) {
        super(color);
    }
}


//