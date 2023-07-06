import java.util.Scanner;

public class SpiralTriangle2 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Nhap vao mot so nguyen duong: ");
            int length = sc.nextInt();
            spiralTriangle(length);
        }
    }
    public static void spiralTriangle(int height){
        int side = height * 2 - 1;
        int[][] matrix = new int[height][side];
        int start = 0, end = height - 1, m = 0, i = 1;
        int x, y, count = 0;
        if (height % 2 == 0) {
            while (matrix[height / 2][height - 2] == 0) {
                if (count % 3 == 0) {
                    x = start;
                    y = side / 2;
                    while (y <= end * 2) {
                        if (i == 10) i = 1;
                        matrix[x][y] = i;
                        x++;
                        y++;
                        i++;
                    }
                    count++;
                } else if (count % 3 == 1) {
                    x = end;
                    y = end * 2 - 1;
                    while (y >= m + 1) {
                        if (i == 10) i = 1;
                        matrix[x][y] = i;
                        y--;
                        i++;
                    }
                    count++;
                } else if (count % 3 == 2) {
                    x = end;
                    y = m;
                    while (x > start) {
                        if (i == 10) i = 1;
                        matrix[x][y] = i;
                        y++;
                        x--;
                        i++;
                    }
                    m = m + 2;
                    start++;
                    end--;
                    count++;
                }
            }
        } else {
            while (matrix[height / 2][(height / 2) * 2] == 0) {
                if (count % 3 == 0) {
                    x = start;
                    y = side / 2;
                    while (y <= end * 2) {
                        if (i == 10) i = 1;
                        matrix[x][y] = i;
                        x++;
                        y++;
                        i++;
                    }
                    count++;
                } else if (count % 3 == 1) {
                    x = end;
                    y = end * 2 - 1;
                    while (y >= m + 1) {
                        if (i == 10) i = 1;
                        matrix[x][y] = i;
                        y--;
                        i++;
                    }
                    count++;
                } else if (count % 3 == 2) {
                    x = end;
                    y = m;
                    while (x > start) {
                        if (i == 10) i = 1;
                        matrix[x][y] = i;
                        y++;
                        x--;
                        i++;
                    }
                    m = m + 2;
                    start++;
                    end--;
                    count++;
                }
            }
        }
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}

