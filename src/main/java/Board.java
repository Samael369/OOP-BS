import java.util.Scanner;

public class Board {
    Scanner scanner = new Scanner(System.in);
    static int boardSize;
    char[][] board = new char[boardSize][boardSize];

    public void initializeGrid() {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                this.board[i][j] = '~';
            }
        }
    }

    public Board() {
        this.board = new char[boardSize][boardSize];
        initializeGrid();
    }

    public char[][] getBoard() {
        return board;
    }

}
