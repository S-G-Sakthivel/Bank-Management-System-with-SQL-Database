import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/BankingSystem"; // Ensure DB name is correct
        String user = "root";  
        String password = "Parzival1*";  

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected Successfully!");

            // Create a statement
            Statement stmt = conn.createStatement();
            // Query to fetch accounts
            String sql = "SELECT * FROM accounts";
            ResultSet rs = stmt.executeQuery(sql);

            // Print account details
            while (rs.next()) {
                int id = rs.getInt("account_id");
                String name = rs.getString("account_holder");
                double balance = rs.getDouble("balance");
                System.out.println("ID: " + id + ", Name: " + name + ", Balance: $" + balance);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
