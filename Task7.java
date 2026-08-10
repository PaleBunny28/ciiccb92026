import java.util.Scanner;

public class Task7 {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            return Double.NaN;
        }
        return a / b;
    }

    public static String formatNumber(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        } else {
            return String.format("%s", value);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        scanner.close();

        double sum = add(num1, num2);
        double diff = subtract(num1, num2);
        double prod = multiply(num1, num2);
        double quot = divide(num1, num2);

        System.out.println("Sum: " + formatNumber(sum));
        System.out.println("Difference: " + formatNumber(diff));
        System.out.println("Product: " + formatNumber(prod));

        if (Double.isNaN(quot)) {
            System.out.println("Quotient: Division by zero is not allowed.");
        } else {
            System.out.println("Quotient: " + formatNumber(quot));
        }
    }
}
