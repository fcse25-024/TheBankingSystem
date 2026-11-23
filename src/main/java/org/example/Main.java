
package org.example;

import model.Customer;
import model.InvestmentAccount;
import model.ChequeAccount;
import model.SavingsAccount;
import model.Withdrawable;
import model.Company;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== BANKING SYSTEM CORE MODEL TEST =====");

        // 1. Create customer
        Customer customer = new Customer(
                "Romeo",
                "Oabile",
                "Francistown",
                new Date(),
                "ABC Company"
        );

        // 2. Create company
        Company company = new Company(
                "ABC Company",
                "REG001",
                "Francistown, Botswana",
                "71234567",
                "TAX001"
        );

        // 3. Create three account types
        InvestmentAccount investment = new InvestmentAccount(
                "IA001", 1000.0, "Francistown", new Date(), customer
        );

        ChequeAccount cheque = new ChequeAccount(
                "CA001", 1500.0, "Francistown",
                new Date(), customer, company
        );

        SavingsAccount savings = new SavingsAccount(
                "SA001", 2000.0, "Francistown", new Date(), customer
        );

        System.out.println("\n--- Accounts Created ---");
        System.out.println("Investment Account: " + investment.getAccountNumber());
        System.out.println("Cheque Account: " + cheque.getAccountNumber());
        System.out.println("Savings Account: " + savings.getAccountNumber());

        // 4. Test deposits
        System.out.println("\n--- Deposit Tests ---");
        investment.deposit(200.0);
        cheque.deposit(300.0);
        savings.deposit(100.0);

        // 5. Test polymorphic withdrawals
        System.out.println("\n--- Withdrawal Tests ---");

        Withdrawable[] withdrawableAccounts = {
                investment, cheque
        };

        for (Withdrawable account : withdrawableAccounts) {
            account.withdraw(100.0);
        }

        // 6. Test Savings Account withdrawal restriction
        System.out.println("\n--- Savings Withdrawal Restriction ---");
        savings.withdraw(100.0);

        // 7. Apply monthly interest
        System.out.println("\n--- Monthly Interest ---");
        investment.applyMonthlyInterest();
        savings.applyMonthlyInterest();

        // 8. Display account balances
        System.out.println("\n--- Account Balances ---");
        System.out.println("Investment: P" + investment.getBalance());
        System.out.println("Savings: P" + savings.getBalance());
        System.out.println("Cheque: P" + cheque.getBalance());

        // 9. Display company details
        System.out.println("\n--- Cheque Account Company ---");
        System.out.println("Company: " + cheque.getCompany().getCompanyName());
        System.out.println("Address: " + cheque.getCompany().getBusinessAddress());

        // 10. Display total customer balance
        System.out.println("\n--- Customer Details ---");
        System.out.println(customer.getCustomerDetails());
        System.out.println("Total Balance: P" + customer.viewBalance());
        System.out.println("Number of Accounts: " + customer.getAccounts().size());

        // 11. Display transaction history
        customer.viewTransactionHistory();

        // 12. Test Investment Account minimum deposit validation
        System.out.println("\n--- Investment Minimum Deposit Test ---");

        Customer testCustomer = new Customer(
                "Test",
                "Customer",
                "Francistown",
                new Date(),
                "ABC Company"
        );

        try {
            new InvestmentAccount(
                    "IA999", 400.0, "Francistown",
                    new Date(), testCustomer
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Account rejected: " + e.getMessage());
        }

        System.out.println("Registered accounts: "
                + testCustomer.getAccounts().size());

        // 13. Test Cheque Account company validation
        System.out.println("\n--- Cheque Company Validation Test ---");

        Company invalidCompany = new Company(
                "XYZ Company",
                "REG002",
                "Gaborone, Botswana",
                "71234567",
                "TAX002"
        );

        try {
            new ChequeAccount(
                    "CA999", 500.0, "Francistown",
                    new Date(), testCustomer, invalidCompany
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Account rejected: " + e.getMessage());
        }

        System.out.println("Registered accounts after rejection: "
                + testCustomer.getAccounts().size());

        System.out.println("\n===== END OF CORE MODEL TEST =====");
    }
}
