import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Nhap vao mot so nguyen duong: ");
            int size = sc.nextInt();
            if (size <= 0) {
                System.out.println("So nhap vao phai la mot so nguyen duong!");
            } else {
                for (int i = 1; i <= size; i++) {
                    // In ra khoảng trắng để căn giữa tam giác
                    for (int j = 1; j <= size - i; j++) {
                        System.out.print(" ");
                    }

                    // In ra các ký tự '*' tương ứng 
                    for (int j = 1; j <= 2 * i - 1; j++) {
                        System.out.print("*");
                    }

                    System.out.println();
                }
            }
        }
    }
}
