
import java.util.*;

public class AddRecords {

    private Scanner scanner;
    private HashMap<String, String> inputs;
    private HashSet<String> skipInputs;
    private EquipmentObject equipmentRecord;

    public AddRecords() {

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
                String result = QueryManager.addNewRecord(inputs);
                System.out.println(result);
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
        System.out.println(LineGenerator.generateLine("Option (C): Rental_no  : " + equipmentRecord.getRentalNo()));
        System.out.println(LineGenerator.generateLine("Option (D): Type  : " + equipmentRecord.getType()));
        System.out.println(LineGenerator.generateLine("Option (E): Model_no  : " + equipmentRecord.getModelNo()));
        System.out
                .println(LineGenerator.generateLine("Option (F): Description  : " + equipmentRecord.getDescription()));
        System.out.println(LineGenerator.generateLine("Option (G): Condition  : " + equipmentRecord.getCondition()));
        System.out.println(LineGenerator.generateLine("Option (H): Length  : " + equipmentRecord.getLength()));
        System.out.println(LineGenerator.generateLine("Option (I): Width  : " + equipmentRecord.getWidth()));
        System.out.println(LineGenerator.generateLine("Option (J): Height  : " + equipmentRecord.getHeight()));
        System.out.println(LineGenerator.generateLine("Option (K): Weight  : " + equipmentRecord.getWeight()));
        System.out.println(LineGenerator.generateLine("Option (L): Warrant_exp  : " + equipmentRecord.getWarrantExp()));
        System.out.println(LineGenerator.generateLine("Option (M): Year  : " + equipmentRecord.getYear()));
        System.out.println(LineGenerator.generateLine("Option (N): Rental_rate  : " + equipmentRecord.getRentalRate()));
        System.out.println(
                LineGenerator.generateLine("Option (O): Rental_status  : " + equipmentRecord.getRentalStatus()));
        System.out.println(LineGenerator.generateLine("Option (P): Purchase_pr  : " + equipmentRecord.getPurchasePr()));
        System.out.println(LineGenerator.generateLine("Option (Q): Order_no  : " + equipmentRecord.getOrderNo()));
        System.out.println(LineGenerator.generateLine("Option (R): Est_arr  : " + equipmentRecord.getEstArr()));
        System.out.println(LineGenerator.generateLine("Option (S): Arr  : " + equipmentRecord.getArr()));
        System.out.println(LineGenerator.generateLine("Option (T): Due_date  : " + equipmentRecord.getDueDate()));
        System.out.println(LineGenerator.generateLine("Option (U): Pickup  : " + equipmentRecord.getPickup()));
        System.out.println(LineGenerator.generateLine("Option (V): Addit_fees  : " + equipmentRecord.getAdditFees()));
        System.out.println(LineGenerator.generateLine("Option (W): Return_cond  : " + equipmentRecord.getReturnCond()));
        System.out.println(LineGenerator.generateLine("Option (Y): EXIT FIELD SELECTION"));
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
                System.out.println("This is going into serial no");
                SerialNo();
                break;
            case 'b':
                Manufacturer();
                break;
            case 'c':
                // skip rentalNo
                System.out.println("Don't think this needs to be part of the query");
                break;
            case 'd':
                Type();
                break;
            case 'e':
                ModelNo();
                break;
            case 'f':
                Description();
                break;
            case 'g':
                Condition();
                break;
            case 'h':
                Length();
                break;
            case 'i':
                Width();
                break;
            case 'j':
                Height();
                break;
            case 'k':
                Height();
                break;
            case 'l':
                WarrantExp();
                break;
            case 'm':
                Year();
                break;
            case 'n':
                RentalRate();
                break;
            case 'o':
                RentalRate();
                break;
            case 'p':
                System.out.println("Don't think this needs to be part of the query");
                // System.out.println("Option (a-z): Purchase_pr");
                break;
            case 'q':
                System.out.println("Don't think this needs to be part of the query");
                // System.out.println("Option (a-z): Order_no");
                break;
            case 'r':
                System.out.println("Don't think this needs to be part of the query");
                // System.out.println("Option (a-z): Est_arr");
                break;
            case 's':
                System.out.println("Don't think this needs to be part of the query");
                // System.out.println("Option (a-z): Arr");
                break;
            case 't':
                System.out.println("Don't think this needs to be part of the query");
                // System.out.println("Option (a-z): Due_date");
                break;
            case 'u':
                System.out.println("Don't think this needs to be part of the query");
                // System.out.println("Option (a-z): Pickup");
                break;
            case 'v':
                System.out.println("Don't think this needs to be part of the query");
                // System.out.println("Option (a-z): Addit_fees");
                break;
            case 'w':
                System.out.println("Don't think this needs to be part of the query");
                // ReturnCond();
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

    private void ReturnCond() {
        // boolean confirm = true;
        // if (!equipmentRecord.getModelNo().equals("null")) {
        // System.out.println("You Already Filled This Section. Would You Like to Edit?
        // (Y/N)");
        // System.out.print("Option: ");
        // String input = scanner.nextLine();
        // confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        // }

        // if (confirm) {
        // String modelNo = "";
        // while (!VerifyInputs.verifyNoInput(modelNo, INFO_LEN.MODEL_NO.getLength())) {
        // System.out.println(LineGenerator.generateLine("Enter the Equipment's Model
        // No"));
        // System.out.print("Model No: ");
        // modelNo = scanner.nextLine();
        // }
        // }
        System.out.println("Delete later");
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

    private void Height() {
        boolean confirm = true;
        if (!equipmentRecord.getHeight().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String height = "";
            while (!VerifyInputs.verifyNoInput(height, 0, true)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Height"));
                System.out.print("Height: ");
                height = scanner.nextLine();
            }
            equipmentRecord.addHeight(height);
        }
        FieldSelection();
        return;
    }

    private void Width() {
        boolean confirm = true;
        if (!equipmentRecord.getWidth().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String width = "";
            while (!VerifyInputs.verifyNoInput(width, 0, true)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's width"));
                System.out.print("Width: ");
                width = scanner.nextLine();
            }
            equipmentRecord.addWidth(width);
        }
        FieldSelection();
        return;
    }

    private void Length() {
        boolean confirm = true;
        if (!equipmentRecord.getLength().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String length = "";
            while (!VerifyInputs.verifyNoInput(length, 0, true)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Length"));
                System.out.print("Length: ");
                length = scanner.nextLine();
            }
            equipmentRecord.addLength(length);
        }
        FieldSelection();
        return;
    }

    private void Condition() {
        boolean confirm = true;
        if (!equipmentRecord.getCondition().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String cond = "";
            while (!VerifyInputs.verifyCond(cond)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Condition"));
                System.out.print("Condition: ");
                cond = scanner.nextLine();
            }
            equipmentRecord.addCondition(cond);
        }
        FieldSelection();
        return;
    }

    private void Description() {
        boolean confirm = true;
        if (!equipmentRecord.getDescription().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String desc = "";
            while (!VerifyInputs.verifyStringOnly(desc)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Description"));
                System.out.print("Description: ");
                desc = scanner.nextLine();
            }
            equipmentRecord.addDescription(desc);
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
            while (!VerifyInputs.verifyNoInput(modelNo, INFO_LEN.MODEL_NO.getLength(), false)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Model No"));
                System.out.print("Model No: ");
                modelNo = scanner.nextLine();
            }
            equipmentRecord.addModelNo(modelNo);
        }
        FieldSelection();
        return;
    }

    private void Type() {
        boolean confirm = true;
        if (!equipmentRecord.getType().equals("null")) {
            System.out.println("You Already Filled This Section. Would You Like to Edit? (Y/N)");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            confirm = (input.toLowerCase().charAt(0) == 'y') ? true : false;
        }

        if (confirm) {
            String type = "";
            while (!VerifyInputs.verifyStringOnly(type)) {
                System.out.println(LineGenerator.generateLine("Enter the Equipment's Type"));
                System.out.print("Type: ");
                type = scanner.nextLine();
            }
            equipmentRecord.addType(type);

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

        if (confirm) {
            System.out.println("confirm is " + confirm);
            String serialNo = "";
            while (!VerifyInputs.verifySerialNo(serialNo)) {
                System.out.println("verify is " + false);
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
