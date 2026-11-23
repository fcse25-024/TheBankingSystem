
package controller;

import dao.AccountDAO;
import dao.TransactionDAO;
import model.Account;
import model.Transaction;

import java.sql.SQLException;
import java.util.List;

public class DepositController {

    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public boolean deposit(Account account, double amount) {

        if (account == null || amount <= 0) {
            return false;
        }

        double oldBalance = account.getBalance();
        List<Transaction> transactions = account.getTransactions();

        try {

            // Deposit money into the account
            account.deposit(amount);

            // Get the transaction created by the deposit method
            Transaction transaction =
                    transactions.get(transactions.size() - 1);

            // Save the updated balance in the database
            boolean balanceSaved = accountDAO.updateAccountBalance(
                    account.getAccountNumber(),
                    account.getBalance()
            );

            if (!balanceSaved) {
                account.setBalance(oldBalance);
                transactions.remove(transaction);
                return false;
            }

            // Save the transaction in the database
            boolean transactionSaved = transactionDAO.insertTransaction(
                    transaction,
                    account.getAccountNumber()
            );

            if (!transactionSaved) {

                // Restore the previous balance in the database
                accountDAO.updateAccountBalance(
                        account.getAccountNumber(),
                        oldBalance
                );

                account.setBalance(oldBalance);
                transactions.remove(transaction);

                return false;
            }

            return true;

        } catch (SQLException e) {

            // Restore the in-memory account if saving fails
            account.setBalance(oldBalance);

            if (transactions.size() > 0) {
                transactions.remove(transactions.size() - 1);
            }

            System.out.println("Deposit could not be saved.");
            e.printStackTrace();

            return false;
        }
    }
}
