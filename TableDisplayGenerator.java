public class TableDisplayGenerator {

    /**
     * This is all boiler plate code to display a visually appealing table
     * 
     * @param columns
     * @param data
     */
    public static void GenerateTableWithData(String[] columns, String[][] data) {
        for (String c : columns) {
            System.out.print(padRight(c, 15));
        }
        System.out.println();

        // Table Outlines
        for (int i = 0; i < columns.length * 15; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Populate with data
        for (String[] r : data) {
            for (String val : r) {
                System.out.print(padRight(val, 15));
            }
            System.out.println();
        }
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static void GenerateTable(ResultPackage result) {
        if (result != null) {
            String[] header = result.getHeaderFields();
            String[][] data = result.getTableData();
            TableDisplayGenerator.GenerateTableWithData(header, data);
        } else {
            System.out.println("Result is null");
        }

    }

}
