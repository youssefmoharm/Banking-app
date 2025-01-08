import java.util.*;
public class main {
    private static HashMap<Integer, BankAccount> accounts = new HashMap<>();
    private static int accountCounter = 1001; // Starting account number
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Banking Application ===");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Banking Application!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        scanner.nextLine(); // Consume leftover newline
        String name = scanner.nextLine();
        System.out.print("Set a password for the account: ");
        String password = scanner.nextLine();

        BankAccount newAccount = new BankAccount(name, accountCounter++, password);
        accounts.put(newAccount.getAccountNumber(), newAccount);

        System.out.println("Account created successfully!");
        System.out.println("Account Holder: " + newAccount.getAccountHolderName());
        System.out.println("Account Number: " + newAccount.getAccountNumber());
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account != null && account.verifyPassword(password)) {
            System.out.println("Login successful! Welcome, " + account.getAccountHolderName());
            accountMenu(scanner, account);
        } else {
            System.out.println("Invalid account number or password!");
        }
    }

    private static void accountMenu(Scanner scanner, BankAccount account) {
        while (true) {
            System.out.println("\n=== Account Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Apply for Loan");
            System.out.println("7. Repay Loan");
            System.out.println("8. Check Loan Balance");
            System.out.println("9. Calculate Loan Interest");
            System.out.println("10. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    System.out.print("Enter the number of years for interest calculation: ");
                    int years = scanner.nextInt();
                    account.calculateInterest(years);
                    break;
                case 6:
                    System.out.print("Enter loan amount: ");
                    double loanAmount = scanner.nextDouble();
                    account.applyForLoan(loanAmount);
                    break;
                case 7:
                    System.out.print("Enter loan repayment amount: ");
                    double repaymentAmount = scanner.nextDouble();
                    account.repayLoan(repaymentAmount);
                    break;
                case 8:
                    System.out.println("Current Loan Balance: $" + account.getLoanBalance());
                    break;
                case 9:
                    System.out.print("Enter the number of years for loan interest calculation: ");
                    int loanYears = scanner.nextInt();
                    account.calculateLoanInterest(loanYears);
                    break;
                case 10:
                    System.out.println("Logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
