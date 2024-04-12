import java.util.List;
import java.util.Scanner;

public class LineGenerator {

    private static boolean debug = false;

    public static String generateLine(String line) {
    	

        String basis = "------------------------------------------------------------------";

        int basisLen = basis.length();

        String word = line;
        int wordLen = word.length();

        int basisHalfLen = basisLen / 2;
        int wordHalfLen = wordLen / 2;

        if (basisLen > wordLen) {

            int startInd = basisHalfLen - wordHalfLen;
            int endInd = basisHalfLen - wordHalfLen;

            String check1 = basis.substring(0, startInd);
            String check2 = basis.substring(0, endInd);

            String newString = basis.substring(0, startInd) + word + basis.substring(0, endInd);
            int total = newString.length();

            if (debug) {
                System.out.println(newString);
                System.out.println("Front size: " + check1.length());
                System.out.println("Front size 2: " + check2.length());
                System.out.println("total size: " + total);
            }
            return newString;
        }
        return line;

    }

    public static void MainOrChange() {
        System.out.println(LineGenerator.generateLine("Please Select (S) to return to Options Menu"));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));
    }

    public static String GetFirstDataVal(ResultPackage result) {

        List<List<String>> tableData = result.getTableData();
        if (!tableData.isEmpty()) {
            List<String> firstRow = tableData.get(0);
            if (!firstRow.isEmpty()) {
                String firstDataValue = firstRow.get(0);
                return firstDataValue;
            } else {
                return "";
            }
        } else {
            System.out.println("There is no data in the table");
            return "";
        }

    }

    public static boolean ConditionalCheck(STATEMENT Statement) {
        Scanner scan = new Scanner(System.in);

        System.out.println(Statement);
        System.out.print("Option: ");
        String input = scan.nextLine();
        return VerifyInputs.verifyYNInput(input);
    }
    
   

}
