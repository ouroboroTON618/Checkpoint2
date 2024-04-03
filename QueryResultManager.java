import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryResultManager {

    // this class takes care of the result manager stuff. this takes care of return
    // messages of success, error.
    // this class will have methods that properly organizes database results that we
    // need.

    // current needed data structure results:
    // Query: Table data without headers: String[][], headers: String[]

    public QueryResultManager() {

    }

    public static String[] getHeaderFields(ResultSetMetaData data, int columnCount) {

        String[] result = null;
        try {
            result = new String[columnCount];
            // prints out column headers......
            for (int i = 1; i <= columnCount; i++) {
                String value = data.getColumnName(i);
                result[i - 1] = value;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static String[][] GetColumnData(ResultSet rs, int columnCount) {
        String[][] result = null;
        try {
            int rowTotal = 0;
            while (rs.next()) {
                rowTotal++;
            }
            result = new String[rowTotal][columnCount];
            rs.beforeFirst();
            int row = 0;
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i);
                    result[row][i - 1] = columnValue;
                }
                row++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
