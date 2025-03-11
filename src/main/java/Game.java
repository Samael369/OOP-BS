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
        }

    }

    public void singlePlayer() {
        System.out.println("enter your name: ");
        String name = scanner.next();
        Player player = new Player(name);
        int size = 10;
        do {
            System.out.println(size > 5 ? "enter the board size: " : "enter a valid board size: ");
            size = scanner.nextInt();
        } while (size < 5);
        Board.boardSize = size;
        Board playerBoard = new Board();
        Board computerBoard = new Board();
        Board playerTrackingBoard = new Board();
        Board computerTrackingBoard = new Board();
    }

    public void multiPlayer() {
        System.out.println("enter first player's name: ");
        String player1Name = scanner.next();
        Player player1 = new Player(player1Name);
        System.out.println("enter second player's name: ");
        String player2Name = scanner.next();
        Player player2 = new Player(player2Name);
    }
}
