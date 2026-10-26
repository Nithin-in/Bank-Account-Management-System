/** A concrete transaction that withdraws money from an account. */
public class WithdrawalTransaction extends BankTransaction {
    public WithdrawalTransaction(double amount) { super(amount); }

    @Override
    public void execute(BankAccount account) {
        account.withdraw(getAmount());
    }
}
