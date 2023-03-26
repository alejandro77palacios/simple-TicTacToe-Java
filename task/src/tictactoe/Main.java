package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char[][] game = createGame();
        showGrid(game);
        char gamer = 'X';
        while (true) {
            String first = scanner.next();
            String second = scanner.next();
            boolean firstTest = first.equals("1") || first.equals("2") || first.equals("3");
            boolean secondTest = second.equals("1") || second.equals("2") || second.equals("3");
            if (firstTest && secondTest) {
                int indexFirst = Integer.parseInt(first) - 1;
                int indexSecond = Integer.parseInt(second) - 1;
                char cell = game[indexFirst][indexSecond];
                if (cell == '_' || cell == ' ') {
                    game[indexFirst][indexSecond] = gamer;
                    gamer = changeGamer(gamer);
                    showGrid(game);
                    if (endGame(game)) {
                        break;
                    }
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                firstTest = first.equals("4") || first.equals("5") || first.equals("6") || first.equals("7") || first.equals("8") || first.equals("9") || first.equals("0");
                secondTest = second.equals("4") || second.equals("5") || second.equals("6") || second.equals("7") || second.equals("8") || second.equals("9") || second.equals("0");
                if (firstTest || secondTest) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    System.out.println("You should enter numbers!");
                }

            }

        }


    }

    private static char[][] createGame() {
        char[][] game = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = ' ';
            }
        }
        return game;
    }

    private static void showRow(char[] row) {
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(row[i] + " ");
        }
        System.out.print("|");
    }

    private static void showGrid(char[][] game) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            showRow(game[i]);
            System.out.println();
        }
        System.out.println("---------");
    }

    private static boolean winsArray(char[] arrayGame, char charGamer) {
        for (char c : arrayGame) {
            if (!(c == charGamer)) {
                return false;
            }
        }
        return true;
    }


    private static char[] getRow(char[][] game, int numRow) {
        char[] row = new char[3];
        for (int i = 0; i < 3; i++) {
            row[i] = game[numRow][i];
        }
        return row;
    }

    private static char[] getCol(char[][] game, int numCol) {
        char[] col = new char[3];
        for (int i = 0; i < 3; i++) {
            col[i] = game[i][numCol];
        }
        return col;
    }


    private static char[] getDiag(char[][] game, int numDiag) {
        char[] diag = new char[3];
        for (int i = 0; i < 3; i++) {
            if (numDiag == 0) {
                diag[i] = game[i][i];
            } else if (numDiag == 1) {
                if (i == 0) {
                    diag[i] = game[2][0];
                } else if (i == 1) {
                    diag[i] = game[1][1];
                } else {
                    diag[i] = game[0][2];
                }
            }
        }
        return diag;
    }

    private static char changeGamer(char current) {
        if (current == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    private static boolean endGame(char[][] game) {
        boolean xWins = false;
        boolean oWins = false;
        char[] emptyChars = {'_', ' '};
        int countX = 0;
        int countO = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char entry = game[i][j];
                if ('X' == entry) {
                    countX++;
                } else if ('O' == entry) {
                    countO++;
                }
            }

        }

        boolean bigDifference = Math.abs(countX - countO) >= 2;

        // check empty
        boolean someEmpty = false;
        for (char empty : emptyChars) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char entry = game[i][j];
                    if (empty == entry) {
                        someEmpty = true;
                        break;
                    }
                }

            }
        }

        // check rows
        for (int iRow = 0; iRow < 3; iRow++) {
            char[] row = getRow(game, iRow);
            if (winsArray(row, 'X')) {
                xWins = true;
            } else if (winsArray(row, 'O')) {
                oWins = true;
            }
        }

        // check cols
        for (int iCol = 0; iCol < 3; iCol++) {
            char[] col = getCol(game, iCol);
            if (winsArray(col, 'X')) {
                xWins = true;
            } else if (winsArray(col, 'O')) {
                oWins = true;
            }
        }

        // check diagonals
        for (int iDiag = 0; iDiag < 2; iDiag++) {
            char[] diag = getDiag(game, iDiag);
            if (winsArray(diag, 'X')) {
                xWins = true;
            } else if (winsArray(diag, 'O')) {
                oWins = true;
            }
        }

        boolean someWinner = xWins || oWins;
        if (bigDifference || (xWins && oWins)) {
            System.out.println("Impossible");
            return true;
        /*} else if (someEmpty && !someWinner) {
            System.out.println("Game not finished");
            return true;*/
        } else if (!someEmpty && !someWinner) {
            System.out.println("Draw");
            return true;
        } else if (xWins) {
            System.out.println("X wins");
            return true;
        } else if (oWins) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }


}
