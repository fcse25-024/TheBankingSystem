
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

public class Customer {

    private int customerID;
    private String name;
    private String surname;
    private String address;
    private Date DOB;
    private String workplace;

    private List<Account> accounts;

    // Constructor for a new customer
    public Customer(String name, String surname, String address,
                    Date DOB, String workplace) {

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.DOB = DOB;
        this.workplace = workplace;

        this.accounts = new ArrayList<>();
    }

    // Constructor for a customer retrieved from the database
    public Customer(int customerID, String name, String surname,
                    String address, Date DOB, String workplace) {

        this.customerID = customerID;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.DOB = DOB;
        this.workplace = workplace;

        this.accounts = new ArrayList<>();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void addAccount(Account account) {
        if (account != null && !accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public String getCustomerDetails() {
        return name + " " + surname + ", " + address;
    }

    public double viewBalance() {
        double totalBalance = 0;

        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }

        return totalBalance;
    }

    public void viewTransactionHistory() {
        System.out.println("\n===== CUSTOMER TRANSACTION HISTORY =====");

        if (accounts.isEmpty()) {
            System.out.println("This customer has no accounts.");
            return;
        }

        for (Account account : accounts) {
            System.out.println("\nAccount Number: " + account.getAccountNumber());

            if (account.getTransactions().isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                for (Transaction transaction : account.getTransactions()) {
                    System.out.println(transaction.getTransactionDetails());
                }
            }
        }

        System.out.println("\n===== END OF TRANSACTION HISTORY =====");
    }

    public void closeAccount() {
        System.out.println("Account closure requested.");
    }
}
