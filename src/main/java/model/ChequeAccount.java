
package model;

import java.util.Date;

public class ChequeAccount extends Account implements Withdrawable {

    private Company company;

    public ChequeAccount(String accountNumber, double balance, String branch,
                         Date dateOpened, Customer customer, Company company) {

        super(accountNumber, balance, branch, dateOpened,
                validateEmploymentAndCompany(customer, company));

        this.company = company;
    }

    private static Customer validateEmploymentAndCompany(
            Customer customer, Company company) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "A customer must be provided."
            );
        }

        if (customer.getWorkplace() == null ||
                customer.getWorkplace().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "A Cheque Account can only be opened by an employed customer."
            );
        }

        if (company == null ||
                company.getCompanyName() == null ||
                company.getCompanyName().trim().isEmpty() ||
                company.getBusinessAddress() == null ||
                company.getBusinessAddress().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Valid company name and business address must be provided."
            );
        }

        if (!customer.getWorkplace().trim().equalsIgnoreCase(
                company.getCompanyName().trim())) {

            throw new IllegalArgumentException(
                    "Company name must match the customer's workplace."
            );
        }

        return customer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        if (company == null ||
                company.getCompanyName() == null ||
                company.getCompanyName().trim().isEmpty() ||
                company.getBusinessAddress() == null ||
                company.getBusinessAddress().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Valid company details must be provided."
            );
        }

        if (!getCustomer().getWorkplace().trim().equalsIgnoreCase(
                company.getCompanyName().trim())) {

            throw new IllegalArgumentException(
                    "Company name must match the customer's workplace."
            );
        }

        this.company = company;
    }

    @Override
    public void withdraw(double amount) {

        if (amount > 0 && amount <= getBalance()) {

            setBalance(getBalance() - amount);

            Transaction transaction = new Transaction(
                    "T" + java.util.UUID.randomUUID(),
                    "Withdrawal",
                    amount,
                    new Date()
            );

            getTransactions().add(transaction);

            System.out.println("Withdrawal successful.");

        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }
}
