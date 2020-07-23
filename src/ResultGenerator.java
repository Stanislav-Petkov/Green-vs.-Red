/**
 * This class has methods for calculating and generating the result value.
 */
public class ResultGenerator {

    int getResultCounter(int[][] tempMatrix1, int[][] tempMatrix2, int x1, int y1, int n) {
        int counter = 0;
        while (n-- > 0) {
            for (int row = 0; row < tempMatrix1.length; row++) {
                for (int col = 0; col < tempMatrix1[row].length; col++) {
                    int currElement = tempMatrix1[row][col];

                    if (currElement == 0) {
                        boolean result = checkIfRedCellBecomesGreen(tempMatrix1, row, col);

                        if (result) {
                            // In this case a red cell is surrounded by 3 or 6 green cells and it becomes green
                            tempMatrix2[row][col] = 1;
                        } else {
                            // In this case a A red cell will stay red
                            tempMatrix2[row][col] = 0;
                        }
                    } else if (currElement == 1) {
                        boolean result = checkIfGreenCellBecomesRedOrStaysGreen(tempMatrix1, row, col);

                        if (result) {
                            // In this case a green cell will stay green if it has 2, 3, 6 green neighbours
                            tempMatrix2[row][col] = 1;
                        } else {
                            // In this case a green cell becomes red
                            tempMatrix2[row][col] = 0;
                        }
                    }
                }
            }

            if (tempMatrix2[y1][x1] == 1) {
                counter++;
            }

            // Copy matrix2 to matrix1
            copyFirstMatrixToSecondMatrix(tempMatrix2, tempMatrix1);

            // Revert matrix to initial state with all zeros
            fillMatrixWithZeroes(tempMatrix2);

        }
        return counter;
    }

