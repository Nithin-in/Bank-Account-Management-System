/** Lightweight assertion-based test suite; run with: java -ea -cp out BankAccountTest */
public class BankAccountTest {
    private static int passed = 0;

    public static void main(String[] args) {
        testAccountCreation();
        testDeposit();
        testInvalidDeposit();
        testWithdrawal();
        testInsufficientBalance();
        testTransfer();
        testInvalidPin();
        testUnknownAccount();
        testPolymorphicTransaction();
        System.out.println("All " + passed + " tests passed.");
    }

    private static void testAccountCreation() {
        Bank bank = new Bank();
        BankAccount a = bank.createAccount("Test User", "1234", 500);
        check(a.getAccountNumber().equals("1001"), "first account number");
        check(a.getHolderName().equals("Test User"), "holder name");
        check(a.getBalance() == 500, "opening balance");
    }

    private static void testDeposit() {
        BankAccount a = new BankAccount("1", "Test", "1234", 100);
        a.deposit(50);
        check(a.getBalance() == 150, "deposit updates balance");
    }

    private static void testInvalidDeposit() {
        BankAccount a = new BankAccount("1", "Test", "1234", 100);
        expectFailure(() -> a.deposit(0), "zero deposit rejected");
        expectFailure(() -> a.deposit(-10), "negative deposit rejected");
        check(a.getBalance() == 100, "invalid deposit leaves balance unchanged");
    }

    private static void testWithdrawal() {
        BankAccount a = new BankAccount("1", "Test", "1234", 100);
        a.withdraw(40);
        check(a.getBalance() == 60, "withdrawal updates balance");
    }

    private static void testInsufficientBalance() {
        BankAccount a = new BankAccount("1", "Test", "1234", 100);
        expectFailure(() -> a.withdraw(101), "overdraft rejected");
        check(a.getBalance() == 100, "failed withdrawal leaves balance unchanged");
    }

    private static void testTransfer() {
        Bank bank = new Bank();
        BankAccount a = bank.createAccount("Sender", "1234", 500);
        BankAccount b = bank.createAccount("Receiver", "5678", 100);
        bank.transfer(a.getAccountNumber(), b.getAccountNumber(), 125);
        check(a.getBalance() == 375, "transfer debits sender");
        check(b.getBalance() == 225, "transfer credits receiver");
    }

    private static void testInvalidPin() {
        BankAccount a = new BankAccount("1", "Test", "1234", 100);
        check(!a.verifyPin("0000"), "incorrect PIN rejected");
        check(a.verifyPin("1234"), "correct PIN accepted");
    }

    private static void testUnknownAccount() {
        Bank bank = new Bank();
        expectFailure(() -> bank.findAccount("missing"), "unknown account rejected");
    }

    private static void testPolymorphicTransaction() {
        BankAccount a = new BankAccount("1", "Test", "1234", 100);
        BankTransaction transaction = new DepositTransaction(25);
        a.executeTransaction(transaction);
        check(a.getBalance() == 125, "polymorphic deposit transaction");
    }

    private static void expectFailure(Runnable action, String label) {
        try {
            action.run();
            throw new AssertionError(label + " (expected exception)");
        } catch (IllegalArgumentException expected) {
            passed++;
        }
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError("FAILED: " + label);
        passed++;
    }
}
