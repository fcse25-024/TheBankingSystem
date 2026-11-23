
package dao;

import database.DatabaseConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDAO {

    private final CustomerDAO customerDAO = new CustomerDAO();

    // CREATE: Insert an account
    public boolean insertAccount(Account account) throws SQLException {

        String sql = "INSERT INTO Account "
                + "(accountNumber, accountType, balance, branch, dateOpened, customerID, companyID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, account.getAccountNumber());
            statement.setString(2, getAccountType(account));
            statement.setDouble(3, account.getBalance());
            statement.setString(4, account.getBranch());

            if (account.getDateOpened() != null) {
                statement.setDate(5,
                        new java.sql.Date(account.getDateOpened().getTime()));
            } else {
                statement.setNull(5, java.sql.Types.DATE);
            }

            statement.setInt(6, account.getCustomer().getCustomerID());

            if (account instanceof ChequeAccount) {
                ChequeAccount chequeAccount = (ChequeAccount) account;

                int companyID = getCompanyID(
                        chequeAccount.getCompany().getCompanyName()
                );

                statement.setInt(7, companyID);
            } else {
                statement.setNull(7, java.sql.Types.INTEGER);
            }

            return statement.executeUpdate() > 0;
        }
    }

    // READ: Retrieve all accounts
    public List<Account> getAllAccounts() throws SQLException {

        List<Account> accounts = new ArrayList<>();
        Map<Integer, Customer> customers = new HashMap<>();

        String sql = "SELECT * FROM Account";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                accounts.add(createAccountFromResult(result, customers));
            }
        }

        return accounts;
    }

    // READ: Retrieve accounts belonging to one customer ID
    public List<Account> getAccountsByCustomer(int customerID)
            throws SQLException {

        List<Account> accounts = new ArrayList<>();
        Map<Integer, Customer> customers = new HashMap<>();

        String sql = "SELECT * FROM Account WHERE customerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    accounts.add(createAccountFromResult(result, customers));
                }
            }
        }

        return accounts;
    }

    // READ: Load accounts into an existing Customer object
    public List<Account> getAccountsByCustomer(Customer customer)
            throws SQLException {

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        List<Account> accounts = new ArrayList<>();

        String sql = "SELECT * FROM Account WHERE customerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customer.getCustomerID());

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    accounts.add(createAccountFromResult(result, customer));
                }
            }
        }

        return accounts;
    }

    // UPDATE: Update an account balance
    public boolean updateAccountBalance(String accountNumber, double balance)
            throws SQLException {

        String sql = "UPDATE Account SET balance = ? WHERE accountNumber = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, balance);
            statement.setString(2, accountNumber);

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE: Delete an account
    public boolean deleteAccount(String accountNumber) throws SQLException {

        String sql = "DELETE FROM Account WHERE accountNumber = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            return statement.executeUpdate() > 0;
        }
    }

    // Determine the account type
    private String getAccountType(Account account) {

        if (account instanceof SavingsAccount) {
            return "Savings";
        }

        if (account instanceof InvestmentAccount) {
            return "Investment";
        }

        if (account instanceof ChequeAccount) {
            return "Cheque";
        }

        throw new IllegalArgumentException("Unknown account type.");
    }

    // Retrieve company ID using company name
    private int getCompanyID(String companyName) throws SQLException {

        String sql = "SELECT companyID FROM Company WHERE companyName = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, companyName);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return result.getInt("companyID");
                }
            }
        }

        throw new SQLException("Company not found: " + companyName);
    }

    // Reconstruct an account using cached Customer objects
    private Account createAccountFromResult(
            ResultSet result,
            Map<Integer, Customer> customers) throws SQLException {

        int customerID = result.getInt("customerID");

        Customer customer = customers.get(customerID);

        if (customer == null) {
            customer = customerDAO.getCustomerById(customerID);

            if (customer == null) {
                throw new SQLException("Customer not found for this account.");
            }

            customers.put(customerID, customer);
        }

        return createAccountFromResult(result, customer);
    }

    // Reconstruct an account using a supplied Customer object
    private Account createAccountFromResult(
            ResultSet result,
            Customer customer) throws SQLException {

        String accountNumber = result.getString("accountNumber");
        double balance = result.getDouble("balance");
        String branch = result.getString("branch");
        Date dateOpened = result.getDate("dateOpened");
        String accountType = result.getString("accountType");

        switch (accountType) {

            case "Savings":
                return new SavingsAccount(
                        accountNumber, balance, branch, dateOpened, customer
                );

            case "Investment":
                return new InvestmentAccount(
                        accountNumber, balance, branch, dateOpened, customer
                );

            case "Cheque":

                int companyID = result.getInt("companyID");

                Company company = getCompanyById(companyID);

                return new ChequeAccount(
                        accountNumber, balance, branch,
                        dateOpened, customer, company
                );

            default:
                throw new SQLException("Unknown account type: " + accountType);
        }
    }

    // Retrieve company details
    private Company getCompanyById(int companyID) throws SQLException {

        String sql = "SELECT * FROM Company WHERE companyID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, companyID);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return new Company(
                            result.getString("companyName"),
                            result.getString("registrationNumber"),
                            result.getString("businessAddress"),
                            result.getString("contactNumber"),
                            result.getString("taxIdentificationNumber")
                    );
                }
            }
        }

        throw new SQLException("Company not found.");
    }
}
