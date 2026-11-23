package controller;

import model.Account;
import model.Customer;

import java.util.List;

public class AccountsController {

    public List<Account> getCustomerAccounts(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        return customer.getAccounts();
    }

    public double getTotalBalance(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        return customer.viewBalance();
    }
}