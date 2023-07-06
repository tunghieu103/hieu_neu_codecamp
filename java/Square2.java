import java.util.Scanner;

public class Square2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nhap vao mot so nguyen duong: ");
            int size = scanner.nextInt();

            int[][] spiral = new int[size][size];

            int num = 1; // Số đầu tiên trong hình xoắn ốc
            int minRow = 0, maxRow = size - 1; // Giới hạn hàng
            int minCol = 0, maxCol = size - 1; // Giới hạn cột

            while (num <= size * size) {
                // Trái sang phải
                for (int i = minCol; i <= maxCol; i++) {
                    spiral[minRow][i] = num % 9 != 0 ? num % 9 : 9;
                    num++;
                }
                minRow++;

                // Trên xuống dưới
                for (int i = minRow; i <= maxRow; i++) {
                    spiral[i][maxCol] = num % 9 != 0 ? num % 9 : 9;
                    num++;
                }
                maxCol--;

                // Phải sang trái
                for (int i = maxCol; i >= minCol; i--) {
                    spiral[maxRow][i] = num % 9 != 0 ? num % 9 : 9;
                    num++;
                }
                maxRow--;

                // Dưới lên trên
                for (int i = maxRow; i >= minRow; i--) {
                    spiral[i][minCol] = num % 9 != 0 ? num % 9 : 9;
                    num++;
                }
                minCol++;
            }

            // In hình vuông xoắn ốc
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(spiral[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
