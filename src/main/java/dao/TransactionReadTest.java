
package dao;

import model.Transaction;
import java.sql.SQLException;
import java.util.List;

public class TransactionReadTest {

    public static void main(String[] args) {

        TransactionDAO transactionDAO = new TransactionDAO();

        try {
            List<Transaction> transactions =
                    transactionDAO.getTransactionsByAccount("SA_TEST001");

            System.out.println("===== TRANSACTIONS FROM DATABASE =====");

            for (Transaction transaction : transactions) {
                System.out.println(
                        transaction.getTransactionID()
                                + " | "
                                + transaction.getTransactionType()
                                + " | P"
                                + transaction.getAmount()
                );
            }

            System.out.println("Total transactions: " + transactions.size());

        } catch (SQLException e) {
            System.out.println("Could not retrieve transactions.");
            e.printStackTrace();
        }
    }
}
