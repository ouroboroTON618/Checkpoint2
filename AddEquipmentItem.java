
import java.util.*;

public class AddEquipmentItem {

    private Scanner scanner;
    private HashMap<String, String> inputs;
    private HashSet<String> skipInputs;
    private EquipmentObject equipmentRecord;

    public AddEquipmentItem() {

        equipmentRecord = new EquipmentObject();

        scanner = new Scanner(System.in);
        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Add Records Page"));
        MenuItems();

    }

    private void MenuItems() {

        System.out.println(LineGenerator.generateLine("Add Records"));
        System.out.println(LineGenerator.generateLine("Option(A): Add"));
        System.out.println(LineGenerator.generateLine("Option(S): Submit Info"));
        System.out.println(LineGenerator.generateLine("Option(M): Return to Main page"));
        MenuSelect();
        return;
    }

    private void MenuSelect() {
        System.out.print("Option: ");
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                Main.DisplayMenuPrompt();
                break;
            case 'a':
                Add();
                break;
            case 's':
                submit();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
        return;
    }

    private void Add() {

        FieldSelection();
        return;
    }

    private void submit() {

        if (equipmentRecord.checkCompleted()) {
            if (Main.databaseEnabled) {
                String result = QueryManager.addNewEquipItem(equipmentRecord);
                System.out.println(result);

                // Display in database
                ResultPackage resultQuery = QueryManager.getRecord(Integer.parseInt(equipmentRecord.getSerialNo()));
                TableDisplayGenerator.GenerateTable(resultQuery);
            } else {
                System.out.println("Database Disabled: New Record Added");
            }
        } else {
            System.out.println("Please Complete All fields in order to submit.");
        }

        MenuItems();
        return;
    }

    private void FieldSelection() {
        System.out.println(LineGenerator.generateLine("Option (A): Serial_no  : " + equipmentRecord.getSerialNo()));
        System.out.println(
                LineGenerator.generateLine("Option (B): Manufacturer  : " + equipmentRecord.getManufacturer()));
        System.out
                .println(LineGenerator.generateLine("Option (C): Order Number  : " + equipmentRecord.getOrderNo()));
        System.out
                .println(
                        LineGenerator.generateLine("Option (D): Purchase Price  : " + equipmentRecord.getPurchasePr()));

        System.out.println(LineGenerator.generateLine("Option (E): Model_no  : " + equipmentRecord.getModelNo()));
        System.out.println(LineGenerator.generateLine("Option (F): Warrant_exp  : " + equipmentRecord.getWarrantExp()));
        System.out.println(LineGenerator.generateLine("Option (G): Year  : " + equipmentRecord.getYear()));

        System.out.println(LineGenerator.generateLine("Option (H): Rental_rate  : " + equipmentRecord.getRentalRate()));
        System.out.println(
                LineGenerator.generateLine("Option (I): Rental_status  : " + equipmentRecord.getRentalStatus()));

        System.out.println(LineGenerator.generateLine("Option (Y): EXIT FIELD SELECTION TO SUBMIT"));
        System.out.println(LineGenerator.generateLine("Option (Z): RETURN TO MAIN PAGE"));
        FieldSelect();
        return;

    }

    private void FieldSelect() {
        System.out.print("Option: ");
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'a':
                SerialNo();
                break;
            case 'b':
                Manufacturer();
                break;
            case 'c':
                Order_no();
                break;
            case 'd':
                PurchasePrice();
                break;
            case 'e':
                ModelNo();
                break;
            case 'f':
                WarrantExp();
                break;
            case 'g':
                Year();
                break;
            case 'h':
                RentalRate();
                break;
            case 'i':
                Rental_status();
                break;
            case 'y':
                MenuItems();
                break;
            case 'z':
                Main.DisplayMenuPrompt();
                break;
            // Add cases for x, y, and z similarly
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
        return;
    }

    private void RentalRate() {
        boolean confirm = true;
        if (!equipmentRecord.getRentalRate().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String rate = "";
            while (!VerifyInputs.verifyNoInput(rate, 0, true)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Rental Rate"));
                System.out.print("Rental Rate: ");
                rate = scanner.nextLine();
            }
            equipmentRecord.addRentalRate(rate);

        }
        FieldSelection();
        return;
    }

