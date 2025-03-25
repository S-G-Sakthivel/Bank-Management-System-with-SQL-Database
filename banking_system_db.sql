CREATE DATABASE BankingSystem;
SHOW DATABASES;
USE BankingSystem;
CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    account_holder VARCHAR(100) NOT NULL,
    balance DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO accounts (account_holder, balance) 
VALUES ('John Doe', 5000.00);
DESC accounts;
select*from accounts;
