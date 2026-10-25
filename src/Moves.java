import java.util.Objects;

public class Moves {

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



}