    private static void fillMatrixWithZeroes(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = 0;
            }
        }
    }

    private static void copyFirstMatrixToSecondMatrix(int[][] matrix1, int[][] matrix2) {
        for (int row = 0; row < matrix1.length; row++) {
            for (int col = 0; col < matrix1[row].length; col++) {
                matrix2[row][col] = matrix1[row][col];
            }
        }
    }

    private static boolean checkIfGreenCellBecomesRedOrStaysGreen(int[][] matrix, int currRow, int currCol) {
        int sum = getTheNeighbourSumValue(matrix, currRow, currCol);
        if (sum == 2 || sum == 3 || sum == 6) {
            return true;
        }
        return false;
    }

    private static boolean checkIfRedCellBecomesGreen(int[][] matrix, int currRow, int currCol) {
        int sum = getTheNeighbourSumValue(matrix, currRow, currCol);
        if (sum == 3 || sum == 6) {
            return true;
        }
        return false;
    }

    // Returns the sum of the neighbours values
    private static int getTheNeighbourSumValue(int[][] matrix, int currRow, int currCol) {
        int sumOfTheNeighbours = 0;

        String onWhichBorderOrNotOnBorder = checkIfOnBorderOrNot(matrix, currRow, currCol);

        if (onWhichBorderOrNotOnBorder.equals("upper left corner")) {
            sumOfTheNeighbours += matrix[currRow + 1][currCol]; // Down
            sumOfTheNeighbours += matrix[currRow][currCol + 1]; // Right
            sumOfTheNeighbours += matrix[currRow + 1][currCol + 1]; // Down Right
        } else if (onWhichBorderOrNotOnBorder.equals("upper right corner")) {
            sumOfTheNeighbours += matrix[currRow + 1][currCol]; // Down
            sumOfTheNeighbours += matrix[currRow][currCol - 1]; // Left
            sumOfTheNeighbours += matrix[currRow + 1][currCol - 1]; // Down Left
        } else if (onWhichBorderOrNotOnBorder.equals("lower left corner")) {
            sumOfTheNeighbours += matrix[currRow - 1][currCol]; // Up
            sumOfTheNeighbours += matrix[currRow][currCol + 1]; // Right
            sumOfTheNeighbours += matrix[currRow - 1][currCol + 1]; // Up Right
        } else if (onWhichBorderOrNotOnBorder.equals("lower right corner")) {
            sumOfTheNeighbours += matrix[currRow - 1][currCol]; // Up
            sumOfTheNeighbours += matrix[currRow][currCol - 1]; // Left
            sumOfTheNeighbours += matrix[currRow - 1][currCol - 1]; // Up Left
        } else if (onWhichBorderOrNotOnBorder.equals("top row bound")) {
            sumOfTheNeighbours += matrix[currRow][currCol - 1]; // Left
            sumOfTheNeighbours += matrix[currRow + 1][currCol - 1]; // Down Left
            sumOfTheNeighbours += matrix[currRow + 1][currCol]; // Down
            sumOfTheNeighbours += matrix[currRow + 1][currCol + 1]; // Down Right
            sumOfTheNeighbours += matrix[currRow][currCol + 1]; // Right
        } else if (onWhichBorderOrNotOnBorder.equals("left column bound")) {
            sumOfTheNeighbours += matrix[currRow - 1][currCol]; // Up
            sumOfTheNeighbours += matrix[currRow - 1][currCol + 1]; // Up Right
            sumOfTheNeighbours += matrix[currRow][currCol + 1]; // Right
            sumOfTheNeighbours += matrix[currRow + 1][currCol + 1]; // Down Right
            sumOfTheNeighbours += matrix[currRow + 1][currCol]; // Down
        } else if (onWhichBorderOrNotOnBorder.equals("bottom row bound")) {
            sumOfTheNeighbours += matrix[currRow][currCol - 1]; // Left
            sumOfTheNeighbours += matrix[currRow - 1][currCol - 1]; // Up Left
            sumOfTheNeighbours += matrix[currRow - 1][currCol]; // Up
            sumOfTheNeighbours += matrix[currRow - 1][currCol + 1]; // Up Right
            sumOfTheNeighbours += matrix[currRow][currCol + 1]; // Right
        } else if (onWhichBorderOrNotOnBorder.equals("right column bound")) {
            sumOfTheNeighbours += matrix[currRow - 1][currCol]; // Up
            sumOfTheNeighbours += matrix[currRow - 1][currCol - 1]; // Up Left
            sumOfTheNeighbours += matrix[currRow][currCol - 1]; // Left
            sumOfTheNeighbours += matrix[currRow + 1][currCol - 1]; // Down Left
            sumOfTheNeighbours += matrix[currRow + 1][currCol]; // Down
        } else if (onWhichBorderOrNotOnBorder.equals("not on border")) {
            // The cell is not on the border, but inside
            for (int row = currRow - 1; row <= currRow + 1; row++) {
                for (int col = currCol - 1; col <= currCol + 1; col++) {
                    if (!(currCol == col && currRow == row)) {
                        sumOfTheNeighbours += matrix[row][col];
                    }
                }
            }
        }
        return sumOfTheNeighbours;
    }

    // Checks if the cell is on the border of the matrix or not
    private static String checkIfOnBorderOrNot(int[][] matrix, int currRow, int currCol) {
        String result = "";
        if (currRow == 0 && currCol == 0) {
            result = "upper left corner";
        } else if (currRow == 0 && currCol == matrix[currRow].length - 1) {
            result = "upper right corner";
        } else if (currRow == matrix.length - 1 && currCol == 0) {
            result = "lower left corner";
        } else if (currRow == matrix.length - 1 && currCol == matrix[currRow].length - 1) {
            result = "lower right corner";
        } else if (currRow == 0 && currCol > 0 && currCol < matrix[currRow].length - 1) {
            result = "top row bound";
        } else if (currCol == 0 && currRow > 0 && currRow < matrix.length - 1) {
            result = "left column bound";
        } else if (currRow == matrix.length - 1 && currCol > 0 && currCol < matrix[currRow].length - 1) {
            result = "bottom row bound";
        } else if (currCol == matrix[currRow].length - 1 && currRow > 0 && currRow < matrix.length - 1) {
            result = "right column bound";
        } else {
            result = "not on border";
        }
        return result;
    }

}
