
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReturnRentalPage {

    private String type;
    private Scanner scanner;
    private String curr_rentalNo;

    public ReturnRentalPage() {

        scanner = new Scanner(System.in);
        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Rental Return Page"));
        MenuItems();
    }

    private void MenuItems() {

        System.out.println(LineGenerator.generateLine("Option(A): Return Item"));
        System.out.println(LineGenerator.generateLine("Option(B): Schedule Pickup"));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));
        MenuSelect();
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
                ReturnItem();
                break;

            case 'b':
                SchedulePickUp();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void SchedulePickUp() {
        if (RentalNo()) {

            String serialNo = SerialNumber();
            String pickUpdate = PickUp();
            int auditFee = AuditFee(pickUpdate, serialNo);

            // Insert into Query

            String updateResult = QueryManager.updateScheduleRentalInfo(serialNo, pickUpdate, auditFee);

            // Do Drone scheulding
            DroneAssign.ScheduleDrone(Integer.parseInt(serialNo), Integer.parseInt(curr_rentalNo), false);

            if (LineGenerator.ConditionalCheck(STATEMENT.COMPLETED)) {
                System.out.println("You have finished return form for item: " + serialNo);
                System.out.println(pickUpdate + " has passed. The Drone has successfully picked your item up.");

                DroneAssign.DroneFinished(Integer.parseInt(serialNo));

                System.out.println("You have successfully returned item: " + serialNo);
                System.out.println("Here is the the item's return status: ");

                // Display the table of hte item rental listing.
                ResultPackage result = QueryManager.getRentalItemHistory(Integer.parseInt(serialNo));
                TableDisplayGenerator.GenerateTable(result);
            }
        }
    }

    private void ReturnItem() {

        if (RentalNo()) {

            String serialNo = SerialNumber();
            String conditon = ReturnCond();
            // Insert values into return

            String updateResult = QueryManager.updateRentalInfo(Integer.parseInt(serialNo), conditon);
            System.out.println(
                    LineGenerator.generateLine("Please go to schedule pickup to schedule a drone to pick up the item"));
            MenuItems();
        }
    }

    private int AuditFee(String pickString, String serialNo) {
        int auditFee = 0;
        // Get the return date of the item serial no
        ResultPackage result = returnDateQuery(Integer.parseInt(serialNo));

        String dueDate = LineGenerator.GetFirstDataVal(result);
        if (dueDate != "") {
            auditFee = late(pickString, dueDate);
        }

        return auditFee;

    }

    private int late(String pickUp, String dueDate) {

        // Create DateTimeFormatter objects for parsing the date strings
        DateTimeFormatter formatterPick = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatterDue = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int cost = 0;
        try {
            // Parse the date strings into LocalDate objects
            LocalDate local_Pick = LocalDate.parse(pickUp, formatterPick);
            LocalDate local_Due = LocalDate.parse(dueDate, formatterDue);
            // Compare the dates
            if (local_Pick.isAfter(local_Due)) {
                long daysBetween = ChronoUnit.DAYS.between(local_Pick, local_Due);
                return 5 * (int) daysBetween;
            } else {
                return cost;
            }
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return cost;
        }
    }

    private ResultPackage returnDateQuery(int serialNum) {
        if (Main.databaseEnabled) {
            return QueryManager.getDueDate(serialNum);
        } else {
            return null;
        }
    }

    private String PickUp() {
        String pickUp = "";
        while (!VerifyInputs.verifyDates(pickUp)) {
            System.out.println(LineGenerator.generateLine(
                    "Warning: You will be charged a late fee if pick up date later than the stated return date"));
            System.out.println(LineGenerator.generateLine("Enter Date for Drone Pick Up:"));
            System.out.print("Date (MM/DD/YYYY): ");
            pickUp = scanner.nextLine();
        }
        return pickUp;

    }

    private String ReturnCond() {
        String returnCond = "";
        while (!VerifyInputs.verifyCond(returnCond)) {
            System.out.println(LineGenerator.generateLine("Enter item return condition(Good/Fair/Poor): "));
            System.out.print("Condition: ");
            returnCond = scanner.nextLine();
        }
        return returnCond;
    }

    private String SerialNumber() {

        String serialNo = "";
//        while (!VerifyInputs.verifySerialNo(serialNo) && !UnreturnedSerialNo(serialNo)) {
        while (!VerifyInputs.verifySerialNo(serialNo)) {
            System.out.println(LineGenerator.generateLine("Enter serial number of item: "));
            System.out.print("Serial Number: ");
            serialNo = scanner.nextLine();
        }
        return serialNo;
    }
    
//    private boolean UnreturnedSerialNo(String SerialNo) {
//    	if (SerialNo.isEmpty()) {
//    		return false;
//    	}
//    	ResultPackage result = QueryManager.CheckUnreturnedSerial(Integer.parseInt(SerialNo));
//    	if (!result.getData().isEmpty()) {
//    		return true;
//    	}
//    	
//    	return false;
//    }

    private boolean RentalNo() {
        String rentalNo = "";
        while (!VerifyInputs.VerifyRentalNo(rentalNo)) {
            System.out.println(LineGenerator.generateLine("Enter RentalNo of Item to Return: "));
            System.out.println("Rental_NO: ");
            rentalNo = scanner.nextLine();
        }
        curr_rentalNo = rentalNo;
        return QueryRentalNo(rentalNo);
    }
//    
//    private boolean Unreturned(String rentalNo) {
//    	if (rentalNo.isEmpty()) {
//    		return false;
//    	}
//    	ResultPackage result = QueryManager.CheckUnreturnedRental(Integer.parseInt(rentalNo));
//    	if (!result.getData().isEmpty()) {
//    		return true;
//    	}
//    	
//    	return false;
//    }

    private boolean QueryRentalNo(String rentalNo) {
        if (Main.databaseEnabled) {
            ResultPackage result = QueryManager.getRentalOrder(rentalNo);
            TableDisplayGenerator.GenerateTable(result);
            return true;
        } else {
            System.out.println("Database Disabled: Rental No Item  Query Success");
            return false;
        }
    }

}
