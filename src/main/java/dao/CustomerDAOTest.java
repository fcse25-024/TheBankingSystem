
package dao;

import model.Customer;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAOTest {

    public static void main(String[] args) {

        CustomerDAO customerDAO = new CustomerDAO();

        try {
            List<Customer> customers = customerDAO.getAllCustomers();

            System.out.println("===== CUSTOMERS FROM DATABASE =====");

            for (Customer customer : customers) {
                System.out.println(
                        "ID: " + customer.getCustomerID()
                                + " | Name: " + customer.getName()
                                + " " + customer.getSurname()
                );
            }

            System.out.println("Total customers: " + customers.size());

        } catch (SQLException e) {
            System.out.println("Could not retrieve customers.");
            e.printStackTrace();
        }
    }
}
