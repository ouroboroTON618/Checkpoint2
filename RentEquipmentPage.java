import java.util.HashMap;
import java.util.Scanner;
import java.sql.Connection;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                put("Purchase Date", PurchasedDate());
                put("Member_id", USER_INFO.MEMBER_ID.getValue().toString());
                put("Pickup", USER_INFO.Name.getValue().toString());
                put("Rental No", GenerateRentalNo());
            }
        };

        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Equipment Rental Page"));

        MenuItems();

    }

    private String PurchasedDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");
        return currentDateTime.format(formatter);
    }

    private String GenerateRentalNo() {
        Random rand = new Random();
        int randomSixDigitNumber = rand.nextInt(900000) + 100000;
        if (Main.databaseEnabled) {
            ResultPackage result = QueryManager.getRentalNos();
            while (VerifyInputs.verifyTableDataSingle(result, Integer.toString(randomSixDigitNumber), false)) {
                randomSixDigitNumber = rand.nextInt(900000) + 100000;
            }
        }
        return Integer.toString(randomSixDigitNumber);
    }

    private void MenuSelect() {
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                Main.DisplayMenuPrompt();
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
            case 's':
                SubmitInfo();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
        return;
    }

    private void RentalSelect() {
        if (!CompletedDianostics("Type")) {
            boolean confirm = false;
            String type = "";
            while (!confirm) {
                type = EquipType();
                System.out.println(
                        LineGenerator.generateLine("Selected Equipment is: " + type + " . Confirm Selection? (Y/N)"));
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
            }
            rentalStatus.put("Type", type);
        }
        MenuItems();
        return;
    }

    private String EquipType() {
        String type = "";
        while (!VerifyInputs.VerifyEquipmentType(type)) {
            System.out.println(LineGenerator.generateLine("Please Enter an Equipment Type to Rent"));
            System.out.println(LineGenerator.generateLine("(H) View Available Equipment"));
            System.out.print("Type: ");
            type = InputParser(scanner.nextLine().toLowerCase());
        }
        return type;

    }

    private ResultPackage EquipmentTypeList() {

        if (Main.databaseEnabled) {
            return QueryManager.getEquipmentTypes();
        }
        return null;
    }

    private String InputParser(String input) {
        if (input.equals('m')) {
            System.out.println("Do You Wish to Return to Main? Your data will be Lost(Y/N):");
            boolean confirm = (scanner.nextLine().toLowerCase().charAt(0) == 'y') ? true : false;

            if (confirm) {
                Main.DisplayMenuPrompt();
            } else {
                MenuItems();
            }
        }
        if (input.equals("h")) {
            DisplayTypes();
            return "";
        } else {
            return input;
        }
    }

    private void DisplayTypes() {

        // Display the equipment List
        ResultPackage result = EquipmentTypeList();
        if (result != null) {
            TableDisplayGenerator.GenerateTableWithData(result.getHeaderFields(), result.getTableData());
        } else {
            System.out.println("Result is null");
        }
    }

    private void MenuItems() {
        System.out.println(LineGenerator.generateLine("Equipment Rental"));
        System.out.println(LineGenerator.generateLine("You have selected (T): " + rentalStatus.get("Name")));
        System.out.println(LineGenerator.generateLine("Delivery Date (D): " + rentalStatus.get("Delivery Date")));
        System.out.println(LineGenerator.generateLine("Return Date (R): " + rentalStatus.get("Return Date")));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));

        if (!rentalStatus.containsValue("Incomplete")) {
            System.out.println(LineGenerator.generateLine("Information Completed: Submit Information (s)"));
        } else {
            System.out.println(LineGenerator.generateLine("Warning: All fields must be completed to submit"));
        }
        MenuSelect();
        return;
    }

    private void DeliveryDate() {

        if (!CompletedDianostics("Delivery Date")) {
            boolean confirm = false;
            String date = "";
            while (!confirm) {
                date = Date("Please Enter an Available Date for Delivery(YYYY/MM/DD)");
                System.out.println(
                        LineGenerator.generateLine("Selected Date is: " + date + " . Confirm Selection? (Y/N)"));
                System.out.print("Option: ");
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
            }
            rentalStatus.put("Delivery Date", date);
        }
        MenuItems();
        return;
    }

    private void ReturnDate() {
        if (!CompletedDianostics("Return Date")) {
            boolean confirm = false;
            String date = "";
            while (!confirm) {
                date = Date("Please Enter an Return Date (YYYY/MM/DD)");
                System.out.println(
                        LineGenerator.generateLine("Selected Date is: " + date + " . Confirm Selection? (Y/N)"));
                System.out.print("Option: ");
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
            }
            rentalStatus.put("Return Date", date);
        }
        MenuItems();
        return;
    }

    private String Date(String typeOfDate) {
        String date = "";
        while (!VerifyInputs.verifyDates(date)) {
            System.out.println(LineGenerator.generateLine(typeOfDate));
            System.out.print("Date: ");
            date = scanner.nextLine();
        }
        return date;
    }

    private void SubmitInfo() {
        if (Main.databaseEnabled) {
            System.out.println(
                    QueryManager.updateRentEquipment(rentalStatus.get("Type"), rentalStatus.get("Delivery Date"),
                            rentalStatus.get("Return Date"), rentalStatus.get("Pickup"),
                            rentalStatus.get("Purchase Date"),
                            rentalStatus.get("Member_id"), rentalStatus.get("Rental No")));
        } else {
            System.out.println("Database Disabled: Information Submitted");
        }
    }

    private boolean CompletedDianostics(String option) {

        if (rentalStatus.get(option) != "Incomplete") {
            System.out.println(LineGenerator.generateLine("This Section Has Already Been Completed."));
            System.out.println(LineGenerator.generateLine("Would You Like to Modify Your Selection? (Y/N)."));
            System.out.print("Option: ");
            String change = InputParser(scanner.nextLine().toLowerCase());
            if (change == "y") {
                return false;
            }
            return true;
        }
        return false;
    }

}