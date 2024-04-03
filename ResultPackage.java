public class ResultPackage {
    private String[][] tableData;
    private String[] headerFields;

    public ResultPackage(String[] header, String[][] data) {
        this.tableData = data;
        this.headerFields = header;
    }

    public String[][] getTableData() {
        return tableData;
    }

    public String[] getHeaderFields() {
        return headerFields;
    }
}
