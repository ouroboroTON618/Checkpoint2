
import java.util.Scanner;

public class SearchPage {

    private String type;
    private Scanner scanner;

    public SearchPage() {

        scanner = new Scanner(System.in);
        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Search Page"));
        MenuItems();
    }

    private void MenuItems() {

        System.out.println(LineGenerator.generateLine("Search Options:"));
        System.out.println(LineGenerator.generateLine("Option(A): Records"));
        System.out.println(LineGenerator.generateLine("Option(B): Member"));
        System.out.println(LineGenerator.generateLine("Option(C): Equipment"));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));
        MenuSelect();
    }

    private void MenuSelect() {
        System.out.print("Option: ");
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                Main.DisplayMenuPrompt();
                break;
            case 'a':
                SearchRecords();
                break;
            case 'b':
                SearchMember();
                break;
            case 'c':
                SearchEquipment();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private ResultPackage GetQuery(Type key, String value) {

        switch (key) {
            case EQUIPMENT_NAME:
                if (Main.databaseEnabled) {
                    return QueryManager.getEquipment(value);
                } else {
                    System.out.println("Database Disabled: Search Equipment Query Success");
                    return null;
                }
            case MEMBER_ID:
                if (Main.databaseEnabled) {
                    int member_id = Integer.parseInt(value);
                    return QueryManager.getMember(member_id);
                } else {
                    System.out.println("Database Disabled: Search Member ID Query Success");
                    return null;
                }
            case SERIAL_NUM:
                if (Main.databaseEnabled) {
                    int serialNum = Integer.parseInt(value);
                    return QueryManager.getRecord(serialNum);
                } else {
                    System.out.println("Database Disabled: Search Record Query Success");
                    return null;
                }
            default:
                return null;
        }

    }

    private void SearchMember() {
        String memberID = "";
        while (!VerifyInputs.VerifyMemberID(memberID)) {
            System.out.println(LineGenerator.generateLine("Enter member_ID to search"));
            System.out.println("Member ID: ");
            memberID = scanner.nextLine();
        }
        DisplayResults(Type.MEMBER_ID, memberID);
    }

    private void SearchRecords() {
        String serialNo = "";
        while (!VerifyInputs.verifySerialNo(serialNo)) {
            System.out.println(LineGenerator.generateLine("Enter serial number of record to search: "));
            System.out.print("Serial Number: ");
            serialNo = scanner.nextLine();
        }
        DisplayResults(Type.SERIAL_NUM, serialNo);
    }

    private void SearchEquipment() {
        String type = "";
        while (!VerifyInputs.VerifyEquipmentType(type)) {
            System.out.println(LineGenerator.generateLine("Enter the Type of Equipment(Type)"));
            System.out.print("Type: ");
            type = scanner.nextLine();
        }
        DisplayResults(Type.EQUIPMENT_NAME, type);
    }

    private void DisplayResults(Type key, String value) {
        ResultPackage result = GetQuery(key, value);

        if (result != null) {
            TableDisplayGenerator.GenerateTableWithData(result.getHeaderFields(), result.getTableData());
        }
        MenuItems();
    }

}
