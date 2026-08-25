import java.util.Scanner;

public class SumAndAverageArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter how many numbers you want to input: ");
        int size = input.nextInt();

        double[] numbers = new double[size];
        double sum = 0;

        for (int i = 0; i < size; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = input.nextDouble();
            sum = sum + numbers[i];
        }

        double average = sum / size;

        System.out.println("\n----- Results -----");
        System.out.println("Sum of all numbers: " + sum);
        System.out.println("Average of all numbers: " + average);

        input.close();
    }
}
