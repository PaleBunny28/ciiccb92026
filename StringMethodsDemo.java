import java.util.Scanner;

public class StringMethodsDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter any string: ");
        String text = input.nextLine();
        
        System.out.println("\n----- Results -----");
        
        int length = text.length();
        System.out.println("1. Length: " + length);
        
        String upper = text.toUpperCase();
        System.out.println("2. Uppercase: " + upper);
        
        String lower = text.toLowerCase();
        System.out.println("3. Lowercase: " + lower);
        
        char first = text.charAt(0);
        System.out.println("4. First character: " + first);
        
        char last = text.charAt(text.length() - 1);
        System.out.println("5. Last character: " + last);
        
        if (text.length() >= 5) {
            String part = text.substring(1, 5);
            System.out.println("6. 2nd to 5th character: " + part);
        } else {
            System.out.println("6. String is too short for 2nd–5th characters");
        }
        
        input.close();
    }
}
