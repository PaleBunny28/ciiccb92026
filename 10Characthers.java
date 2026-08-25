import java.util.Scanner;

public class StringBuilderDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb;

        while (true) {
            System.out.print("Enter a string of at least 10 characters: ");
            String text = input.nextLine();

            if (text.length() >= 10) {
                sb = new StringBuilder(text);
                break;
            } else {
                System.out.println("Input is too short. Please enter at least 10 characters.\n");
            }
        }

        System.out.println("\n===== StringBuilder Operations =====\n");

        System.out.println("1. Length: " + sb.length());

        System.out.println("2. First character: " + sb.charAt(0));

        System.out.println("3. Last character: " + sb.charAt(sb.length() - 1));

        int position = sb.indexOf("a");
        if (position != -1) {
            System.out.println("4. First occurrence of 'a' at index: " + position);
        } else {
            System.out.println("4. Letter 'a' was not found.");
        }

        System.out.println("5. Substring from index 3 to 6: " + sb.substring(3, 6));

        sb.append("123");
        System.out.println("6. After appending '123': " + sb.toString());

        sb.insert(4, "xyz");
        System.out.println("7. After inserting 'xyz' at index 4: " + sb.toString());

        sb.delete(2, 4);
        System.out.println("8. After deleting from index 2 to 4: " + sb.toString());

        sb.deleteCharAt(8);
        System.out.println("9. After deleting character at index 8: " + sb.toString());

        sb.reverse();
        System.out.println("10. Reversed string: " + sb.toString());

        input.close();
    }
}
