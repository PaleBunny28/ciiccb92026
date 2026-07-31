public class Task3 {

    public static void main(String[] args) {

        String b = "Wow";
        String a = b;
        String c = "Hello";
        String d = "Wow!";

        boolean b1 = a == b;
        boolean b2 = d.equals(b + "!");
        boolean b3 = !c.equals(a);

        if (b1 && b2 && b3) {
            System.out.println("Success!");
        }

    }
}
