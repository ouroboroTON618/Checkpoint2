
import java.util.Scanner;

public class SearchPage {

    private String type;
    private Scanner scanner;

    public SearchPage() {

        scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------");
        System.out.println("-----------------------Search Page----------------------");
        MenuItems();
    }

    private void MenuItems() {

        System.out.println("---------------------------Search Options: -------------------------------");
        System.out.println("--------------------------Option(A): Records------------------------");
        System.out.println("--------------------------Option(B): Member------------------------");
        System.out.println("--------------------------Option(C): Equipment------------------------");
        System.out.println(
                "-------------------------Please Select a Menu Option Or M to return to Main Page-------------------");
        MenuSelect();
    }

    private void MenuSelect() {
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                System.out.println("temporay place holder for switch to main page");
                break;
            case 'a':
                SearchRecords();
                break;
            case 'b':
                SearchMember();
                break;
            case 'c':
                SearchEqipment();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void SearchEqipment() {
        System.out.println("Enter Equipment name to search: ");
        String name = scanner.nextLine();

        ResultPackage result = GetQuery("name", name);
        TableDisplayGenerator.GenerateTableWithData(result.getHeaderFields(), result.getTableData());
        MenuItems();
    }

    private ResultPackage GetQuery(String type, String value) {

        switch (type) {
            case "name":
                return QueryManager.getSearchEquip(value);
            case "member":

                return null;
            case "records":

                return null;
            default:
                return null;
        }

    }

    private void SearchMember() {
        System.out.println("Enter member_ID to search: ");
        // Implement later
    }

    private void SearchRecords() {
        System.out.println("Enter serial number of record to search: ");
        int serial_num = scanner.nextInt();

        // implement later

    }

}
