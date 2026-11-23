
package app;

import dao.AccountDAO;
import dao.CustomerDAO;
import model.Customer;

import java.sql.SQLException;

public class BankingData {

    public static Customer customer;

    static {

        try {

            CustomerDAO customerDAO = new CustomerDAO();
            AccountDAO accountDAO = new AccountDAO();

            // Load Romeo's customer record from the database
            customer = customerDAO.getCustomerById(1);

            if (customer == null) {
                throw new SQLException("Customer not found in database.");
            }

            // Load accounts into the same Customer object
            accountDAO.getAccountsByCustomer(customer);

            System.out.println("Banking data loaded successfully!");

            System.out.println("Customer: "
                    + customer.getName() + " "
                    + customer.getSurname());

            System.out.println("Number of accounts: "
                    + customer.getAccounts().size());

        } catch (SQLException e) {

            throw new ExceptionInInitializerError(e);
        }
    }
}
