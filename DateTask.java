package Task121;

public class DateTask {
    // Step 4: Make instance variables PRIVATE for encapsulation
    private byte day;
    private byte month;
    private short year;

    // Step 5: No-args constructor → calls this(1, 1, 1) → "1/1/1"
    public DateTask() {
        this(1, 1, 1);
    }

    // Constructor with 3 arguments
    public DateTask(int m, int d, int y) {
        setDate(m, d, y);
    }

    // Step 6: toString() → format "12/1/2012"
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    // Set full date with validation
    public void setDate(int m, int d, int y) {
        if (valid(d, m, y)) {
            day = (byte) d;
            month = (byte) m;
            year = (short) y;
        } else {
            day = 0;
            month = 0;
            year = 0;
        }
    }

    // Step 7: getDay() → return day
    public int getDay() {
        return day;
    }

    // Step 8: setDay() → return 0 if invalid
    public void setDay(int day) {
        if (valid(day, this.month, this.year)) {
            this.day = (byte) day;
        } else {
            this.day = 0;
        }
    }

    // Step 9: getMonth() → return month
    public int getMonth() {
        return month;
    }

    // Step 10: setMonth() → return/set 0 if invalid
    public void setMonth(int month) {
        if (valid(this.day, month, this.year)) {
            this.month = (byte) month;
        } else {
            this.month = 0;
        }
    }

    // Step 11: getYear() → return year
    public int getYear() {
        return year;
    }

    // Step 12: setYear() → set 0 if invalid
    public void setYear(int year) {
        if (valid(this.day, this.month, year)) {
            this.year = (short) year;
        } else {
            this.year = 0;
        }
    }

    // Leap years listing (static method)
    public static void leapYears() {
        for (int i = 1980; i <= 2023; i = i + 4) {
            if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0)) {
                System.out.println("The year " + i + " is a leap year");
            }
        }
    }

    // Validation helper (private)
    private boolean valid(int day, int month, int year) {
        if (day > 31 || day < 1 || month > 12 || month < 1 || year < 1) {
            System.out.println("Attempting to create a non-valid date " + month + "/" + day + "/" + year);
            return false;
        }
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return (day <= 30);
            case 2:
                return day <= 28 || (day == 29 && year % 4 == 0);
        }
        return true;
    }
}
