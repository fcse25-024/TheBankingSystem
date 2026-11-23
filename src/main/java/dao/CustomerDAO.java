
package dao;

import database.DatabaseConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // CREATE
    public int insertCustomer(Customer customer) throws SQLException {

        String sql = "INSERT INTO Customer "
                + "(firstName, lastName, address, dateOfBirth, workplace) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getSurname());
            statement.setString(3, customer.getAddress());

            if (customer.getDOB() != null) {
                statement.setDate(4,
                        new java.sql.Date(customer.getDOB().getTime()));
            } else {
                statement.setNull(4, java.sql.Types.DATE);
            }

            statement.setString(5, customer.getWorkplace());

            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    int customerID = keys.getInt(1);
                    customer.setCustomerID(customerID);
                    return customerID;
                }
            }
        }

        throw new SQLException("Customer insertion failed.");
    }

    // READ: Retrieve all customers
    public List<Customer> getAllCustomers() throws SQLException {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM Customer";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Customer customer = new Customer(
                        result.getInt("customerID"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("address"),
                        result.getDate("dateOfBirth"),
                        result.getString("workplace")
                );

                customers.add(customer);
            }
        }

        return customers;
    }

    // READ: Retrieve one customer by ID
    public Customer getCustomerById(int customerID) throws SQLException {

        String sql = "SELECT * FROM Customer WHERE customerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    return new Customer(
                            result.getInt("customerID"),
                            result.getString("firstName"),
                            result.getString("lastName"),
                            result.getString("address"),
                            result.getDate("dateOfBirth"),
                            result.getString("workplace")
                    );
                }
            }
        }

        return null;
    }

    // UPDATE
    public boolean updateCustomer(int customerID, Customer customer)
            throws SQLException {

        String sql = "UPDATE Customer SET firstName = ?, lastName = ?, "
                + "address = ?, dateOfBirth = ?, workplace = ? "
                + "WHERE customerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getSurname());
            statement.setString(3, customer.getAddress());

            if (customer.getDOB() != null) {
                statement.setDate(4,
                        new java.sql.Date(customer.getDOB().getTime()));
            } else {
                statement.setNull(4, java.sql.Types.DATE);
            }

            statement.setString(5, customer.getWorkplace());
            statement.setInt(6, customerID);

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deleteCustomer(int customerID) throws SQLException {

        String sql = "DELETE FROM Customer WHERE customerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);

            return statement.executeUpdate() > 0;
        }
    }
}
