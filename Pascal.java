import java.util.Scanner;

final class Pascal {

    private Pascal() {
        // To prevent instantiation
    }

    static void TrianglePrinter(final int n) {

        for (int line = 1; line <= n; line++) {
            int c = 1;
            for (int i = 1; i <= line; i++) {
                System.out.print(c + " ");
                c = c * (line - i) / i;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter N: ");
        int input = scanner.nextInt();

        TrianglePrinter(input);
    }
}