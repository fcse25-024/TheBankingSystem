
package dao;

import java.sql.SQLException;

public class TransactionDeleteTest {

    public static void main(String[] args) {

        TransactionDAO transactionDAO = new TransactionDAO();

        try {
            boolean deleted =
                    transactionDAO.deleteTransaction("TXN001");

            if (deleted) {
                System.out.println("Transaction deleted successfully!");
            } else {
                System.out.println("Transaction not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
}
