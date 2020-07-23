/**
 * This class has a method for printing the result value on the console
 */
public class ConsolePrinter {

    private int resultCounter;

    public ConsolePrinter(int resultCounter) {
        this.resultCounter = resultCounter;
    }

    public void printResult() {
        System.out.println("Result: " + resultCounter);
    }
}
