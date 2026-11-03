//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    ChessGame gameA = new ChessGame();
    gameA.chessGame();
    moveFunction(gameA);
}

void moveFunction(ChessGame game) {
    if (game.move.moveRequest("b1", "c3")) {
        int pieceNumberIndex= game.move.currentLocationNumberIndex;
        int pieceLetterIndex=game.move.currentLocationLetterIndex;
        if (game.BasicValidation()) {
            if(game.board.board[pieceNumberIndex][pieceLetterIndex].validMove(game.move)){
            game.nextMove();
        }
    }
}}

