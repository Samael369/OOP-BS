import java.util.Scanner;

public class Board {
    Scanner scanner = new Scanner(System.in);
    static int boardSize;
    final static char SHIP = '#';
    final static char WATER = '~';
    final static char MISS = 'O';
    final static char HIT = 'X';
    private char[][] board;

    public Board() {
        this.board = new char[boardSize][boardSize];
        initializeGrid();
    }

    public void initializeGrid() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = '~';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void placeShips() {
        Ship airCraftCarrier = new Ship(5);
        Ship battleShip = new Ship(4);
        Ship Submarine = new Ship(3);
        Ship patrolBoat = new Ship(2);

        airCraftCarrier.placeShipRandomly(this, airCraftCarrier);
        battleShip.placeShipRandomly(this, battleShip);
        Submarine.placeShipRandomly(this, Submarine);
        patrolBoat.placeShipRandomly(this, patrolBoat);
    }

    public boolean placeShip(Ship ship, int row, int col, boolean horizontal) {
        int size = ship.getSize();
        if (row + size > boardSize || col + size > boardSize) return false;
        if (horizontal) {
            for (int i = col; i < col + size; i++) {
                if (board[row][i] != '~') return false;
            }
            for (int i = col; i < col + size; i++) board[row][i] = '#';
        } else {
            for (int i = row; i < row + size; i++) {
                if (board[i][col] != '~') return false;
            }
            for (int i = row; i < row + size; i++) board[i][col] = '#';
        }
        return true;
    }

    public void updateBoard(Board opponentBoard, String coordinate) {
        int row = Coordinate.parseIntRow(coordinate);
        int col = Coordinate.parseIntCol(coordinate);
        char[][] opponentBoardCopy = opponentBoard.getBoard();
        switch (opponentBoardCopy[row][col]) {
            case SHIP:
                this.board[row][col] = HIT;
                break;
            case WATER:
                this.board[row][col] = MISS;
                break;
        }
    }

    public void printBoard() {
        System.out.println("  ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        for (int i = 0; i < boardSize; i++) {
            System.out.print((char) ('0' + i) + " ");
            for (int j = 0; j < boardSize; j++) {
                System.out.print((this.board[i][j] + " "));
            }
            System.out.println(" " + (char) ('0' + i));
        }
        for (int i = 0; i < boardSize; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
    }
}