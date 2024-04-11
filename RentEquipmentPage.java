import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentEquipmentPage {

    private String type;
    private Scanner scanner;

    private HashMap<String, EquipmentRentalObject> cart;

    public RentEquipmentPage() {
        scanner = new Scanner(System.in);
        cart = new HashMap<>();

        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Equipment Rental Page"));

        MenuItems();

    }

    private void MenuItems() {
        System.out.println(LineGenerator.generateLine("Equipment Rental"));
        // System.out.println(LineGenerator.generateLine("You have selected (T): " +
        // rentalStatus.get("Name")));
        // System.out.println(LineGenerator.generateLine("Delivery Date (D): " +
        // rentalStatus.get("Delivery Date")));
        // System.out.println(LineGenerator.generateLine("Return Date (R): " +
        // rentalStatus.get("Return Date")));

        // //-------------

        // //-----
        System.out.println(LineGenerator.generateLine("Option (A): Select Item(s)"));
        System.out.println(LineGenerator.generateLine("Option (B): Begin Checkout"));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));

        // if (!rentalStatus.containsValue("Incomplete")) {
        // System.out.println(LineGenerator.generateLine("Information Completed: Submit
        // Information (s)"));
        // } else {
        // System.out.println(LineGenerator.generateLine("Warning: All fields must be
        // completed to submit"));
        // }
        MenuSelect();
        return;
    }

    private void MenuSelect() {
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                Main.DisplayMenuPrompt();
                break;
            case 'a':
                AddItems();
                break;
            case 'b':
                Checkout();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
        return;
    }

    private void Checkout() {
        for (HashMap.Entry<String, EquipmentRentalObject> entry : cart.entrySet()) {
            String key = entry.getKey();
            EquipmentRentalObject value = entry.getValue();
            CheckoutSelectionTypes(value);

        }

    }

    public void ItemSelectionTypes() {
        System.out.println(LineGenerator.generateLine("Option (A): Rent By Type"));
        System.out.println(LineGenerator.generateLine("Option (B): Rent By Item Serial No"));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));

    }

    public void CheckoutSelectionTypes(EquipmentRentalObject item) {
        System.out.println(LineGenerator.generateLine("You have selected: " +
                item.getType() + " [ " + item.getSerialNo() + "]"));
        System.out.println(LineGenerator.generateLine("Estimated Delivery Date (D): " +
                item.getEstArr()));
        System.out.println(LineGenerator.generateLine("Due Date (R): " +
                item.getDueDate()));

        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));

        ItemInfoSelect(item);
    }

    private void ItemInfoSelect(EquipmentRentalObject item) {
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                Main.DisplayMenuPrompt();
                break;
            case 'd':
                DeliveryDate(item);
                break;
            case 'r':
                ReturnDate(item);
                break;
            case 's':
                SubmitInfo(item);
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
        return;
    }

    private void AddItems() {

        boolean addMore = true;
        while (addMore) {
            ItemSelectionTypes();
            RentEQuipSelect();
            addMore = Continue();
        }
        MenuSelect();
    }

    private boolean Continue() {

        System.out.println(LineGenerator.generateLine("Would You Like to Rent More equipment? (Y/N)"));
        System.out.print("(y/n): ");
        String choice = scanner.nextLine();
        return VerifyInputs.verifyYNInput(choice);
    }

    private void RentEQuipSelect() {
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                Main.DisplayMenuPrompt();
                break;
            case 'a':
                RentalSelect();
                break;
            case 'b':
                SerialNoSelect();
                break;

            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
        return;
    }

    private void SerialNoSelect() {
        if (!CompletedDianostics("Type")) {
            boolean confirm = false;
            String type = "";
            String serial = "";
            while (!confirm) {
                serial = SerialNumber();
                type = GetTypeBySerial(serial);
                System.out.println(
                        LineGenerator
                                .generateLine("Selected Equipment is: " + type + " [" + serial
                                        + "]. Confirm Selection? (Y/N)"));
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
            }
            EquipmentRentalObject item = new EquipmentRentalObject();
            item.setSerialNo(serial);
            item.setType(type);
            item.setRentalNo(GenerateRentalNo());
        }
    }

    private String GetTypeBySerial(String serial) {

        ResultPackage result = QueryManager.getTypeBySerial(Integer.parseInt(serial));

        return LineGenerator.GetFirstDataVal(result);
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

    private void RentalSelect() {
        if (!CompletedDianostics("Type")) {
            boolean confirm = false;
            String type = "";
            String serial = "";
            while (!confirm) {
                type = EquipType();
                if (DisplaySerialByType(type)) {
                    serial = SerialNumber();
                    System.out.println(
                            LineGenerator
                                    .generateLine("Selected Equipment is: " + type + " [" + serial
                                            + "]. Confirm Selection? (Y/N)"));
                    String confirmation = scanner.nextLine();
                    confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
                }
            }
            EquipmentRentalObject item = new EquipmentRentalObject();
            item.setSerialNo(serial);
            item.setType(type);
            item.setRentalNo(GenerateRentalNo());
        }

        return;
    }

    private boolean DisplaySerialByType(String type) {

        // Gets list of all equipment serial numbers of that specific type and is of
        // avaliable status
        ResultPackage result = QueryManager.getSerialByType(type);
        if (result != null) {
            TableDisplayGenerator.GenerateTable(result);
            return true;
        } else {
            return false;
        }

    }

    private String SerialNumber() {

        String serialNo = "";
        while (!VerifyInputs.verifySerialNoExist(serialNo)) {
            System.out.println(LineGenerator.generateLine("Enter serial number of item: "));
            System.out.print("Serial Number: ");
            serialNo = scanner.nextLine();
        }
        return serialNo;
    }

    private String EquipType() {
        String type = "";
        while (!VerifyInputs.VerifyEquipmentType(type)) {
            System.out.println(LineGenerator.generateLine("Please Enter an Equipment Type to Rent"));
            DisplayTypes();
            System.out.print("Type: ");
            type = scanner.nextLine();
        }
        return type;

    }

    private ResultPackage EquipmentTypeList() {

        if (Main.databaseEnabled) {
            return QueryManager.getEquipmentTypes();
        }
        return null;
    }

    private void InputParser(String input) {
        if (input.equals('m')) {
            System.out.println("Do You Wish to Return to Main? Your data will be Lost(Y/N):");
            boolean confirm = (scanner.nextLine().toLowerCase().charAt(0) == 'y') ? true : false;

            if (confirm) {
                Main.DisplayMenuPrompt();
            } else {
                MenuItems();
            }
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

    private void DeliveryDate(EquipmentRentalObject item) {

        if (!CompletedDianostics("Estimated Delivery Date")) {
            boolean confirm = false;
            String date = "";
            while (!confirm) {
                date = Date("Please Enter an Available Date for Delivery(YYYY/MM/DD). This is an estimated date.");
                System.out.println(
                        LineGenerator.generateLine("Selected Date is: " + date + " . Confirm Selection? (Y/N)"));
                System.out.print("Option: ");
                String confirmation = scanner.nextLine();
                confirm = (confirmation.toLowerCase().charAt(0) == 'y') ? true : false;
            }
            item.setEstArr(date);
        }
        CheckoutSelectionTypes(item);

        return;
    }

    private void ReturnDate(EquipmentRentalObject item) {
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
            item.setDueDate(date);
        }
        CheckoutSelectionTypes(item);

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

    /*
     * This is placed on hoold
     */
    private void SubmitInfo(item) {
        if (Main.databaseEnabled) {
        String resultUpdate = QueryManager.updateRentEquipment(item);
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