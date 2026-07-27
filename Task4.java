import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        scanner.close();

        StringBuilder reversedBuilder = new StringBuilder(input);
        reversedBuilder.reverse();
        String reversedStr = reversedBuilder.toString();

        if (input.equals(reversedStr)) {
            System.out.println("The input string is a palindrome.");
        } else {
            System.out.println("The input string is not a palindrome.");
        }
    }
}
