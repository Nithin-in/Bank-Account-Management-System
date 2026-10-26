import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** Coordinates account creation, lookup and transfers between accounts. */
public class Bank {
    private final Map<String, BankAccount> accounts = new LinkedHashMap<>();
    private int nextAccountNumber = 1001;

    public BankAccount createAccount(String name, String pin, double openingBalance) {
        String number = String.valueOf(nextAccountNumber);
        BankAccount account = new BankAccount(number, name, pin, openingBalance);
        accounts.put(number, account);
        nextAccountNumber++;
        return account;
    }

    public BankAccount findAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) throw new IllegalArgumentException("Account not found.");
        return account;
    }

    public Collection<BankAccount> getAllAccounts() {
        return java.util.Collections.unmodifiableCollection(accounts.values());
    }

    public void transfer(String fromNumber, String toNumber, double amount) {
        if (fromNumber.equals(toNumber))
            throw new IllegalArgumentException("Source and destination accounts must differ.");
        BankAccount from = findAccount(fromNumber);
        BankAccount to = findAccount(toNumber);
        from.withdraw(amount);
        try {
            to.deposit(amount);
        } catch (RuntimeException ex) {
            from.deposit(amount);
            throw ex;
        }
        from.recordTransfer(String.format("Transfer to %s: -₹%.2f", toNumber, amount));
        to.recordTransfer(String.format("Transfer from %s: +₹%.2f", fromNumber, amount));
    }
}
