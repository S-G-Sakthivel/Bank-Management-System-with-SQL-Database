# **Bank Management System**  

## **Overview**  
The **Bank Management System** is a Java-based console application that allows users to manage bank accounts. It supports basic operations such as account creation, deposits, withdrawals, balance checks, and account details viewing.  

## **Features**  
- ✅ Create a new bank account with a minimum deposit.  
- 💰 Deposit money into an account.  
- 💸 Withdraw money from an account (if funds are sufficient).  
- 📊 Check the current account balance.  
- 🆔 View account details by providing an account ID and name.  

## **Technologies Used**  
- **Java** (JDK 22)  
- **MySQL** (JDBC for database connectivity)  

## **Setup Instructions**  
1. **Clone the repository** (if using version control):  
   ```sh
   git clone https://github.com/your-repo/bank-management.git](https://github.com/S-G-Sakthivel/Bank-Management-System-with-SQL-Database
   cd bank-management
   ```
2. **Set up the MySQL database:**  
   - Create a database named `BankingSystem`.  
   - Create a table `accounts` with the following structure:  
     ```sql
     CREATE TABLE accounts (
         account_id INT AUTO_INCREMENT PRIMARY KEY,
         account_holder VARCHAR(255) NOT NULL,
         balance DOUBLE NOT NULL CHECK (balance >= 0)
     );
     ```
3. **Update database credentials** in `BankOperations.java`:  
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/BankingSystem";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";
   ```
4. **Compile and run the program:**  
   ```sh
   javac BankOperations.java
   java BankOperations
   ```

## **Usage**  
1️⃣ Run the program.  
2️⃣ Choose an option from the menu.  
3️⃣ Follow the prompts to perform banking operations.  
