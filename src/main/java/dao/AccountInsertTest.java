
package dao;

import model.Customer;
import model.Company;
import model.ChequeAccount;

import java.sql.SQLException;
import java.util.Date;

public class AccountInsertTest {

    public static void main(String[] args) {

        CustomerDAO customerDAO = new CustomerDAO();
        AccountDAO accountDAO = new AccountDAO();

        try {
            Customer customer = customerDAO.getCustomerById(1);

            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            Company company = new Company(
                    "ABC Company",
                    "REG001",
                    "Francistown, Botswana",
                    "76018634",
                    "TAX001"
            );

            ChequeAccount chequeAccount = new ChequeAccount(
                    "CA_TEST001",
                    1000.00,
                    "Francistown",
                    new Date(),
                    customer,
                    company
            );

            boolean inserted = accountDAO.insertAccount(chequeAccount);

            if (inserted) {
                System.out.println("Cheque account inserted successfully!");
            } else {
                System.out.println("Cheque account insertion failed.");
            }

        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
}
