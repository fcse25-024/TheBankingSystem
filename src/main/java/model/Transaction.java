
package model;

import java.util.Date;

public class Transaction {

    private String transactionID;
    private String transactionType;
    private double amount;
    private Date transactionDate;

    public Transaction(String transactionID, String transactionType,
                       double amount, Date transactionDate) {

        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void recordTransaction() {
        System.out.println("Transaction recorded successfully.");
    }

    public String getTransactionDetails() {
        return transactionID + " - " + transactionType + " - " + amount;
    }
}
