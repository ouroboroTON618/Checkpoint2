
import java.util.HashMap;
import java.util.Scanner;

public class UsefulReportsPage {

    private String type;
    private Scanner scanner;
    private HashMap<String, String> rentalStatus;

    public UsefulReportsPage() {
        scanner = new Scanner(System.in);

        System.out.println("------------------------------------------------------------------");
        System.out.println("-----------------------Useful Reports Page----------------------");
        MenuItems();
    }

    private void MenuItems() {

        System.out.println("---------------------------Useful Reports -------------------------------");
        System.out.println("---------------------------Option A: Renting Checkouts-------------------------------");
        System.out.println("---------------------------Option B: Popular Item-------------------------------");
        System.out.println("---------------------------Option C: Popular Manufacturer-------------------------------");
        System.out.println("---------------------------Option D: Popular Drone-------------------------------");
        System.out.println("---------------------------Option E: Items Checked Out-------------------------------");
        System.out.println(
                "---------------------------Option F: Equipment by Type of equipment-------------------------------");

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
                RentingCheckouts();
                break;
            case 'b':
                PopularItem();
                break;
            case 'c':
                PopularManufacturer();
                break;
            case 'd':
                PopularDrone();
                break;
            case 'e':
                ItemsCheckedOut();
                break;
            case 'f':
                EquipmentByTypeOfEquipment();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void RentingCheckouts() {
        // CALL QUERY MANAGER

        System.out.println("Enter the member ID: ");

        // Assuming its valid
        String member_ID = scanner.nextLine();

        ResultPackage result = QueryManager.getRentingCheckouts(member_ID);
        String[] header = result.getHeaderFields();
        String[][] data = result.getTableData();

        TableDisplayGenerator.GenerateTableWithData(header, data);
    }

    private void PopularItem() {

        // call table generator
        ResultPackage result = QueryManager.getItemsCheckedOut();
        String[] header = result.getHeaderFields();
        String[][] data = result.getTableData();

        TableDisplayGenerator.GenerateTableWithData(header, data);
    }

    private void PopularManufacturer() {
        ResultPackage result = QueryManager.getPopularManufacturer();
        // call table generator
        String[] header = result.getHeaderFields();
        String[][] data = result.getTableData();

        TableDisplayGenerator.GenerateTableWithData(header, data);
    }

    private void PopularDrone() {
        ResultPackage result = QueryManager.getPopularDrone();
        // call table generator
        String[] header = result.getHeaderFields();
        String[][] data = result.getTableData();

        TableDisplayGenerator.GenerateTableWithData(header, data);
    }

    private void ItemsCheckedOut() {

        ResultPackage result = QueryManager.getItemsCheckedOut();
        // call table generator
        String[] header = result.getHeaderFields();
        String[][] data = result.getTableData();

        TableDisplayGenerator.GenerateTableWithData(header, data);
    }

    private void EquipmentByTypeOfEquipment() {
        System.out.println("Enter the Year: ");
        String year = scanner.nextLine();
        System.out.println("Enter the Type of Equipement: ");
        // Assuming its valid
        String type = scanner.nextLine();

        int yearNum = Integer.parseInt(year);

        ResultPackage result = QueryManager.getEquipmentByTypeOfEquipment(yearNum, type);
        // call table generator
        String[] header = result.getHeaderFields();
        String[][] data = result.getTableData();

        TableDisplayGenerator.GenerateTableWithData(header, data);
    }

}
