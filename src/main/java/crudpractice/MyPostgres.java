package crudpractice;
import java.sql.*;

public class MyPostgres {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user = "myuser";
        String password = "mysecretpassword";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Create table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name TEXT)");

            // Insert
            stmt.executeUpdate("INSERT INTO users (name) VALUES ('Alice')");

            // Read
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
            }

            // Update
            stmt.executeUpdate("UPDATE users SET name='Bob' WHERE name='Alice'");

//            // Delete
//            stmt.executeUpdate("DELETE FROM users WHERE name='Bob'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
