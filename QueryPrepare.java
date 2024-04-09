import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class QueryPrepare {

    // this class will take care of the prepared statement stuff.
    // this class might not even be needed.

    private static PreparedStatement ps;

    public QueryPrepare() {

    }

    /**
     * Queries the database and prints the results.
     * 
     * @param conn a connection object
     * @param sql  a SQL statement that returns rows:
     *             this query is written with the Statement class, typically
     *             used for static SQL SELECT statements.
     */
    public static ResultPackage sqlQuery(Connection conn, String sql) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            String[] headerFields = QueryResultManager.getHeaderFields(rsmd, columnCount);

            List<List<String>> dataValues = QueryResultManager.GetColumnData(rs, columnCount);

            rs.close();
            return new ResultPackage(headerFields, dataValues);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * Queries the database and populates data struct for table display
     * 
     * @param conn a connection object
     * @param sql  a SQL statement that returns rows:
     *             this query is written with the PrepareStatement class, typically
     *             used for dynamic SQL SELECT statements.
     */
    public static ResultPackage sqlQuery(Connection conn, PreparedStatement sql) {
        try {
            ResultSet rs = sql.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            String[] headerFields = QueryResultManager.getHeaderFields(rsmd, columnCount);
            List<List<String>> dataValues = QueryResultManager.GetColumnData(rs, columnCount);

            rs.close();

            return new ResultPackage(headerFields, dataValues);

        } catch (SQLException e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Updates the database and verifies success.
     * 
     * @param conn a connection object
     * @param sql  a SQL statement that updates rows:
     *             this query is written with the PrepareStatement class, typically
     *             used for SQL Update statements.
     * @return
     */
    public static String updateQuery(Connection conn, PreparedStatement sql) {
        try {
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                return "Database Updated Successfully";
            } else {
                return "No row found to update";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

}
