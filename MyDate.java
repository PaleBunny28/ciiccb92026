package Task121;

public class MyDate {
    public static void main(String[] args) {
        // Step 13: date1 = Jan 1, 1978; date2 = Sept 21, 1984
        DateTask date1 = new DateTask(1, 1, 1978);
        DateTask date2 = new DateTask(9, 21, 1984);

        // Print dates
        System.out.println(date1);
        System.out.println(date2);

        // Step 14: Display leap years AFTER printing dates
        System.out.println("\n--- Leap Years ---");
        DateTask.leapYears();
    }
}
