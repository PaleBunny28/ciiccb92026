import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a sentence or text: ");
        String text = input.nextLine().trim(); // Remove leading/trailing spaces

        int wordCount = 0;

        if (text.isEmpty()) {
            wordCount = 0;
        } else {
            String[] words = text.split("\\s+");
            wordCount = words.length;
        }

        System.out.println("\nNumber of words: " + wordCount);
        
        input.close();
    }
}
