/** A concrete transaction that deposits money into an account. */
public class DepositTransaction extends BankTransaction {
    public DepositTransaction(double amount) { super(amount); }

    @Override
    public void execute(BankAccount account) {
        account.deposit(getAmount());
    }
}
