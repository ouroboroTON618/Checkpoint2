import java.util.Scanner;

public class Main {

    private static RentEquipmentPage rentPage;

    /*
     * Find if a given record exists
     */
    private static void SearchRecord(Scanner scanner, EquipManager equipmentList) {
        System.out.println("Enter serial number of record to search: ");
        equipmentList.SearchEquipment(scanner.nextInt());
    }

    /*
     * Add a new record to database
     */
    private static void AddRecord(Scanner scanner, EquipManager equipmentList) {
        Equipment newEquip = EquipmentFactory.GenerateEquip(scanner);
        equipmentList.AddEquipment(newEquip);
    }

    /*
     * Delete record from database
     */
    private static void DeleteRecord(Scanner scanner, EquipManager equipmentList) {
        System.out.println("Enter serial number of record to delete: ");
        equipmentList.DeleteEquipment(scanner.nextInt());
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

        while (!exit) {
            DisplayMenuPrompt();
            option = scanner.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    AddRecord(scanner, equipmentList);
                    break;
                case 2:
                    DeleteRecord(scanner, equipmentList);
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
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
            System.out.println();
        }

        scanner.close();
    }
}