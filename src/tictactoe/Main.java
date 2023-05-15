package tictactoe;

import java.util.Scanner;
import java.lang.String;
import java.util.InputMismatchException;

public class Main {
    static String[][] arr = {{"_", "_", "_"}, {"_", "_", "_"}, {"_", "_", "_"}};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to a Simple Tic-Tac-Toe game!!");
        printGrid();
        // Inputting user for coordinates
        String turn = "X";
        while (true) {
            try {
                System.out.printf("Please enter co-ordinates for %s: ", turn);
                int x = scan.nextInt();
                int y = scan.nextInt();
                if (areValidCoordinates(x, y)) {
                    updateGrid(x, y, turn);
                    printGrid();
                    if (turn.equals("X")) {
                        if (hasWon("X")) {
                            System.out.printf("%s wins\n", turn);
                            break;
                        }
                        turn = "O";
                    } else {
                        if (hasWon("O")) {
                            System.out.printf("%s wins\n", turn);
                            break;
                        }
                        turn = "X";
                    }
                    if (isGameFinished()) {
                        System.out.println("Draw");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scan.nextLine();
            }
        }
    }

    static boolean areValidCoordinates(int x, int y) {
        if (x > 3 || y > 3 || x < 1 || y < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (!arr[x - 1][y - 1].equals("_")) {
            System.out.println("This cell is already occupied! Choose another one!");
            return false;
        }
        return true;
    }

    static boolean hasWon(String turn) {
        boolean checkColumn = true;
        boolean checkRow = true;
        boolean checkLeftDiagonal = true;
        boolean checkRightDiagonal = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!arr[i][j].equals(turn)) {
                    checkRow = false;
                }
                if (!arr[j][i].equals(turn)) {
                    checkColumn = false;
                }
                if (i == j) {
                    if (!arr[i][j].equals(turn)) {
                        checkLeftDiagonal = false;
                    }
                }
                if (i + j == 2) {
                    if (!arr[i][j].equals(turn)) {
                        checkRightDiagonal = false;
                    }
                }
            }
            if (checkRow || checkColumn) {
                return true;
            }
            checkRow = true;
            checkColumn = true;
        }
        return checkRightDiagonal || checkLeftDiagonal;
    }

    static boolean isGameFinished() {
        for (String[] i : arr) {
            for (String j : i) {
                if (j.equals("_")) {
                    return false;
                }
            }
        }
        return true;
    }

    static void updateGrid(int x, int y, String turn) {
        arr[x - 1][y - 1] = (turn.equals("X")) ? "X" : "O";
    }

    static void printGrid() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.printf("| %s", arr[i][j]);
                } else if (j == 2) {
                    System.out.printf(" %s |\n", arr[i][j]);
                } else {
                    System.out.printf(" %s", arr[i][j]);
                }
            }
        }
        System.out.println("---------");
    }
}