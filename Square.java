import java.util.Scanner;

public class Square {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Nhap vao so nguyen duong: ");
            int size = sc.nextInt();

            if (size <= 0) {
                System.out.println("So nhap vao phai la mot so nguyen duong!");
            } else {
                // In ra hình vuông
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
            }
        }
    }
}
