/**
 * Thrown for business-rule violations such as insufficient funds
 * or exceeding the daily withdrawal limit.
 */
public class BankException extends Exception {
    public BankException(String message) {
        super(message);
    }
}
