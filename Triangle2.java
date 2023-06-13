import java.util.Scanner;

public class Triangle2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nhap vao mot so nguyen duong: ");
            int size = scanner.nextInt();

            for (int i = 1; i <= size; i++) {
                // In ra khoảng trắng để căn giữa mẫu
                for (int j = 1; j <= size - i; j++) {
                    System.out.print("  ");
                }
    
                // In ra số tăng dần từ 1 đến i
                for (int j = 1; j <= i; j++) {
                    System.out.print(j + " ");
                }
    
                // In ra số giảm dần từ i-1 xuống 1
                for (int j = i - 1; j >= 1; j--) {
                    System.out.print(j + " ");
                }
    
                System.out.println();
            }
        }
    }
}
