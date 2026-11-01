
enum pieceColor {white,black}
abstract  class Pieces {
 int [][]possibleMoves;
 pieceColor color;

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

  king( pieceColor color) {
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

   knight( pieceColor color) {
    super( color);
   }
  }
 class bishop extends Pieces {
  int [][]possibleMoves={
          {1,1},
          {-1,-1},
          {1,-1},
          {-1,1}
  };

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
    pawn(pieceColor color) {
        super(color);
    }
}


