
package model;

import java.util.Date;

public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, String branch,
                          Date dateOpened, Customer customer) {

        super(accountNumber, balance, branch, dateOpened, customer);

        this.interestRate = 0.0005;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    public void applyMonthlyInterest() {

        double interest = calculateInterest();

        setBalance(getBalance() + interest);

        Transaction transaction = new Transaction(
                "T" + java.util.UUID.randomUUID(),
                "Monthly Interest",
                interest,
                new Date()
        );

        getTransactions().add(transaction);

        System.out.println("Monthly interest applied: P" + interest);
    }

    public void withdraw(double amount) {
        System.out.println("Withdrawals are not allowed from Savings Accounts.");
    }
}
