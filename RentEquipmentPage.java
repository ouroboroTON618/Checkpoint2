import java.util.HashMap;
import java.util.Scanner;

import javax.naming.directory.SearchResult;

import java.util.Random;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentEquipmentPage {

    private String type;
    private Scanner scanner;

    private HashMap<String, EquipmentRentalObject> cart;

    private boolean dataComplete;

    public RentEquipmentPage() {
        scanner = new Scanner(System.in);
        cart = new HashMap<>();
        dataComplete = false;
        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Equipment Rental Page"));

        MenuItems();

    }

    private void MenuItems() {
        dataComplete = checkComplete();
        System.out.println(LineGenerator.generateLine("Equipment Rental"));
    
        System.out.println(LineGenerator.generateLine("Option (A): Select Item(s)"));
        System.out.println(LineGenerator.generateLine("Option (L): Begin Checkout"));
        if (dataComplete) {
            System.out.println(LineGenerator
                    .generateLine("Option(S): All Data Has Been Received. Submit Order and Assign Drone."));
        }
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));

        MenuSelect();
        return;
    }

    private boolean checkComplete() {
        if (cart.size() != 0) {
            for (HashMap.Entry<String, EquipmentRentalObject> entry : cart.entrySet()) {
                String key = entry.getKey();
                EquipmentRentalObject value = entry.getValue();
                if (!value.checkCompleted()) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;

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
            case 'l':
                Checkout();
                break;
            case 'c':
                Remove();
                break;
            case 's':
                if (dataComplete) {
                    beginSumitProcess();
                } else {
                    System.out.println(
                            "Requirements Not Met. Not all fields are complete. Please complete all fields to submit");
                    MenuItems();
                }
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
        return;
    }

    private void Remove() {
        System.out.println("Remove From Cart");

        for (HashMap.Entry<String, EquipmentRentalObject> entry : cart.entrySet()) {
            String key = entry.getKey();
            EquipmentRentalObject value = entry.getValue();

            System.out.println("Item [ " + key + " :  " + value.getSerialNo() + " ]");
        }
        String serialNo = SerialNumber();
        cart.remove(serialNo);
        System.out.println(LineGenerator.generateLine("Item [" + serialNo + "] has been removed from the cart."));

        System.out.println(LineGenerator.generateLine("Your Cart Has: "));
        for (HashMap.Entry<String, EquipmentRentalObject> entry : cart.entrySet()) {
            String key = entry.getKey();
            EquipmentRentalObject value = entry.getValue();
            System.out.println("Item [ " + key + " :  " + value.getSerialNo() + " ]");
        }
    }

    private void Checkout() {
    	System.out.println("We are in checkouts");
    	
    	if(cart.size() != 0) {
	        for (HashMap.Entry<String, EquipmentRentalObject> entry : cart.entrySet()) {
	            String key = entry.getKey();
	            EquipmentRentalObject value = entry.getValue();
	            CheckoutSelectionTypes(value);
	        }
        }else {
        	System.out.println("Please add to your cart.");
        	MenuItems();
        }
    }

    public void ItemSelectionTypes() {
        System.out.println(LineGenerator.generateLine("Option (A): Rent By Type"));
        System.out.println(LineGenerator.generateLine("Option (B): Rent By Item Serial No"));
        System.out.println(LineGenerator.generateLine("Option (C): Remove Items from cart"));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));

    }

    public void CheckoutSelectionTypes(EquipmentRentalObject item) {
        System.out.println(LineGenerator.generateLine("When All Information is fufilled for all items"));
        System.out
                .println(LineGenerator.generateLine("Select (S) to return to Options menu to submit and assign Drone"));
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
                MenuItems();
                break;

            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                CheckoutSelectionTypes(item);
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
        MenuItems();
      
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
        cart.put(serial,item);
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
        cart.put(serial,item);
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

        if (!CompletedDianostics(item, GET_FIELD.EST_ARR)) {
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
        if (!CompletedDianostics(item, GET_FIELD.DUE_DATE)) {
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
    private void SubmitInfo(EquipmentRentalObject item) {

        if (Main.databaseEnabled) {
        	
        	System.out.println("We are about to add");
            String addResult = QueryManager.addRentEquipment(item);
            System.out.println(addResult);
        } else {
            System.out.println("Database Disabled: Information Submitted");
        }
    }

    private void beginSumitProcess() {

    	System.out.println("We are in begin sub");
        String rentalNo = "0";
        for (HashMap.Entry<String, EquipmentRentalObject> entry : cart.entrySet()) {
            String key = entry.getKey();
            EquipmentRentalObject value = entry.getValue();
            
            System.out.println("We are in for loop");
            SubmitInfo(value);
            rentalNo = value.getRentalNo();
            DroneAssign.ScheduleDrone(Integer.parseInt(key), Integer.parseInt(value.getRentalNo()), true);
        }

        System.out.println(LineGenerator.generateLine("Everything has been successfully submitted!"));
        System.out.println(LineGenerator.generateLine("Here is your order information:"));

        ResultPackage result = QueryManager.getBulkOrder(rentalNo);
        TableDisplayGenerator.GenerateTable(result);
    }

    private boolean CompletedDianostics(EquipmentRentalObject item, GET_FIELD type) {

        switch (type) {
            case TYPE:
                if (item.getType() != null) {
                    return CompletedDianosticsMsg();
                }
                return false;
            case SERIAL_NO:
                if (item.getSerialNo() != null) {
                    return CompletedDianosticsMsg();
                }
                return false;
            case RENTAL_NO:
                if (item.getRentalNo() != null) {
                    return CompletedDianosticsMsg();
                }
                return false;
            case EST_ARR:
                if (item.getEstArr() != null) {
                    return CompletedDianosticsMsg();
                }
                return false;

            case DUE_DATE:
                if (item.getDueDate() != null) {
                    return CompletedDianosticsMsg();
                }
                return false;
            case CUSTOMER_COST:
                if (item.getCustomerCost() != null) {
                    return CompletedDianosticsMsg();
                }
                return false;
            default:
                return false;
        }

    }

    private boolean CompletedDianosticsMsg() {
        System.out.println(LineGenerator.generateLine("This Section Has Already Been Completed."));
        System.out.println(LineGenerator.generateLine("Would You Like to Modify Your Selection? (Y/N)."));
        System.out.print("Option: ");
        String input = scanner.nextLine();
       
        if(input.toLowerCase().charAt(0) == 'y') {
        	return false;
        }else {
        	return true;
        }
    }

}