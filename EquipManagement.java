import java.util.Scanner;

public class EquipManagement {

    private static void SearchRecord(Scanner scanner, EquipDatabase equipmentList) {
        System.out.println("Enter serial number of record to search: ");
        equipmentList.SearchEquipment(scanner.nextInt());
    }

    private static void AddRecord(Scanner scanner, EquipDatabase equipmentList) {
        Equipment newEquip = EquipmentFactory.GenerateEquip(scanner);
        equipmentList.AddEquipment(newEquip);
    }

    private static void DeleteRecord(Scanner scanner, EquipDatabase equipmentList) {
        System.out.println("Enter serial number of record to delete: ");
        equipmentList.DeleteEquipment(scanner.nextInt());
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
        System.out.println("8. Exit");
        System.out.print("Option: ");
    }

    public static void main(String[] args) {
        EquipDatabase equipmentList = new EquipDatabase();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option;

        while (!exit) {
            DisplayMenuPrompt();
            option = scanner.nextInt();
            System.out.println();

            
            switch(option) {
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
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Exiting program...");
                    exit = true;
                    break;
                default: System.out.println("Invalid option. Try again!");
            }
        }

        scanner.close();
    }
}