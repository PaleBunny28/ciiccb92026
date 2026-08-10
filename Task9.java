import static java.lang.Math.*;
import java.util.Scanner;

public class Task9 {

    public static int add(int a, int b) {
        return addExact(a, b);
    }

    public static int subtract(int a, int b) {
        return subtractExact(a, b);
    }

    public static int multiply(int a, int b) {
        return multiplyExact(a, b);
    }

    public static float divide(int a, int b) {
        if (b == 0) {
            System.out.println("⚠ Cannot divide by zero!");
            return 0;
        }
        return (float) floorDiv(a, b);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Ask user for numbers
        System.out.print("Enter first number: ");
        int num1 = input.nextInt();
        System.out.print("Enter second number: ");
        int num2 = input.nextInt();
        input.close();

        // Calculate and display results
        System.out.println("\n--- Results ---");
        System.out.println("Sum: " + add(num1, num2));
        System.out.println("Difference: " + subtract(num1, num2));
        System.out.println("Product: " + multiply(num1, num2));
        System.out.println("Quotient: " + divide(num1, num2));
    }
}
