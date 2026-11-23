
package dao;

import java.sql.SQLException;

public class TransactionUpdateTest {

    public static void main(String[] args) {

        TransactionDAO transactionDAO = new TransactionDAO();

        try {
            boolean updated = transactionDAO.updateTransaction(
                    "TXN001",
                    "Deposit",
                    750.00
            );

            if (updated) {
                System.out.println("Transaction updated successfully!");
            } else {
                System.out.println("Transaction not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
}
