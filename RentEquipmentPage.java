import java.util.HashMap;
import java.util.Scanner;

public class RentEquipmentPage {

    private String type;
    private Scanner scanner;
    private HashMap<String, String> rentalStatus;

    public RentEquipmentPage() {
        scanner = new Scanner(System.in);
        rentalStatus = new HashMap<String, String>() {
            {
                put("Type", "Incomplete");
                put("Delivery Date", "Incomplete");
                put("Return Date", "Incomplete");
                put("Pickup Date", "Incomplete");
            }
        };

        System.out.println("------------------------------------------------------------------");
        System.out.println("-----------------------Equipment Rental Page----------------------");
        MenuItems();

    }

    private void MenuSelect() {
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                System.out.println("temporay place holder for switch to main page");
                break;
            case 't':
                RentalSelect();
                break;
            case 'd':
                DeliveryDate();
                break;
            case 'r':
                ReturnDate();
                break;
            case 'p':
                PickupDate();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void RentalSelect() {

        if (CompletedDianostics("Type")) {
            boolean confirm = false;
            String type = "null";
            while (!confirm) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("-------------Please Enter an Equipment Type to Rent---------------");
                System.out.println("----------To see what equipment is available: Press H-------------");
                type = InputParser(scanner.nextLine().toLowerCase());
                System.out.println("Selected Equipment is: " + type + " . Confirm Selection? (Y/N)");
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y' && isValid(type)) ? true : false;
            }
            rentalStatus.put("Type", type);
        }
        MenuItems();
    }

    private boolean isValid(String type) {

        // if array of types contains string type. Then return True.
        // return true;
        // else if array of types does not contain string 'type' then print:
        System.out.println("We do not have that equipment on hand. Please select another.");
        return false;
    }

    private String InputParser(String input) {
        if (input.equals('m')) {
            System.out.println("Do You Wish to Return to Main? Your data will be Lost(Y/N):");
            boolean confirm = (scanner.nextLine().toLowerCase().charAt(0) == 'y') ? true : false;

            // if(confirm)//return them to main

            // else continue on.
        }

        if (input.equals("h")) {
            return DisplayTypes();
        } else {
            return input;
        }
    }

    private String DisplayTypes() {
        // select from array.

        System.out.println("-------------Please Enter an Equipment Type to Rent---------------");
        type = scanner.nextLine();
        return type;
    }

    private void MenuItems() {

        System.out.println("---------------------------Equipment Rental-------------------------------");
        System.out.println("--------------------------You have selected: " + rentalStatus.get("Name")
                + "------------------------");

        System.out.println("--------------------------Delivery Date: " + rentalStatus.get("Delivery Date")
                + "------------------------");
        System.out.println("--------------------------Pickup Date: " + rentalStatus.get("Pickup Date")
                + "------------------------");
        System.out.println("--------------------------Return Date: " + rentalStatus.get("Return Date")
                + "------------------------");
        System.out.println(
                "-------------------------Please Select a Menu Option Or M to return to Main Page-------------------");
        MenuSelect();
    }

    private void DeliveryDate() {

        if (CompletedDianostics("Delivery Date")) {
            boolean confirm = false;
            String date = "";
            while (!confirm) {
                System.out.println("Please Enter an Avaliable Date for Delivery(YYYY/MM/DD): ");
                date = scanner.nextLine();
                System.out.println("Selected Date is: " + date + " . Confirm Selection? (Y/N)");
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
            }
            rentalStatus.put("Delivery Date", date);
        }
        MenuItems();
    }

    private void ReturnDate() {
        if (CompletedDianostics("Return Date")) {
            boolean confirm = false;
            String date = "";
            while (!confirm) {
                System.out.println("Please Enter an Return Date (YYYY/MM/DD):");
                date = scanner.nextLine();
                System.out.println("Selected Date is: " + date + " . Confirm Selection? (Y/N)");
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
            }
            rentalStatus.put("Return Date", date);
        }
        MenuItems();
    }

    private void PickupDate() {
        if (CompletedDianostics("Pickup Date")) {
            boolean confirm = false;
            String date = "";
            while (!confirm) {
                System.out.println("Please Enter an Pickup Date (YYYY/MM/DD):");
                date = scanner.nextLine();
                System.out.println("Selected Date is: " + date + " . Confirm Selection? (Y/N)");
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
            }
            rentalStatus.put("Pickup Date", date);
        }
        MenuItems();
    }

    private boolean CompletedDianostics(String option) {

        if (rentalStatus.get(option) == "Incomplete") {
            System.out.println("This Section Has Already Been Completed.");
            System.out.println("Would You Like to Modify Your Selection? (Y/N).");
            String change = InputParser(scanner.nextLine().toLowerCase());
            if (change == "y") {
                return false;
            }
            return true;
        }
        return false;
    }

}