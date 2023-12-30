import java.util.InputMismatchException;
import java.util.Scanner;

public class connect4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            int[] columnHeight = new int[]{0, 0, 0, 0, 0, 0, 0};
            boolean player1Turn = true;
            String turn = "";
            // Populate the table with method
            String[][] populatedBoard = populateTable();
            while (true) {
                boolean winner;
                outputTable(populatedBoard);
                winner = checkForWinner(populatedBoard);
                if (winner) {
                    System.out.println(turn + " won the game!");
                    break;
                }
                if (player1Turn) {
                    turn = "Player 1";
                } else {
                    turn = "Player 2";
                }
                boolean fullTable = checkForFull(populatedBoard);
                if (fullTable) {
                    break;
                }
                System.out.println("It is " + turn + "'s turn, chose which column you want to place your piece in: ");
                try {
                    int userColumn = scan.nextInt();
                    if (userColumn > 7 || userColumn < 1) {
                        System.out.println("Enter an integer from 1-7 (0 to quit)");
                        continue;
                    }
                    int arrayColumn = userColumn - 1;

                    if (columnHeight[arrayColumn] == 6) {
                        System.out.println("column " + userColumn + " is full, enter another column");
                    } else {
                        populatedBoard = changeTable(columnHeight, player1Turn, populatedBoard, arrayColumn);
                        columnHeight[arrayColumn] += 1;
                        player1Turn = !player1Turn;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid integer, please enter a valid integer");
                    scan = new Scanner(System.in);
                }
            }
            System.out.println("Do you want to play again (n to quit)?");
            scan = new Scanner(System.in);
            String playAgain = scan.nextLine();
            if (playAgain.equalsIgnoreCase("n")) {
                break;
            }
            scan = new Scanner(System.in);
        }
    }
    private static String[][] populateTable() {
        String[][] board = new String[6][7];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = "_";
            }
        }
        return board;
    }
    private static void outputTable(String[][] board) {
        for (int r = 0; r < board.length; r++) {
            System.out.print("|");
            for (int c = 0; c < board[0].length; c++) {
                System.out.print(board[r][c] + "|");
            }
            System.out.println();
        }
        System.out.println(" 1 2 3 4 5 6 7");
    }
    private static String[][] changeTable(int[] columnHeights, boolean player1Turn, String[][] populatedBoard, int arrayColumn) {
        String move;
        if (player1Turn) {
            move = "X";
        } else {
            move = "O";
        }
        int rows = (int) (Math.sqrt(Math.pow(columnHeights[arrayColumn] - 5, 2)));
        populatedBoard[rows][arrayColumn] = move;
        return populatedBoard;
    }
    private static boolean checkForWinner(String[][] board) {
        boolean isWinner;
        boolean won = false;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < 4; c++) {
                String hPlace1 = board[r][c];
                String hPlace2 = board[r][c+1];
                String hPlace3 = board[r][c+2];
                String hPlace4 = board[r][c+3];

                if (!(hPlace1.equals("_"))) {
                    isWinner = (hPlace1.equals(hPlace2) && hPlace3.equals(hPlace4) && hPlace1.equals(hPlace4));
                    if (isWinner){
                        won = true;
                    }
                }
            }
        }
        for (int c = 0; c < board[0].length; c++) {
            for (int r = 0; r < 3; r ++) {
                String vPlace1 = board[r][c];
                String vPlace2 = board[r+1][c];
                String vPlace3 = board[r+2][c];
                String vPlace4 = board[r+3][c];

                if (!(vPlace1.equals("_"))) {
                    isWinner = (vPlace1.equals(vPlace2) && vPlace3.equals(vPlace4) && vPlace1.equals(vPlace4));
                    if (isWinner){
                        won = true;
                    }
                }
            }
        }
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                String d1Place1 = board[r][c];
                String d1Place2 = board[r+1][c+1];
                String d1Place3 = board[r+2][c+2];
                String d1Place4 = board[r+3][c+3];

                if (!(d1Place1.equals("_"))) {
                    isWinner = (d1Place1.equals(d1Place2) && d1Place3.equals(d1Place4) && d1Place1.equals(d1Place4));
                    if (isWinner){
                        won = true;
                    }
                }
            }
        }
        for (int r = 5; r > 2; r--) {
            for (int c = 0; c < 4; c++) {
                String d2Place1 = board[r][c];
                String d2Place2 = board[r-1][c+1];
                String d2Place3 = board[r-2][c+2];
                String d2Place4 = board[r-3][c+3];

                if (!(d2Place1.equals("_"))) {
                    isWinner = (d2Place1.equals(d2Place2) && d2Place3.equals(d2Place4) && d2Place1.equals(d2Place4));
                    if (isWinner){
                        won = true;
                    }
                }
            }
        }
        return won;
    }

    private static boolean checkForFull(String[][] board){
        boolean full = true;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].equals("_")) {
                    full = false;
                    break;
                }
            }
        }
        return full;
    }
}

