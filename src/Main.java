import java.util.Scanner;

/** Console user interface for the Bank Account Management System. */
public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Bank BANK = new Bank();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("========================================");
        System.out.println("     BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("========================================");
        while (running) {
            printMenu();
            String choice = INPUT.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> createAccount();
                    case "2" -> showBalance();
                    case "3" -> deposit();
                    case "4" -> withdraw();
                    case "5" -> transfer();
                    case "6" -> transactionHistory();
                    case "7" -> listAccounts();
                    case "0" -> { running = false; System.out.println("Thank you for using the system."); }
                    default -> System.out.println("Invalid option. Please choose from the menu.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Input error. Please enter a valid value.");
            }
            System.out.println();
        }
        INPUT.close();
    }

    private static void printMenu() {
        System.out.println("1. Create account");
        System.out.println("2. Check balance");
        System.out.println("3. Deposit money");
        System.out.println("4. Withdraw money");
        System.out.println("5. Transfer money");
        System.out.println("6. View transaction history");
        System.out.println("7. List accounts (demo/admin)");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void createAccount() {
        System.out.print("Account holder name: ");
        String name = INPUT.nextLine();
        System.out.print("Set 4-digit PIN: ");
        String pin = INPUT.nextLine().trim();
        double opening = readAmount("Opening deposit (₹): ");
        BankAccount account = BANK.createAccount(name, pin, opening);
        System.out.println("Account created successfully. Account number: " + account.getAccountNumber());
    }

    private static BankAccount authenticatedAccount() {
        System.out.print("Account number: ");
        BankAccount account = BANK.findAccount(INPUT.nextLine().trim());
        System.out.print("4-digit PIN: ");
        if (!account.verifyPin(INPUT.nextLine().trim()))
            throw new IllegalArgumentException("Incorrect PIN.");
        return account;
    }

    private static void showBalance() {
        BankAccount account = authenticatedAccount();
        System.out.printf("%s, your available balance is ₹%.2f%n", account.getHolderName(), account.getBalance());
    }

    private static void deposit() {
        BankAccount account = authenticatedAccount();
        account.executeTransaction(new DepositTransaction(readAmount("Amount to deposit (₹): ")));
        System.out.printf("Deposit successful. New balance: ₹%.2f%n", account.getBalance());
    }

    private static void withdraw() {
        BankAccount account = authenticatedAccount();
        account.executeTransaction(new WithdrawalTransaction(readAmount("Amount to withdraw (₹): ")));
        System.out.printf("Withdrawal successful. New balance: ₹%.2f%n", account.getBalance());
    }

    private static void transfer() {
        BankAccount from = authenticatedAccount();
        System.out.print("Destination account number: ");
        String to = INPUT.nextLine().trim();
        BANK.transfer(from.getAccountNumber(), to, readAmount("Amount to transfer (₹): "));
        System.out.println("Transfer completed successfully.");
    }

    private static void transactionHistory() {
        BankAccount account = authenticatedAccount();
        System.out.println("--- Transaction history for " + account.getAccountNumber() + " ---");
        for (String transaction : account.getTransactions()) System.out.println("• " + transaction);
    }

    private static void listAccounts() {
        if (BANK.getAllAccounts().isEmpty()) {
            System.out.println("No accounts have been created yet.");
            return;
        }
        System.out.printf("%-15s %-25s %s%n", "ACCOUNT NO.", "ACCOUNT HOLDER", "BALANCE");
        for (BankAccount account : BANK.getAllAccounts()) {
            System.out.printf("%-15s %-25s ₹%.2f%n", account.getAccountNumber(),
                    account.getHolderName(), account.getBalance());
        }
        System.out.println("Note: This listing is for demonstration; a real system must restrict administrator access.");
    }

    private static double readAmount(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(INPUT.nextLine().trim());
    }
}
