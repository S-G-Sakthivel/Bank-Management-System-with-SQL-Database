
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

public class BankOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/BankingSystem";
    private static final String USER = "root";
    private static final String PASSWORD = "Parzival1*";

    public static void createAccount(String holderName, double initialBalance) {
        if (initialBalance < 1000) {
            System.out.println("Error: Minimum deposit should be ₹1000 to create an account.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO accounts (account_holder, balance) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, holderName);
            stmt.setDouble(2, initialBalance);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int accountId = generatedKeys.getInt(1);
                System.out.println("Account created successfully for " + holderName + "! Your Account ID is: " + accountId);
            } else {
                System.out.println("Error: Account ID could not be retrieved.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void depositMoney(int accountId, double amount) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Deposited ₹" + amount + " successfully!");
            } else {
                System.out.println("Account not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void withdrawMoney(int accountId, double amount) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String checkSql = "SELECT balance FROM accounts WHERE account_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, accountId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                if (currentBalance >= amount) {
                    String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setDouble(1, amount);
                    stmt.setInt(2, accountId);
                    stmt.executeUpdate();
                    System.out.println("Withdrawn ₹" + amount + " successfully!");
                } else {
                    System.out.println("Insufficient balance!");
                }
            } else {
                System.out.println("Account not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void checkBalance(int accountId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT balance FROM accounts WHERE account_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                System.out.println("Current Balance: ₹" + balance);
            } else {
                System.out.println("Account not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAccountDetails(String name, int accountId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM accounts WHERE account_id = ? AND account_holder = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            stmt.setString(2, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\nAccount Details:");
                System.out.println("Account ID: " + rs.getInt("account_id"));
                System.out.println("Account Holder: " + rs.getString("account_holder"));
                System.out.println("Balance: ₹" + rs.getDouble("balance"));
            } else {
                System.out.println("No account found with the provided details.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Banking System!");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); 
                    System.out.print("Enter Account Holder Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Initial Deposit Amount: ");
                    double balance = scanner.nextDouble();
                    createAccount(name, balance);
                    break;
                case 2:
                    System.out.print("Enter Account ID: ");
                    int depositId = scanner.nextInt();
                    System.out.print("Enter Amount to Deposit: ");
                    double depositAmount = scanner.nextDouble();
                    depositMoney(depositId, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter Account ID: ");
                    int withdrawId = scanner.nextInt();
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdrawMoney(withdrawId, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter Account ID: ");
                    int checkId = scanner.nextInt();
                    checkBalance(checkId);
                    break;
                case 5:
                    scanner.nextLine(); 
                    System.out.print("Enter Account Holder Name: ");
                    String accName = scanner.nextLine();
                    System.out.print("Enter Account ID: ");
                    int accId = scanner.nextInt();
                    viewAccountDetails(accName, accId);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}

