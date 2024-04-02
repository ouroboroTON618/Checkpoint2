
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
        // results in format of:
        // header: string[]
        // data: string[][]

        // call table generator
    }

    private void PopularItem() {
        // CALL QUERY MANAGER
        // CALL QUERY MANAGER
        // results in format of:
        // header: string[]
        // data: string[][]

        // call table generator
    }

    private void PopularManufacturer() {
        // CALL QUERY MANAGER
        // CALL QUERY MANAGER
        // results in format of:
        // header: string[]
        // data: string[][]

        // call table generator
    }

    private void PopularDrone() {
        // CALL QUERY MANAGER
        // CALL QUERY MANAGER
        // results in format of:
        // header: string[]
        // data: string[][]

        // call table generator
    }

    private void ItemsCheckedOut() {
        // CALL QUERY MANAGER
        // CALL QUERY MANAGER
        // results in format of:
        // header: string[]
        // data: string[][]

        // call table generator
    }

    private void EquipmentByTypeOfEquipment() {
        // CALL QUERY MANAGER
        // CALL QUERY MANAGER
        // results in format of:
        // header: string[]
        // data: string[][]

        // call table generator
    }

}
