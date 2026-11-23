
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Account {

    private String accountNumber;
    private double balance;
    private String branch;
    private Date dateOpened;
    private Customer customer;

    private List<Transaction> transactions;

    public Account(String accountNumber, double balance, String branch,
                   Date dateOpened, Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "An account must belong to a customer."
            );
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        this.dateOpened = dateOpened;
        this.customer = customer;

        this.transactions = new ArrayList<>();

        customer.addAccount(this);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;

            Transaction transaction = new Transaction(
                    "T" + java.util.UUID.randomUUID(),
                    "Deposit",
                    amount,
                    new Date()
            );

            transactions.add(transaction);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void deposit(double amount, String reference) {
        if (amount > 0) {
            balance += amount;

            Transaction transaction = new Transaction(
                    "T" + java.util.UUID.randomUUID(),
                    "Deposit - " + reference,
                    amount,
                    new Date()
            );

            transactions.add(transaction);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}
