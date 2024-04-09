import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            for (int i = 1; i <= columnCount; i++) {
                String value = data.getColumnName(i);
                result[i - 1] = value;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static List<List<String>> GetColumnData(ResultSet rs, int columnCount) {
        List<List<String>> result = new ArrayList<>();
        try {

            while (rs.next()) {
                List<String> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i);
                    String dataWithoutNewLine = columnValue.replaceAll("\\r?\\n", ", ");
                    rowData.add(dataWithoutNewLine);
                }
                result.add(rowData);
            }

            if (Main.debugMode) {
                System.out.println("Data parsing COMPLETED");
            }

        } catch (SQLException e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return result;
    }

}
