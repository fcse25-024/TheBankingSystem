
package controller;

import dao.AccountDAO;
import dao.TransactionDAO;
import model.Account;
import model.Transaction;
import model.Withdrawable;

import java.sql.SQLException;
import java.util.List;

public class WithdrawController {

    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public boolean withdraw(Account account, double amount) {

        if (account == null || amount <= 0) {
            return false;
        }

        if (!(account instanceof Withdrawable)) {
            return false;
        }

        if (amount > account.getBalance()) {
            return false;
        }

        double oldBalance = account.getBalance();
        List<Transaction> transactions = account.getTransactions();

        try {

            // Withdraw money from the account
            ((Withdrawable) account).withdraw(amount);

            // Get the transaction created by the withdrawal method
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

            // Save the withdrawal transaction in the database
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

            // Restore the in-memory balance if saving fails
            account.setBalance(oldBalance);

            if (transactions.size() > 0) {
                transactions.remove(transactions.size() - 1);
            }

            System.out.println("Withdrawal could not be saved.");
            e.printStackTrace();

            return false;
        }
    }
}
