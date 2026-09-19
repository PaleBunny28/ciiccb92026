import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student's bank account.
 * Enforces a daily withdrawal limit and a minimum balance, typical of
 * a student/savings account.
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final double DAILY_WITHDRAW_LIMIT = 5000.0;
    public static final double MIN_BALANCE = 0.0;
    public static final double ANNUAL_INTEREST_RATE = 0.025; // 2.5% simple annual interest

    private final String accountNumber;
    private final String studentId;
    private final String fullName;
    private String pin; // 4-digit PIN stored as text (see README note on hashing for production use)
    private double balance;
    private final List<Transaction> history;

    private double withdrawnToday;
    private String lastWithdrawDate;

    public Account(String accountNumber, String studentId, String fullName, String pin, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.studentId = studentId;
        this.fullName = fullName;
        this.pin = pin;
        this.balance = 0.0;
        this.history = new ArrayList<>();
        this.withdrawnToday = 0.0;
        this.lastWithdrawDate = "";

        if (initialDeposit > 0) {
            deposit(initialDeposit, "Initial deposit on account opening");
        }
    }

    public void deposit(double amount, String description) {
        balance += amount;
        history.add(new Transaction("DEPOSIT", amount, balance, description));
    }

    /**
     * Attempts a withdrawal, enforcing the minimum balance and the
     * daily withdrawal limit for student accounts.
     */
    public void withdraw(double amount, String description) throws BankException {
        resetDailyLimitIfNewDay();

        if (balance - amount < MIN_BALANCE) {
            throw new BankException("Insufficient funds. Available balance: " + String.format("%.2f", balance));
        }
        if (withdrawnToday + amount > DAILY_WITHDRAW_LIMIT) {
            double remaining = DAILY_WITHDRAW_LIMIT - withdrawnToday;
            throw new BankException("Daily withdrawal limit exceeded. You can still withdraw up to "
                    + String.format("%.2f", Math.max(remaining, 0)) + " today.");
        }

        balance -= amount;
        withdrawnToday += amount;
        history.add(new Transaction("WITHDRAW", amount, balance, description));
    }

    private void resetDailyLimitIfNewDay() {
        String today = LocalDate.now().toString();
        if (!today.equals(lastWithdrawDate)) {
            lastWithdrawDate = today;
            withdrawnToday = 0.0;
        }
    }

    /** Applies one month of simple interest based on the annual rate. */
    public void applyMonthlyInterest() {
        double interest = balance * (ANNUAL_INTEREST_RATE / 12);
        if (interest > 0) {
            balance += interest;
            history.add(new Transaction("INTEREST", interest, balance, "Monthly interest credited"));
        }
    }

    public boolean checkPin(String attempt) {
        return pin.equals(attempt);
    }

    public void changePin(String newPin) {
        this.pin = newPin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public void printStatement() {
        System.out.println("---- Transaction History for " + accountNumber + " (" + fullName + ") ----");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (Transaction t : history) {
            System.out.println(t);
        }
    }

    @Override
    public String toString() {
        return String.format("%s | %-20s | Student ID: %-10s | Balance: %10.2f",
                accountNumber, fullName, studentId, balance);
    }
}
