import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single transaction (deposit, withdrawal, transfer, interest)
 * recorded against a student account.
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final String description;
    private final String timestamp;

    public Transaction(String type, double amount, double balanceAfter, String description) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-10s %10.2f | Balance after: %10.2f | %s",
                timestamp, type, amount, balanceAfter, description);
    }
}
