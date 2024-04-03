import java.util.Scanner;
import java.sql.Connection;

public class Main {

    private static RentEquipmentPage rentPage;
    private static EditDeleteRecordsPage editDeletePage;
    private static SearchPage SPage;
    private static AddRecords addRecPage;
    public static Connection conn;

    /*
     * Find if a given record exists
     */
    private static void SearchRecordPage(Scanner scanner, EquipManager equipmentList) {
        SPage = new SearchPage();
    }

    /*
     * Add a new record to database
     */
    private static void AddRecord(Scanner scanner, EquipManager equipmentList) {
        addRecPage = new AddRecords();
    }

    /*
     * Delete record from database
     */
    private static void EditDeleteRecord(Scanner scanner, EquipManager equipmentList) {
        editDeletePage = new EditDeleteRecordsPage(equipmentList);

    }

    private static void RentEquipment() {
        rentPage = new RentEquipmentPage();
    }

    /*
     * Menu with all options for the user
     */
    private static void DisplayMenuPrompt() {
        System.out.println("Choose one of the following menu options:");
        System.out.println("1. Add new records");
        System.out.println("2. Edit/delete records");
        System.out.println("3. Search");
        System.out.println("4. Rent equipment");
        System.out.println("5. Return equipment");
        System.out.println("6. Schedule delivery of equipment");
        System.out.println("7. Schedule pickup of equipment:");
        System.out.println("9. Useful Reports");
        System.out.println("10. Exit");
        System.out.print("Option: ");
    }

    public static void main(String[] args) {
        EquipManager equipmentList = new EquipManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option;

        QueryConnection connectionManager = new QueryConnection();
        conn = connectionManager.GetConnection();

        while (!exit) {
            DisplayMenuPrompt();
            option = scanner.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    AddRecord(scanner, equipmentList);
                    break;
                case 2:
                    EditDeleteRecord(scanner, equipmentList);
                    break;
                case 3:
                    SearchRecord(scanner, equipmentList);
                    break;
                case 4:
                    RentEquipment();
                    break;
                case 5:
                    RentEquipment();
                    break;
                case 6:
                    RentEquipment();
                    break;
                case 7:
                    RentEquipment();
                    break;
                case 10:
                    System.out.println("Exiting program...");
                    exit = true;
                    connectionManager.closeConnection(exit);
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
            System.out.println();
        }

        scanner.close();
    }
}