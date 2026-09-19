# StudentPocket — Student Banking App (Java)

A simple console-based banking application for students, written in plain
Java with **no external dependencies and no database**. All accounts live
in memory for as long as the program is running.

## Features

- **Open an account** — name, student ID, 4-digit PIN, optional initial deposit
- **Login** with account number + PIN
- **Deposit / Withdraw** — withdrawals enforce a daily limit (5,000 by
  default) and a minimum balance of 0
- **Transfer** funds to another student's account
- **Transaction history** for each account, with timestamps
- **Change PIN**
- **Admin panel** (password `admin123`) to list all accounts, see total
  bank-wide deposits, and apply monthly interest (2.5% annual, simple
  interest) to every account

## No database

Everything is kept in a single in-memory `HashMap<String, Account>` inside
`StudentBankApp`. Nothing is written to disk, so **all accounts disappear
when the program exits** — perfect for a demo, a class assignment, or as a
starting point before you wire up real storage.

## Files

| File                  | Purpose                                      |
|------------------------|-----------------------------------------------|
| `StudentBankApp.java` | Main class and all menus                      |
| `Account.java`        | Account model: balance, PIN, withdrawal rules |
| `Transaction.java`    | A single deposit/withdrawal/transfer record   |
| `BankException.java`  | Custom exception for failed withdrawals       |

## How to run

You need a JDK installed (Java 8+). Then, from this folder:

```bash
javac *.java
java StudentBankApp
```

## Notes / things to change before real-world use

- PINs are stored as plain text for simplicity — in a real app, hash them
  (e.g. with `BCrypt` or `SHA-256` + salt).
- The admin password is hard-coded (`admin123`) — move it to a config file
  or environment variable.
- There's no persistence at all right now. If you later want accounts to
  survive a restart, two easy options are:
  - Save/load the `accounts` map to a file with Java serialization
  - Connect to a real database (SQLite is the simplest to start with)

## Ideas to extend it

- Add account types (checking vs. savings) with different rules
- Add a "savings goal" tracker for students
- Export transaction history to CSV
- Build a Swing or JavaFX GUI on top of the same `Account`/`StudentBankApp` logic
