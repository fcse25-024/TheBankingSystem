
CREATE DATABASE IF NOT EXISTS banking_system;

USE banking_system;

-- =====================================
-- 1. CUSTOMER TABLE
-- =====================================

CREATE TABLE Customer (
                          customerID INT AUTO_INCREMENT PRIMARY KEY,
                          firstName VARCHAR(50) NOT NULL,
                          lastName VARCHAR(50) NOT NULL,
                          address VARCHAR(150) NOT NULL,
                          dateOfBirth DATE NOT NULL,
                          workplace VARCHAR(100)
) ENGINE=InnoDB;


-- =====================================
-- 2. COMPANY TABLE
-- =====================================

CREATE TABLE Company (
                         companyID INT AUTO_INCREMENT PRIMARY KEY,
                         companyName VARCHAR(100) NOT NULL,
                         registrationNumber VARCHAR(50),
                         businessAddress VARCHAR(150) NOT NULL,
                         contactNumber VARCHAR(20),
                         taxIdentificationNumber VARCHAR(50)
) ENGINE=InnoDB;


-- =====================================
-- 3. ACCOUNT TABLE
-- =====================================

CREATE TABLE Account (
                         accountID INT AUTO_INCREMENT PRIMARY KEY,
                         accountNumber VARCHAR(30) NOT NULL UNIQUE,
                         accountType VARCHAR(20) NOT NULL,
                         balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
                         branch VARCHAR(100) NOT NULL,
                         dateOpened DATE NOT NULL,
                         customerID INT NOT NULL,
                         companyID INT NULL,

                         CONSTRAINT fk_accout_customer
                             FOREIGN KEY (customerID)
                                 REFERENCES Customer(customerID)
                                 ON DELETE RESTRICT
                                 ON UPDATE RESTRICT,

                         CONSTRAINT fk_account_company
                             FOREIGN KEY (companyID)
                                 REFERENCES Company(companyID)
                                 ON DELETE RESTRICT
                                 ON UPDATE RESTRICT
) ENGINE=InnoDB;


-- =====================================
-- 4. TRANSACTION TABLE
-- =====================================

CREATE TABLE `Transaction` (
                               transactionID INT AUTO_INCREMENT PRIMARY KEY,
                               transactionType VARCHAR(30) NOT NULL,
                               amount DECIMAL(15,2) NOT NULL,
                               transactionDate DATETIME NOT NULL,
                               accountID INT NOT NULL,
                               reference VARCHAR(100),

                               CONSTRAINT fk_transaction_account
                                   FOREIGN KEY (accountID)
                                       REFERENCES Account(accountID)
                                       ON DELETE RESTRICT
                                       ON UPDATE RESTRICT
) ENGINE=InnoDB;
