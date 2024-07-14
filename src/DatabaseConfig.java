import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final  String url ="jdbc:mysql://localhost:3306/BANK";
    private static final String username="root";
    private static final String password="Admin@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
