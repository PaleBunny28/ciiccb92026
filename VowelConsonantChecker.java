import java.util.Scanner;

public class VowelConsonantChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a word or sentence: ");
        String text = input.nextLine().toLowerCase(); // convert to lowercase for easy checking
        
        int vowelCount = 0;
        int consonantCount = 0;
        String vowelsList = "aeiou";

        System.out.println("\n----- Character Check -----");

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                if (vowelsList.indexOf(ch) != -1) {
                    System.out.println(ch + " → Vowel");
                    vowelCount++;
                } else {
                    System.out.println(ch + " → Consonant");
                    consonantCount++;
                }
            }
        }

        System.out.println("\n✅ Total Vowels: " + vowelCount);
        System.out.println("✅ Total Consonants: " + consonantCount);
        
        input.close();
    }
}
