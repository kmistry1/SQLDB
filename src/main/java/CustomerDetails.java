import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDetails {

    public static void main(String[] args) {
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            rs = DriverManager.getConnection("jdbc:mysql://localhost:3306/ITCS6112", "root", "password")
                    .createStatement().executeQuery("SELECT * FROM ITCS6112.CustomerDetails");
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
        return "************ Customer Detail [" + getTableData(rs, "PersonID") + "] ************" + "\n" + "Full Name: " + getTableData(rs, "FirstName") + " " + getTableData(rs, "LastName") + "\n" + "Address: " + getTableData(rs, "Address") + "\n" + getTableData(rs, "City") + ", " + getTableData(rs, "State") + " " + getTableData(rs, "ZipCode") + " " + getTableData(rs, "Country") + "\n";
    }

    private static String getTableData(ResultSet rs, String personID) throws SQLException {
        return rs.getString(personID);
    }
}
