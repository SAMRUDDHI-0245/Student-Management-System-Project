import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        Connection conn = DBConnect.getConnection();
        if (conn != null) {
            System.out.println("Database Connected Successfully!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}
