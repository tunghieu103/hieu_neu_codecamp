import java.util.Scanner;

public class Game {
    private static final int[] rowMoves = {1, 1, 1, 0, 0, -1, -1, -1};
    private static final int[] colMoves = {1, 0, -1, 1, -1, 1, 0, -1};
    private static int[][] board;
    private static int boardSize;
    private static char[] alphabet;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap vao kich thuoc o vuong: ");
        boardSize = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Nhap vao chuoi ky tu: ");
        String inputString = scanner.nextLine();

        // Kiểm tra kích thước chuỗi
        int stringLength = inputString.length();
        if (stringLength < boardSize * boardSize) {
            System.out.println("Chuoi ky tu khong du de di qua tat cac o tren ban co.");
            return;
        } else if (stringLength > boardSize * boardSize) {
            System.out.println("Chuoi ky tu vuot qua so luong o tren ban co. Cac ky tu thua se bi bo qua.");
        }

        alphabet = new char[boardSize * boardSize + 1];
        for (int i = 1; i <= boardSize * boardSize; i++) {
            alphabet[i] = inputString.charAt(i - 1);
        }

        board = new int[boardSize][boardSize];

        // Khởi tạo bàn cờ với tất cả các ô có giá trị ban đầu là 0
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }

        System.out.print("Nhap vao vi tri x cua o khoi dau: ");
        int startX = scanner.nextInt();

        System.out.print("Nhap vao vi tri y cua o khoi dau: ");
        int startY = scanner.nextInt();

        board[startX][startY] = 1; 

        if (solveKingsTour(startX, startY, 2)) {
            printSolution();
        } else {
            System.out.println("Không tìm thấy lời giải.");
        }

        scanner.close();
    }

    private static boolean solveKingsTour(int x, int y, int moveNumber) {
        if (moveNumber == boardSize * boardSize + 1) {
            return true; 
        }

        for (int i = 0; i < 8; i++) {
            int nextX = x + rowMoves[i];
            int nextY = y + colMoves[i];

            if (isValidMove(nextX, nextY)) {
                board[nextX][nextY] = moveNumber;
                if (solveKingsTour(nextX, nextY, moveNumber + 1)) {
                    return true;
                } else {
                    board[nextX][nextY] = 0;
                }
            }
        }

        return false; 
    }

    private static boolean isValidMove(int x, int y) {
        return (x >= 0 && x < boardSize && y >= 0 && y < boardSize && board[x][y] == 0);
    }

    private static void printSolution() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int index = board[i][j];
                System.out.print(alphabet[index] + "\t");
            }
            System.out.println();
        }
    }
}
