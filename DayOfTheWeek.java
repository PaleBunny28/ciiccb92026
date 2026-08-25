import java.util.Scanner;

public class DayOfWeek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number (1-7): ");
        
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            
            // Map number to day
            if (number == 1) {
                System.out.println("Monday");
            } else if (number == 2) {
                System.out.println("Tuesday");
            } else if (number == 3) {
                System.out.println("Wednesday");
            } else if (number == 4) {
                System.out.println("Thursday");
            } else if (number == 5) {
                System.out.println("Friday");
            } else if (number == 6) {
                System.out.println("Saturday");
            } else if (number == 7) {
                System.out.println("Sunday");
            } else {
                // Number is integer but outside 1–7
                System.out.println("Invalid input");
            }
        } else {
            // Not a number — character, text, symbol, etc.
            System.out.println("Invalid input");
        }
        
        scanner.close();
    }
}
