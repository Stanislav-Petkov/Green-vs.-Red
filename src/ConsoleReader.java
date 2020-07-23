import java.util.Arrays;
import java.util.Scanner;

/**
 * This class reads the input from the console.
 */
public class ConsoleReader {
    private Scanner scanner;
    private int cols;  // cols is equal to x being the width
    private int rows;  // rows is equal to y being the height
    private int[][] generationZero;
    private int x1;
    private int y1;
    private int n;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    public void readInput() {
        String[] line = scanner.nextLine().split(", ");
        cols = Integer.parseInt(line[0]);
        rows = Integer.parseInt(line[1]);

        this.generationZero = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            // Read the (long x characters)
            generationZero[row] = Arrays.stream(scanner.nextLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // Read the last line from the input
        String lastLine = scanner.nextLine();

        // Split the last line into coordinates and N as Integers
        int[] coordinatesAndN = Arrays.stream(lastLine.split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        this.x1 = coordinatesAndN[0];
        this.y1 = coordinatesAndN[1];
        this.n = coordinatesAndN[2];
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public int getX1() {
        return this.x1;
    }

    public int getY1() {
        return this.y1;
    }

    public int getN() {
        return this.n;
    }

    public int[][] getGenerationZeroMatrix() {
        return this.generationZero;
    }
}
