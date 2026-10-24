public class CheesGame {

    int totalMove = 0;
    Board board = new Board();

    public void chessGame() {
        board.initializeBoard();
    }

    String turn() {
        if (totalMove % 2 == 0) {
            return "W";
        } else {
            return "B";
        }
    }
    void moveRequest(String currentLocation,String moveLocation) {
        int  currentLocationLetterAscii=currentLocation.charAt(0);
        int currentLocationNumberAscii=currentLocation.charAt(1);
        int moveLocationLetterAscii=moveLocation.charAt(0);
        int moveLocationNumberAscii=moveLocation.charAt(1);
        int currentLocationLetterIndex=currentLocationLetterAscii-97;
        int currentLocationNumberIndex=currentLocationNumberAscii-49;
        int moveLocationLetterIndex=moveLocationLetterAscii-97;
        int moveLocationNumberIndex=moveLocationNumberAscii-49;
    }}
