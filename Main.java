import java.util.Scanner;
import java.sql.Connection;

public class Main {

    private static QueryConnection connectionManager;
    private static RentEquipmentPage rentPage;
    private static EditDeleteRecordsPage editDeletePage;
    private static UsefulReportsPage usefulPage;
    private static SearchPage SPage;
    private static AddRecords addRecPage;
    private static ReturnRentalPage returnPage;

    private static ConfirmDelivered confirm;
    public static Connection conn;
    public static boolean databaseEnabled = false;
    private static Scanner scanner;
    public static boolean debugMode = true;

    /*
     * This is merely a git test
     */
    /*
     * Find if a given record exists
     */
    private static void SearchRecordPage(Scanner scanner) {
        SPage = new SearchPage();
        return;
    }

    /*
     * Add a new record to database
     */
    private static void AddRecord(Scanner scanner) {
        addRecPage = new AddRecords();
        return;
    }

    /*
     * Delete record from database
     */
    private static void EditDeleteRecord(Scanner scanner) {
        editDeletePage = new EditDeleteRecordsPage();
        return;

    }

    private static void RentEquipment() {
        rentPage = new RentEquipmentPage();
        return;
    }

    private static void UsefulReports() {
        usefulPage = new UsefulReportsPage();
        return;
    }

    private static void Return_PickUp() {
        returnPage = new ReturnRentalPage();
        return;
    }

    private static void Confirm_Delivery() {
        confirm = new ConfirmDelivered();
        return;
    }

    /*
     * Menu with all options for the user
     */
    public static void DisplayMenuPrompt() {
        System.out.println("Choose one of the following menu options:");
        System.out.println("1. Add new equipment item");
        System.out.println("2. Edit/delete records");
        System.out.println("3. Search");
        System.out.println("4. Rent / Schedule Delivery Equipment");
        System.out.println("5. Useful Reports");
        System.out.println("6. Return / Schedule Return Pickup Equipment");
        System.out.println("7. Confirm Delivery Page");
        System.out.println("10. Exit");
        System.out.print("Option: ");
        MenuSelect();

    }

    public static void MenuSelect() {
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                AddRecord(scanner);
                break;
            case 2:
                EditDeleteRecord(scanner);
                break;
            case 3:
                SearchRecordPage(scanner);
                break;
            case 4:
                RentEquipment();
                break;
            case 5:
                UsefulReports();
                break;
            case 6:

                Return_PickUp();
                break;
            case 7:
                Confirm_Delivery();
                break;
            case 10:
                Exit();
                break;
            default:
                System.out.println("Invalid option. Try again!");
        }

        // .
    }

    private static void Exit() {
        System.out.println("Exiting program...");
        if (databaseEnabled) {
            connectionManager.closeConnection(true);
        }
        System.exit(0);
    }

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        boolean exit = false;
        int option;

        connectionManager = new QueryConnection();
        conn = connectionManager.GetConnection();
        System.out.println("This is the connection: " + connectionManager.GetConnection());

        if (databaseEnabled) {
            connectionManager = new QueryConnection();
            conn = connectionManager.GetConnection();
        }

        while (!exit) {
            DisplayMenuPrompt();

            System.out.println();
        }

        scanner.close();
    }
}