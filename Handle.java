import java.util.Scanner;
public class Handle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter text(exit - stop):");
            String str = scanner.nextLine();
            if (str.equals("exit")) {
                break;
            }
            System.out.println("You entered: " + str.toUpperCase());
        }
        scanner.close();
    }
}