import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final Random random = new Random();
    private static final int[] rowMoves = {1, 1, 1, 0, 0, -1, -1, -1};
    private static final int[] colMoves = {1, 0, -1, 1, -1, 1, 0, -1};
    private static int[][] board;
    private static int n;
    private static int m;
    private static char[] alphabet;

    public static void main(String[] args) {
        System.out.println("___________Tro choi o chu________");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nhap vao kich thuoc dong: ");
            n = scanner.nextInt();
            System.out.print("Nhap vao kich thuoc cot: ");
            m = scanner.nextInt();

            board = new int[n][m];

            ArrayList<String> arrayList = new ArrayList<>();
            scanner.nextLine();
            arrayList = gamePlay(n,m);

            // Gộp các chuỗi thành một chuỗi duy nhất

            int rand;
            System.out.println(arrayList);
            Collections.shuffle(arrayList);
            StringBuilder sb = new StringBuilder();
            for (String s : arrayList) {
                StringBuilder str = new StringBuilder(s);
                rand = random.nextInt(2);
                str = new StringBuilder(s);
                if (rand % 2 == 0){
                    str.reverse();
                }
                sb.append(str);
            }
            String mergedString = sb.toString();
            
            alphabet = new char[n * m + 1];

            // Đánh dấu các ký tự vào mảng alphabet
            for (int i = 1; i <= n * m; i++) {
                alphabet[i] = mergedString.charAt(i - 1);
            }
        }

        // Khởi tạo bàn cờ với tất cả các ô có giá trị ban đầu là 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = 0;
            }
        }

        int startX = random.nextInt(n);
        int startY = random.nextInt(m);

        board[startX][startY] = 1;

        if (solveKingsTour(startX, startY, 2)) {
            printSolution();
        } else {
            System.out.println("Khong tim thay loi giai.");
        }

    }

    public static ArrayList<String> gamePlay(int n, int m){
        try (Scanner scanner = new Scanner(System.in)) {
            int sum = 0;
            ArrayList<String> arrayList = new ArrayList<>();
            while (sum < n * m) {
                System.out.print("Nhap vao cac tu: ");
                String inputString = scanner.nextLine();
                if (inputString.length() > n * m - sum) {
                    do {
                        System.out.println("Nhap lai tu vi vuot qua");
                        inputString = scanner.nextLine();
                    } while (inputString.length() > n * m - sum);
                }
                sum += inputString.length();
                arrayList.add(inputString);
                System.out.println("So ki tu cua tu con lai la: " + (n * m - sum));
            }
            return arrayList;
        }
    }

    private static boolean solveKingsTour(int x, int y, int moveNumber) {
        if (moveNumber == n * m + 1) {
            return true; // Đã đi qua tất cả các ô
        }

     
        for (int i = 0; i < 8; i++) {
            int direct = random.nextInt(8);
            int nextX = x + rowMoves[direct];
            int nextY = y + colMoves[direct];

            if (isValidMove(nextX, nextY)) {
                board[nextX][nextY] = moveNumber;
                if (solveKingsTour(nextX, nextY, moveNumber + 1)) {
                    return true;
                } else {
                    board[nextX][nextY] = 0; // Quay lui
                }
            }
        }

        return false; // Không tìm thấy lời giải
    }

   
    private static boolean isValidMove(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < m && board[x][y] == 0);
    }

    private static void printSolution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int index = board[i][j];
                System.out.print(alphabet[index] + "\t");
            }
            System.out.println();
        }
    }
}
