enum pieceColor {white,black}
abstract  class Pieces {
 int [][]possibleMoves;
 pieceColor color;
abstract boolean validMove(Moves moves);
 Pieces(pieceColor color){

  this.color=color;
 }
};
 class king extends Pieces {
int [][]possibleMoves={
        {1,0},
        {0,1},
        {1,1},
        {-1,-1},
        {1,-1},
        {0,-1},
        {-1,1},
        {-1,0}
};


     @Override
     boolean validMove(Moves moves) {
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
  }}
  class knight extends Pieces {
 int [][] possibleMoves={
         { 1,  2},
         { 2,  1},
         { 2, -1},
         { 1, -2},
         {-1, -2},
         {-2, -1},
         {-2,  1},
         {-1,  2}
 };

      @Override
      boolean validMove(Moves moves) {
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
  int [][]possibleMoves={
          {1,1},
          {-1,-1},
          {1,-1},
          {-1,1}
  };

     @Override
     boolean validMove(Moves moves) {
         System.out.println("Invalid Bishop Move");
         return false;
     }

     bishop(pieceColor color) {
   super(color);
  }}
class rook extends Pieces {
  int[][]possibleMoves={
          {1,0},
          {0,1},
          {-1,0},
          {0,-1}
  };

    @Override
    boolean validMove(Moves moves) {
        System.out.println("Invalid Rook Move");
        return false;
    }

    rook(pieceColor color) {
  super(color);
 }
}
class queen extends Pieces {
  int [][]possibleMoves={
          {1,0},
          {0,1},
          {-1,0},
          {0,-1},
          {1,1},
          {-1,-1},
          {1,-1},
          {-1,1}

  };

    @Override
    boolean validMove(Moves moves) {
        System.out.println("Invalid Queen Move");
        return false;
    }

    queen(pieceColor color) {
  super(color);
 }
}
class pawn extends Pieces {
int [][]possibleMoves={
        {1,0},
        {-1,0},
        {2,0},
        {-2,0},
        {1,1},
        {1,-1},
        {-1,1},
        {-1,-1}
};

    @Override
    boolean validMove(Moves moves) {
        System.out.println("Invalid Pawn Move");
        return false;
    }

    pawn(pieceColor color) {
        super(color);
    }
}


