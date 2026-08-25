import java.util.Scanner;

public class NumberChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a number: ");

        if (input.hasNextInt()) {
            int num = input.nextInt();
            
            if (num > 0) {
                System.out.println("Positive");
            } else if (num < 0) {
                System.out.println("Negative");
            } else {
                System.out.println("Zero");
            }
            
            if (num == 0) {
                System.out.println("Zero is neither odd nor even");
            } else if (num % 2 == 0) {
                System.out.println("Even");
            } else {
                System.out.println("Odd");
            }
            
        } else {
            System.out.println("Invalid input — please enter a whole number");
        }
        
        input.close();
    }
}
