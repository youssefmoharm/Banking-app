import java.util.*;

public class BankAccount {
    private String accountHolderName;
    private int accountNumber;
    private String password;
    private double balance;
    private double loanBalance; // Loan amount taken by the account holder
    private List<String> transactionHistory;
    private static final double ANNUAL_INTEREST_RATE = 2.5; // 2.5% annual interest
    private static final double LOAN_INTEREST_RATE = 5.0; // 5% annual loan interest

    public BankAccount(String accountHolderName, int accountNumber, String password) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = 0.0;
        this.loanBalance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public double getBalance() {
        return balance;
    }

    public double getLoanBalance() {
        return loanBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Successfully withdrew $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    public void applyForLoan(double amount) {
        if (amount > 0) {
            loanBalance += amount;
            transactionHistory.add("Loan taken: $" + amount);
            System.out.println("Loan of $" + amount + " granted.");
        } else {
            System.out.println("Invalid loan amount!");
        }
    }

    public void repayLoan(double amount) {
        if (amount > 0 && amount <= balance) {
            if (amount >= loanBalance) {
                balance -= loanBalance;
                transactionHistory.add("Loan repaid: $" + loanBalance);
                loanBalance = 0.0;
                System.out.println("Loan fully repaid!");
            } else {
                loanBalance -= amount;
                balance -= amount;
                transactionHistory.add("Loan repayment: $" + amount);
                System.out.println("Loan partially repaid. Remaining loan balance: $" + loanBalance);
            }
        } else if (amount > balance) {
            System.out.println("Insufficient balance for loan repayment!");
        } else {
            System.out.println("Invalid repayment amount!");
        }
    }

    public void calculateInterest(int years) {
        if (years > 0) {
            double interest = (balance * ANNUAL_INTEREST_RATE * years) / 100;
            balance += interest;
            transactionHistory.add("Interest earned for " + years + " years: $" + interest);
            System.out.println("Interest of $" + interest + " added for " + years + " years.");
        } else {
            System.out.println("Invalid number of years for interest calculation.");
        }
    }

    public void calculateLoanInterest(int years) {
        if (years > 0) {
            double interest = (loanBalance * LOAN_INTEREST_RATE * years) / 100;
            loanBalance += interest;
            transactionHistory.add("Loan interest added for " + years + " years: $" + interest);
            System.out.println("Loan interest of $" + interest + " added for " + years + " years.");
        } else {
            System.out.println("Invalid number of years for loan interest calculation.");
        }
    }

    public void showTransactionHistory() {
        System.out.println("\nTransaction History for Account: " + accountNumber);
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}
