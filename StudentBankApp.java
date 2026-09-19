import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * StudentBankApp
 * ----------------
 * A console-based student banking system written in plain Java (no external
 * libraries, no database). Supports account registration, login, deposits,
 * withdrawals (with a daily limit), balance checks, transaction history,
 * transfers between students, monthly interest, and a simple admin view.
 *
 * All data lives in memory only (a HashMap) for the lifetime of the running
 * program - there is no database and nothing is written to disk. Once you
 * exit the app, every account is gone. This keeps the app dependency-free
 * and easy to run anywhere a JVM is available.
 *
 * Compile:  javac *.java
 * Run:      java StudentBankApp
 */
public class StudentBankApp {

    private static final String BANK_NAME = "StudentPocket";
    private static final String ADMIN_PASSWORD = "admin123"; // change before real use

    private final Map<String, Account> accounts = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public static void main(String[] args) {
        StudentBankApp app = new StudentBankApp();
        app.run();
        System.out.println("Goodbye! (Remember: nothing is saved between runs - there's no database.)");
    }

    private void run() {
        System.out.println("=================================================");
        System.out.println("   Welcome to " + BANK_NAME + " - Student Banking App");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    registerAccount();
                    break;
                case "2":
                    loginFlow();
                    break;
                case "3":
                    adminFlow();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Open a new student account");
        System.out.println("2. Login to my account");
        System.out.println("3. Admin panel");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    // ---------------------------------------------------------------
    // Registration
    // ---------------------------------------------------------------

    private void registerAccount() {
        System.out.println("\n--- Open a New Student Account ---");
        System.out.print("Full name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine().trim();

        String pin = readNewPin();

        double initialDeposit = readNonNegativeDouble("Initial deposit (0 or more): ");

        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, studentId, name, pin, initialDeposit);
        accounts.put(accountNumber, account);

        System.out.println("\nAccount created successfully!");
        System.out.println("Your account number is: " + accountNumber);
        System.out.println("Keep this number and your PIN safe - you'll need both to log in.");
    }

    private String readNewPin() {
        while (true) {
            System.out.print("Choose a 4-digit PIN: ");
            String pin = scanner.nextLine().trim();
            if (pin.matches("\\d{4}")) {
                return pin;
            }
            System.out.println("PIN must be exactly 4 digits. Try again.");
        }
    }

    private String generateAccountNumber() {
        String candidate;
        do {
            candidate = "STU" + (100000 + random.nextInt(900000));
        } while (accounts.containsKey(candidate));
        return candidate;
    }

    // ---------------------------------------------------------------
    // Login + account session
    // ---------------------------------------------------------------

    private void loginFlow() {
        System.out.println("\n--- Login ---");
        System.out.print("Account number: ");
        String accNum = scanner.nextLine().trim();

        Account account = accounts.get(accNum);
        if (account == null) {
            System.out.println("No account found with that number.");
            return;
        }

        System.out.print("PIN: ");
        String pin = scanner.nextLine().trim();

        if (!account.checkPin(pin)) {
            System.out.println("Incorrect PIN.");
            return;
        }

        System.out.println("\nWelcome back, " + account.getFullName() + "!");
        accountSession(account);
    }

    private void accountSession(Account account) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- Account Menu (" + account.getAccountNumber() + ") ---");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer to another student");
            System.out.println("5. View transaction history");
            System.out.println("6. Change PIN");
            System.out.println("7. Log out");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.printf("Current balance: %.2f%n", account.getBalance());
                    break;
                case "2":
                    depositFlow(account);
                    break;
                case "3":
                    withdrawFlow(account);
                    break;
                case "4":
                    transferFlow(account);
                    break;
                case "5":
                    account.printStatement();
                    break;
                case "6":
                    changePinFlow(account);
                    break;
                case "7":
                    loggedIn = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void depositFlow(Account account) {
        double amount = readPositiveDouble("Amount to deposit: ");
        account.deposit(amount, "Cash deposit");
        System.out.printf("Deposit successful. New balance: %.2f%n", account.getBalance());
    }

    private void withdrawFlow(Account account) {
        double amount = readPositiveDouble("Amount to withdraw: ");
        try {
            account.withdraw(amount, "Cash withdrawal");
            System.out.printf("Withdrawal successful. New balance: %.2f%n", account.getBalance());
        } catch (BankException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private void transferFlow(Account sender) {
        System.out.print("Recipient account number: ");
        String toAccNum = scanner.nextLine().trim();

        if (toAccNum.equals(sender.getAccountNumber())) {
            System.out.println("You cannot transfer to your own account.");
            return;
        }

        Account recipient = accounts.get(toAccNum);
        if (recipient == null) {
            System.out.println("No account found with that number.");
            return;
        }

        double amount = readPositiveDouble("Amount to transfer: ");
        try {
            sender.withdraw(amount, "Transfer to " + recipient.getAccountNumber());
            recipient.deposit(amount, "Transfer from " + sender.getAccountNumber());
            System.out.printf("Transfer successful. New balance: %.2f%n", sender.getBalance());
        } catch (BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    private void changePinFlow(Account account) {
        System.out.print("Current PIN: ");
        String current = scanner.nextLine().trim();
        if (!account.checkPin(current)) {
            System.out.println("Incorrect PIN.");
            return;
        }
        String newPin = readNewPin();
        account.changePin(newPin);
        System.out.println("PIN updated successfully.");
    }

    // ---------------------------------------------------------------
    // Admin panel
    // ---------------------------------------------------------------

    private void adminFlow() {
        System.out.print("\nAdmin password: ");
        String pw = scanner.nextLine().trim();
        if (!ADMIN_PASSWORD.equals(pw)) {
            System.out.println("Incorrect admin password.");
            return;
        }

        boolean inAdmin = true;
        while (inAdmin) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. List all accounts");
            System.out.println("2. Show total deposits in the bank");
            System.out.println("3. Apply monthly interest to all accounts");
            System.out.println("4. Back to main menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listAllAccounts();
                    break;
                case "2":
                    showTotalDeposits();
                    break;
                case "3":
                    applyInterestToAll();
                    break;
                case "4":
                    inAdmin = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void listAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet.");
            return;
        }
        System.out.println("\n--- All Accounts (" + accounts.size() + ") ---");
        for (Account a : accounts.values()) {
            System.out.println(a);
        }
    }

    private void showTotalDeposits() {
        double total = accounts.values().stream().mapToDouble(Account::getBalance).sum();
        System.out.printf("Total funds held across all student accounts: %.2f%n", total);
    }

    private void applyInterestToAll() {
        for (Account a : accounts.values()) {
            a.applyMonthlyInterest();
        }
        System.out.println("Monthly interest applied to all accounts.");
    }

    // ---------------------------------------------------------------
    // Input helpers
    // ---------------------------------------------------------------

    private double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("Amount must be greater than zero.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Amount cannot be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

}
