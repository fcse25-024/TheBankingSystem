
package dao;

import database.DatabaseConnection;
import model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // CREATE: Save a transaction
    public boolean insertTransaction(Transaction transaction,
                                     String accountNumber)
            throws SQLException {

        String sql = "INSERT INTO `Transaction` "
                + "(transactionType, amount, transactionDate, accountID, reference) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            String accountSQL =
                    "SELECT accountID FROM Account WHERE accountNumber = ?";

            int accountID;

            try (PreparedStatement accountStatement =
                         connection.prepareStatement(accountSQL)) {

                accountStatement.setString(1, accountNumber);

                try (ResultSet result = accountStatement.executeQuery()) {

                    if (!result.next()) {
                        throw new SQLException("Account not found: " + accountNumber);
                    }

                    accountID = result.getInt("accountID");
                }
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, transaction.getTransactionType());
                statement.setDouble(2, transaction.getAmount());

                if (transaction.getTransactionDate() != null) {
                    statement.setTimestamp(
                            3,
                            new java.sql.Timestamp(
                                    transaction.getTransactionDate().getTime()
                            )
                    );
                } else {
                    statement.setNull(3, java.sql.Types.TIMESTAMP);
                }

                statement.setInt(4, accountID);
                statement.setString(5, transaction.getTransactionID());

                return statement.executeUpdate() > 0;
            }
        }
    }

    // READ: Retrieve transactions for one account
    public List<Transaction> getTransactionsByAccount(
            String accountNumber) throws SQLException {

        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT t.reference, t.transactionType, "
                + "t.amount, t.transactionDate "
                + "FROM `Transaction` t "
                + "JOIN Account a ON t.accountID = a.accountID "
                + "WHERE a.accountNumber = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    Transaction transaction = new Transaction(
                            result.getString("reference"),
                            result.getString("transactionType"),
                            result.getDouble("amount"),
                            result.getTimestamp("transactionDate")
                    );

                    transactions.add(transaction);
                }
            }
        }

        return transactions;
    }

    // UPDATE: Modify transaction details
    public boolean updateTransaction(String transactionID,
                                     String transactionType,
                                     double amount) throws SQLException {

        String sql = "UPDATE `Transaction` "
                + "SET transactionType = ?, amount = ? "
                + "WHERE reference = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transactionType);
            statement.setDouble(2, amount);
            statement.setString(3, transactionID);

            return statement.executeUpdate() > 0;
        }
    }

    // DELETE: Remove a transaction
    public boolean deleteTransaction(String transactionID)
            throws SQLException {

        String sql = "DELETE FROM `Transaction` WHERE reference = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transactionID);

            return statement.executeUpdate() > 0;
        }
    }
}
