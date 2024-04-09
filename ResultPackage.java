import java.util.List;

public class ResultPackage {
    private List<List<String>> tableData;
    private String[] headerFields;

    public ResultPackage(String[] header, List<List<String>> data) {
        this.tableData = data;
        this.headerFields = header;
    }

    public List<List<String>> getTableData() {
        return tableData;
    }

    public String[] getHeaderFields() {
        return headerFields;
    }
}
