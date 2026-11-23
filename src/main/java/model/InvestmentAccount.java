
package model;

import java.util.Date;

public class InvestmentAccount extends Account implements Withdrawable {

    private double interestRate;
    private double minimumDeposit;

    public InvestmentAccount(String accountNumber, double balance, String branch,
                             Date dateOpened, Customer customer) {

        super(accountNumber, validateMinimumDeposit(balance), branch,
                dateOpened, customer);

        this.interestRate = 0.05;
        this.minimumDeposit = 500.0;
    }

    // Validate the minimum deposit before the account is registered.
    private static double validateMinimumDeposit(double balance) {

        if (balance < 500.0) {
            throw new IllegalArgumentException(
                    "Investment Account requires a minimum opening deposit of P500."
            );
        }

        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMinimumDeposit() {
        return minimumDeposit;
    }

    public void setMinimumDeposit(double minimumDeposit) {
        this.minimumDeposit = minimumDeposit;
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

    @Override
    public void withdraw(double amount) {

        if (amount > 0 && amount <= getBalance()) {

            setBalance(getBalance() - amount);

            Transaction transaction = new Transaction(
                    "T" + java.util.UUID.randomUUID(),
                    "Withdrawal",
                    amount,
                    new Date()
            );

            getTransactions().add(transaction);

            System.out.println("Withdrawal successful.");

        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }
}
