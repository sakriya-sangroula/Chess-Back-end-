import java.util.Arrays;

public class Board {
    String[][] board= new String[8][8];
     void initializeBoard(){
    for(int i=2 ;i<6;i++){
        for(int j=0;j<8;j++){
            board[1][j]="p";
            board[i][j]="Empty";
            board[6][j]="P";
        }
        board[0][0]="r";
        board[0][1]="n";
        board[0][2]="b";
        board[0][3]="q";
        board[0][4]="k";
        board[0][5]="b";
        board[0][6]="n";
        board[0][7]="r";
        board[7][0]="R";
        board[7][1]="N";
        board[7][2]="B";
        board[7][3]="Q";
        board[7][4]="K";
        board[7][5]="B";
        board[7][6]="N";
        board[7][7]="R";
    }
    System.out.println(Arrays.deepToString(board));
}}
