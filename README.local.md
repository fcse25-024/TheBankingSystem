
# The Banking System

## Project Overview
The Banking System is a Java-based banking application developed as part of the Object-Oriented Analysis and Design (OOAD) assignment.

The application allows customers to manage different types of bank accounts, perform deposits and withdrawals where permitted, and view transaction history.

## Account Types

The system supports three account types:

- **Savings Account:** Allows deposits and earns monthly interest of 0.05%. Withdrawals are not permitted.
- **Investment Account:** Allows deposits and withdrawals and earns monthly interest of 5%. A minimum opening deposit of BWP 500 is required.
- **Cheque Account:** Allows deposits and withdrawals. It is available only to employed customers and requires company details.

## Technologies Used

- Java
- JavaFX
- Maven
- JDBC
- MariaDB (MySQL-compatible)
- XAMPP
- IntelliJ IDEA
- Git and GitHub

## Project Structure

The project is organised into the following packages:

- `app` – Application entry point and shared application data.
- `model` – Core banking classes and business logic.
- `view` – JavaFX user interface screens.
- `controller` – Handles communication between the views and model.
- `dao` – Database operations and CRUD functionality.
- `database` – Database connection and SQL schema.

## Database Setup

The application uses a MariaDB database managed through XAMPP.

1. Install and open XAMPP.
2. Start Apache and MySQL.
3. Open phpMyAdmin at `http://localhost/phpmyadmin`.
4. Open the Import tab.
5. Import the SQL file located at `database/banking_system.sql`.
6. Ensure that the database is named `banking_system`.

The SQL file creates the database and the required tables:

- Customer
- Company
- Account
- Transaction

**Important:** The SQL file creates the database structure. Sample records must also be inserted into the database before running the application.

## Database Connection

The database connection is configured in:

`database/DatabaseConnection.java`

The default connection settings are:

- Database: `banking_system`
- Host: `localhost`
- Port: `3306`
- Username: `root`
- Password: Blank

If your MariaDB installation uses a different username or password, update the connection settings accordingly.

## Running the Application

1. Install Java and IntelliJ IDEA.
2. Install and configure Maven.
3. Open the project in IntelliJ IDEA.
4. Ensure that XAMPP Apache and MySQL are running.
5. Ensure that the database has been created and populated with the required records.
6. Run the main application class:

`app.BankingApplication`

Alternatively, run the following command from the project directory on a desktop environment that supports JavaFX:

```bash
mvn javafx:run
```

## Login Details

The current demonstration login credentials are:

- Username: `admin`
- Password: `1234`

## Main Application Features

- Customer and account management.
- Multiple accounts per customer.
- Deposits into supported account types.
- Withdrawals from Investment and Cheque accounts.
- Monthly interest calculations.
- Transaction recording.
- Transaction history.
- Database connectivity using JDBC.

## Author

Developed as part of the Bachelor of Sciences (Honours) in Computer Systems Engineering programme.
