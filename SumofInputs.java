import java.util.Scanner;

public class SumOfInputs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int sum = 0;
        int count = 0;
        
        System.out.println("Enter integers one by one.");
        System.out.println("Type any non-number when finished.");

        while (true) {
            System.out.print("Enter number: ");
            
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                sum = sum + number;   // add number to total
                count = count + 1;     // count how many numbers
            } else {
                // Stop loop when input is NOT an integer
                scanner.next(); // clear invalid input
                break;
            }
        }

        System.out.println("\n✅ Total numbers entered: " + count);
        System.out.println("✅ Sum of all inputs: " + sum);
        
        scanner.close();
    }
}
