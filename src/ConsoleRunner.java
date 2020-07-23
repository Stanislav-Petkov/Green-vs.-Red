/**
 * This class creates objects from the ConsoleReader, ResultGenerator, ConsolePrinter classes,
 * which are responsible for reading the input content, generating the result and printing
 * the result value.
 */
public class ConsoleRunner {

    public void run() {

        ConsoleReader reader = new ConsoleReader();
        // Read the input from the console
        reader.readInput();

        int rows = reader.getRows();
        int cols = reader.getCols();

        // Initialize the initial matrix with input values;
        int[][] generationZero = reader.getGenerationZeroMatrix();
        int[][] tempMatrix = new int[rows][cols];

        // Get the coordinates of the cell, for which the result should be generated
        int x1 = reader.getX1();
        int y1 = reader.getY1();
        int n = reader.getN();

        ResultGenerator resultCounter = new ResultGenerator();
        // Get the final result value
        int counter = resultCounter.getResultCounter(generationZero, tempMatrix, x1, y1, n);

        ConsolePrinter printer = new ConsolePrinter(counter);
        // Print the result value
        printer.printResult();
    }
}
