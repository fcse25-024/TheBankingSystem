package controller;

import model.Account;
import model.Customer;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryController {

    public List<Transaction> getTransactionHistory(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        List<Transaction> allTransactions = new ArrayList<>();

        for (Account account : customer.getAccounts()) {
            allTransactions.addAll(account.getTransactions());
        }

        return allTransactions;
    }
}