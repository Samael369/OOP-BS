import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    byte singlePlayer = 1;
    byte multiPlayer = 2;

    public byte gameMode() {
        System.out.println("Please choose the game mode: ");
        System.out.println("1: Single player");
        System.out.println("2: Multi player");
        return scanner.nextByte();
    }

    public void start() {
        boolean playAgain;
        do {
            playGame();
            playAgain = askReplay();
        } while (playAgain);
    }

    private boolean askReplay() {
        System.out.println("Play again? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        return scanner.next().equalsIgnoreCase("yes");
    }

    private void playGame() {
        int mode = gameMode();
        if (mode == singlePlayer) {
            singlePlayer();
        } else if (mode == multiPlayer) {
            multiPlayer();
        }

    }

    public void singlePlayer() {
        System.out.println("enter your name: ");
        String name = scanner.next();
        Player player = new Player(name);
        AIPlayer aiPlayer = new AIPlayer("Computer");
        Board.setSize();
        Board playerBoard = new Board();
        Board computerBoard = new Board();
        Board playerTrackingBoard = new Board();
        Board computerTrackingBoard = new Board();
        playerBoard.placeShips();
        computerBoard.placeShips();
        boolean playerTurn = true;
        do {
            if (playerTurn) {
                playerTrackingBoard.printBoard();
                String coordinate = player.makeMove();
                while (!Coordinate.inputValidator(coordinate, playerTrackingBoard)) {
                    System.out.println("\u001B[31m" + "Invalid input!" + "\u001B[0m");
                    coordinate = player.makeMove();
                }
                playerTrackingBoard.updateBoard(computerBoard, coordinate);
            } else {
                System.out.println("Computer ");
                String coordinate = aiPlayer.makeMove();
                while (!Coordinate.inputValidator(coordinate, computerTrackingBoard)) {
                    coordinate = aiPlayer.makeMove();
                }
                computerTrackingBoard.updateBoard(playerBoard, coordinate);
            }
            playerTurn = !playerTurn;
        } while (!gameOver(playerBoard, computerBoard));
    }

    public void multiPlayer() {
        System.out.println("enter first player's name: ");
        String player1Name = scanner.next();
        Player player1 = new Player(player1Name);
        System.out.println("enter second player's name: ");
        String player2Name = scanner.next();
        Player player2 = new Player(player2Name);
    }

    public boolean gameOver(Board player1, Board player2) {
        return player1.allShipsSunk() || player2.allShipsSunk();
    }
}
