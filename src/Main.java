//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    ChessGame gameA = new ChessGame();
    gameA.chessGame();
    moveFunction(gameA);
}

void moveFunction(ChessGame game) {
    if (game.move.moveRequest("e2", "e5")) {
        if (game.BasicValidation()) {
            game.nextMove();
        }
    }

}

