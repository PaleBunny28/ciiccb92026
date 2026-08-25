import java.util.Scanner;

public class PatternGenerator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a positive integer: ");
        
        if (input.hasNextInt()) {
            int n = input.nextInt();
            
            if (n > 0) {
                System.out.println("\nGenerated Pattern:\n");
                
                for (int row = 1; row <= n; row++) {
                    
                    for (int col = 1; col <= row; col++) {
                        System.out.print(col + " ");
                    }
                    
                    // Move to next line after each row
                    System.out.println();
                }
            } else {
                System.out.println("Please enter a positive number!");
            }
        } else {
            System.out.println("Invalid input — please enter an integer.");
        }
        
        input.close();
    }
}
