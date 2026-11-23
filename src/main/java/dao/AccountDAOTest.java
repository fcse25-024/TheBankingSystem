
package dao;

import model.Account;
import java.sql.SQLException;
import java.util.List;

public class AccountDAOTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        try {
            List<Account> accounts = accountDAO.getAllAccounts();

            System.out.println("===== ACCOUNTS FROM DATABASE =====");

            for (Account account : accounts) {
                System.out.println(
                        account.getAccountNumber()
                                + " | Balance: P" + account.getBalance()
                );
            }

            System.out.println("Total accounts: " + accounts.size());

        } catch (SQLException e) {
            System.out.println("Could not retrieve accounts.");
            e.printStackTrace();
        }
    }
}
