import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection connection = null;
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
