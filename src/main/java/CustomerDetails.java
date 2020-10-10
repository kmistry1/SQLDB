import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDetails {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ITCS6112";
    static final String USER = "root";
    static final String PASS = "password";
    static final String sqlQuery = "SELECT * FROM ITCS6112.CustomerDetails";


    public static void main(String[] args) {
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            rs = DriverManager.getConnection(DB_URL, USER, PASS)
                    .createStatement().executeQuery(sqlQuery);
            while (rs.next()) {
                printoutData(rs);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new Error("Error", e);
        } finally {
            try {
                rs.getStatement().getConnection().close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printoutData(ResultSet rs) throws SQLException {
        System.out.println(toString(rs));
    }

    private static String toString(ResultSet rs) throws SQLException {
        return "************ Customer Detail ["
                + getTableData(rs, "PersonID")
                + "] ************" + "\n" + "Full Name: "
                + getTableData(rs, "FirstName")
                + " " + getTableData(rs, "LastName")
                + "\n" + "Address: " + getTableData(rs, "Address")
                + "\n" + getTableData(rs, "City") + ", "
                + getTableData(rs, "State") + " "
                + getTableData(rs, "ZipCode") + " "
                + getTableData(rs, "Country") + "\n";
    }

    private static String getTableData(ResultSet rs, String personID) throws SQLException {
        return rs.getString(personID);
    }
}
