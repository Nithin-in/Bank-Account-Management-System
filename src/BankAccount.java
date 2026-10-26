import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Models one bank account and protects its balance through validated operations. */
public class BankAccount {
    private final String accountNumber;
    private final String holderName;
    private final String pin;
    private double balance;
    private final List<String> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, String holderName, String pin, double openingBalance) {
        if (accountNumber == null || accountNumber.isBlank())
            throw new IllegalArgumentException("Account number is required.");
        if (holderName == null || holderName.isBlank())
            throw new IllegalArgumentException("Account holder name is required.");
        if (pin == null || !pin.matches("\\d{4}"))
            throw new IllegalArgumentException("PIN must contain exactly 4 digits.");
        if (!Double.isFinite(openingBalance) || openingBalance < 0)
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        this.accountNumber = accountNumber;
        this.holderName = holderName.trim();
        this.pin = pin;
        this.balance = openingBalance;
        transactions.add(String.format("Account opened with balance ₹%.2f", openingBalance));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public boolean verifyPin(String enteredPin) { return pin.equals(enteredPin); }
    public List<String> getTransactions() { return Collections.unmodifiableList(transactions); }

    /** Executes transactions through an abstract base type to demonstrate polymorphism. */
    public void executeTransaction(BankTransaction transaction) {
        if (transaction == null) throw new IllegalArgumentException("Transaction is required.");
        transaction.execute(this);
    }

    public void deposit(double amount) {
        validateAmount(amount);
        balance += amount;
        transactions.add(String.format("Deposit: +₹%.2f | Balance: ₹%.2f", amount, balance));
    }

    public void withdraw(double amount) {
        validateAmount(amount);
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance.");
        balance -= amount;
        transactions.add(String.format("Withdrawal: -₹%.2f | Balance: ₹%.2f", amount, balance));
    }

    private void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero.");
    }

    void recordTransfer(String description) { transactions.add(description); }
}
