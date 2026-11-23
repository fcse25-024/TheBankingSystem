
package dao;

import model.Transaction;

import java.sql.SQLException;
import java.util.Date;

public class TransactionInsertTest {

    public static void main(String[] args) {

        TransactionDAO transactionDAO = new TransactionDAO();

        Transaction transaction = new Transaction(
                "TXN001",
                "Deposit",
                500.00,
                new Date()
        );

        try {
            boolean inserted = transactionDAO.insertTransaction(
                    transaction,
                    "SA_TEST001"
            );

            if (inserted) {
                System.out.println("Transaction inserted successfully!");
            } else {
                System.out.println("Transaction insertion failed.");
            }

        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
}
