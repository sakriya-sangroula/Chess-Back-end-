//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    ChessGame gameA = new ChessGame();
    gameA.chessGame();
    gameA.move.moveRequest("e2","e5");
    if(gameA.BasicValidation()){
        gameA.nextMove();
    }
    gameA.move.moveRequest("e7","e6");
    if(gameA.BasicValidation()){
        gameA.nextMove();
    }

}

