import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordinate {
    Pattern pattern = Pattern.compile("([0-9])([a-zA-z])|([a-zA-Z])([0-9])");
    Matcher matcher;
    int size = Board.boardSize;

    public boolean inputValidator(String coordinate) {
        matcher = pattern.matcher(coordinate);
        int row = 0;
        int col = 0;
        if (matcher.matches()) {
            char letter = matcher.group(2) != null ? matcher.group(2).charAt(0) : matcher.group(3).charAt(0);
            char number = matcher.group(1) != null ? matcher.group(1).charAt(0) : matcher.group(4).charAt(0);
            row = number - '0';
            col = Character.toUpperCase(letter) - 'A';
        } else return false;
        return row >= 0 && col >= 0 && row < size && col < size;
    }
}