    private void Year() {
        boolean confirm = true;
        if (!equipmentRecord.getYear().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String year = "";
            while (!VerifyInputs.verifyYear(year)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Year"));
                System.out.print("Year: ");
                year = scanner.nextLine();
            }
            equipmentRecord.addYear(year);
        }
        FieldSelection();
        return;
    }

    private void WarrantExp() {
        boolean confirm = true;
        if (!equipmentRecord.getWarrantExp().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String date = "";
            while (!VerifyInputs.verifyDates(date)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Warrenty Expiration Date"));
                System.out.print("Date: ");
                date = scanner.nextLine();
            }
            equipmentRecord.addWarrantExp(date);
        }
        FieldSelection();
        return;
    }

    private void Order_no() {
        boolean confirm = true;
        if (!equipmentRecord.getOrderNo().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String orderNo = "";
            while (!VerifyInputs.verifyNoInput(orderNo, INFO_LEN.ORDER_NO.getLength(), false)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Order no"));
                System.out.print("Order no: ");
                orderNo = scanner.nextLine();
            }
            equipmentRecord.addOrderNo(orderNo);
        }
        FieldSelection();
        return;
    }

    private void Rental_status() {
        boolean confirm = true;
        if (!equipmentRecord.getRentalStatus().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String rentalStatus = "";
            while (!VerifyInputs.verifyRentalStatus(rentalStatus)) {
                System.out
                        .println(LineGenerator
                                .generateLine("Enter the Equipment's Rental Status (Available/Unavailable)"));
                System.out.print("Status: ");
                rentalStatus = scanner.nextLine();
            }
            equipmentRecord.addRentalStatus(rentalStatus);
        }
        FieldSelection();
        return;
    }

    private void PurchasePrice() {
        boolean confirm = true;
        if (!equipmentRecord.getPurchasePr().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String price = "";
            while (!VerifyInputs.verifyNoInput(price, 0, true)) {
                System.out.println(LineGenerator.generateLine("Enter the purchase price of this item"));
                System.out.print("Price (Omit $: only integers allowed): ");
                price = scanner.nextLine();
            }
            equipmentRecord.addPurchasePr(price);
        }
        FieldSelection();
        return;
    }

    private void ModelNo() {
        boolean confirm = true;
        
        
        if (!equipmentRecord.getModelNo().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

      
        if (confirm) {
            String modelNo = "";
            while (!VerifyInputs.verifyNoInput(modelNo, INFO_LEN.MODEL_NO.getLength(), false)
                    || !VerifyInputs.verifyModelExists(modelNo)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Model No"));
                System.out.print("Model No: ");
                modelNo = scanner.nextLine();
            }
            equipmentRecord.addModelNo(modelNo);
        }
        FieldSelection();
        return;
    }

    private void Manufacturer() {
        boolean confirm = true;
        if (!equipmentRecord.getManufacturer().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String manu = "";
            while (!VerifyInputs.verifyStringOnly(manu)) {
                System.out.println(LineGenerator.generateLine("Enter the equipment's manufacturer"));
                System.out.print("Manufacturer: ");
                manu = scanner.nextLine();
            }
            equipmentRecord.addManufacturer(manu);
        }
        FieldSelection();
        return;
    }

    private void SerialNo() {
        System.out.println("We are in serial No");

        boolean confirm = true;
        if (!equipmentRecord.getSerialNo().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        // TODO:Might have to check the logic here
        if (confirm) {
            System.out.println("confirm is " + confirm);
            String serialNo = "";
            while (!VerifyInputs.verifySerialNo(serialNo) || VerifyInputs.verifySerialNoExistOnly(serialNo)) {
                System.out.println(LineGenerator.generateLine("Enter the equipment's serial no"));
                System.out.print("Serial No: ");
                serialNo = scanner.nextLine();
            }
            equipmentRecord.addSerialNo(serialNo);
        }
        FieldSelection();
        return;
    }

}
