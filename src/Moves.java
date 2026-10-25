import java.util.Objects;

public class Moves {
    ChessGame game;

    public Moves(ChessGame game) {
        this.game = game;
    }

    int currentLocationLetterIndex;
    int currentLocationNumberIndex;
    int moveLocationLetterIndex;
    int moveLocationNumberIndex;

    public void moveRequest(String currentLocation, String moveLocation) {
        int currentLocationLetterAscii = currentLocation.charAt(0);
        int currentLocationNumberAscii = currentLocation.charAt(1);
        int moveLocationLetterAscii = moveLocation.charAt(0);
        int moveLocationNumberAscii = moveLocation.charAt(1);
        currentLocationLetterIndex = currentLocationLetterAscii - 97;
        currentLocationNumberIndex = currentLocationNumberAscii - 49;
        moveLocationLetterIndex = moveLocationLetterAscii - 97;
        moveLocationNumberIndex = moveLocationNumberAscii - 49;
    }

    public boolean BasicValidation() {
        String turn = game.turn();
        String piece = game.board.board[currentLocationNumberIndex][currentLocationLetterIndex];
        String movePieceTo = game.board.board[moveLocationNumberIndex][moveLocationLetterIndex];
        if (piece.length() > 1) {
            return false;
        }
        if (Objects.equals(turn, "W") && Character.isLowerCase(piece.charAt(0))) {
            return false;
        }
        if (Objects.equals(turn, "B") && Character.isUpperCase(piece.charAt(0))) {
            return false;
        }
        boolean isWhite = Character.isUpperCase(piece.charAt(0));
        if (isWhite && Objects.equals(turn, "W")) {
            if (Objects.equals(movePieceTo, "Empty")) {
                return true;
            } else {
                return Character.isLowerCase((movePieceTo.charAt(0)));
            }

        }
        if (!isWhite && Objects.equals(turn, "B")) {
            if (Objects.equals(movePieceTo, "Empty")) {
                return true;
            } else {
                return Character.isUpperCase(movePieceTo.charAt(0));
            }

        }
        return false;
    }


}
