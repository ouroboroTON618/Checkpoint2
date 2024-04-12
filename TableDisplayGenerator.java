import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableDisplayGenerator {

    /**
     * This is all boiler plate code to display a visually appealing table
     * 
     * @param columns
     * @param data
     */
    public static void GenerateTableWithData(String[] columnsArr, List<List<String>> data) {
        List<String> columns = Arrays.asList(columnsArr);
        List<Integer> columnWidths = getColumnWidths(columns, data);

        for (int i = 0; i < columns.size(); i++) {
            System.out.print(padRight(columns.get(i), columnWidths.get(i)));
        }
        System.out.println();

        // Line for table
        for (int width : columnWidths) {
            for (int i = 0; i < width; i++) {
                System.out.print("-");
            }
        }
        System.out.println();

        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                System.out.print(padRight(row.get(i), columnWidths.get(i)));
            }
            System.out.println();
        }
    }

    public static List<Integer> getColumnWidths(List<String> columns, List<List<String>> data) {
        List<Integer> widths = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            int maxWidth = columns.get(i).length();
            for (List<String> row : data) {
                String value = row.get(i);
                maxWidth = Math.max(maxWidth, value.length());
            }
            widths.add(maxWidth + 10);
        }
        return widths;
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static void GenerateTable(ResultPackage result) {

        if (result != null) {
            String[] header = result.getHeaderFields();
            List<List<String>> data = result.getTableData();
            System.out.println();
            GenerateTableWithData(header, data);
            System.out.println();
        } else {
            System.out.println("Result is null");
        }

    }

}
