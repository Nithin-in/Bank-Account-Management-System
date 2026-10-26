/**
 * Abstract representation of a banking transaction.
 * Subclasses implement execute(), demonstrating inheritance and runtime polymorphism.
 */
public abstract class BankTransaction {
    private final double amount;

    protected BankTransaction(double amount) {
        if (!Double.isFinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero.");
        this.amount = amount;
    }

    public double getAmount() { return amount; }
    public abstract void execute(BankAccount account);
}
