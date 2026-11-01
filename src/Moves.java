
public class Moves {

    int currentLocationLetterIndex;
    int currentLocationNumberIndex;
    int moveLocationLetterIndex;
    int moveLocationNumberIndex;
    int[][] moveChangeIndex =new int[0][0];

    public boolean moveRequest(String currentLocation, String moveLocation) {
        if(currentLocation.length()!=2||moveLocation.length()!=2){
            System.out.println("invalid move request");
            return false;
        }
        int currentLocationLetterAscii = currentLocation.charAt(0);
        int currentLocationNumberAscii = currentLocation.charAt(1);
        int moveLocationLetterAscii = moveLocation.charAt(0);
        int moveLocationNumberAscii = moveLocation.charAt(1);
        currentLocationLetterIndex = currentLocationLetterAscii - 97;
        currentLocationNumberIndex = currentLocationNumberAscii - 49;
        moveLocationLetterIndex = moveLocationLetterAscii - 97;
        moveLocationNumberIndex = moveLocationNumberAscii - 49;
        moveChangeIndex[0][0]=currentLocationLetterIndex-moveLocationLetterIndex;
        moveChangeIndex[0][1]=currentLocationNumberIndex-moveLocationNumberIndex;
        if(currentLocationNumberIndex>7||currentLocationLetterIndex>7||moveLocationNumberIndex>7||moveLocationLetterIndex>7){
            System.out.println("Invalid move request");
            return false;
        }
        if(currentLocationNumberIndex<0||currentLocationLetterIndex<0||moveLocationNumberIndex<0||moveLocationLetterIndex<0){
            System.out.println("Invalid move request");
            return false;}
        return true;
    }


}
