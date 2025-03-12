import java.util.Scanner;

public class Board {
    Scanner scanner = new Scanner(System.in);
    static int boardSize;
    static char ship =  '#';
    static String water = "\u001B[34m" + '~' + "\u001B[0m";
    static String miss = "\u001B[31m" +  'O' + "\u001B[0m";
    static String hit = "\u001B[32m" + 'X' + "\u001B[0m";
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

    public void printBoard(Board opponentBoard, String coordinate) {
        char[][] boardCopy = this.board;
        char[][] opponentBoardCopy = opponentBoard.getBoard();
        int row = Coordinate.parseIntRow(coordinate);
        int col = Coordinate.parseIntCol(coordinate);
        switch (opponentBoard.getBoard()[row][col]) {
            case '~':
                boardCopy[row][col] = 'X';
        }
    }
}