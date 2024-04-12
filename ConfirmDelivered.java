
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ConfirmDelivered {

    private String type;
    private Scanner scanner;
    private String curr_rentalNo;

    public ConfirmDelivered() {

        scanner = new Scanner(System.in);
        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Confirm Item Delivered Page"));
        MenuItems();
    }

    private void MenuItems() {

        System.out.println(LineGenerator.generateLine("Option(A): Confirm Item Delivered"));
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
                ConfirmDelivery();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void ConfirmDelivery() {

        if (RentalNo()) {
            String serialNo = SerialNumber();
            String date = DateArrived();

            UpdateDatabase(serialNo, date);
            DisplayDatabase(serialNo);
        }
        MenuItems();

    }

    private void DisplayDatabase(String serialNo) {
        ResultPackage result = QueryManager.getRecord(Integer.parseInt(serialNo));
        TableDisplayGenerator.GenerateTable(result);
    }

    private void UpdateDatabase(String serialNo, String date) {
        String result = QueryManager.updateDeliveryDateDelivered(Integer.parseInt(serialNo),Integer.parseInt(curr_rentalNo), date);
        ResultPackage droneIDResult = QueryManager.getDroneID_Item_Rental(Integer.parseInt(serialNo),
                Integer.parseInt(curr_rentalNo));
        String droneSerial = LineGenerator.GetFirstDataVal(droneIDResult);
        DroneAssign.DroneFinished(Integer.parseInt(droneSerial));
        System.out.println(LineGenerator
                .generateLine("Delivery Data for item [" + serialNo + "] has been updated! The drone has returned"));
    }

    private String DateArrived() {
        String date = "";
        while (!VerifyInputs.verifyDates(date)) {
            System.out.println(LineGenerator.generateLine("Enter date of when Item arrived(MM/DD/YYYY)"));
            System.out.print("Date: ");
            date = scanner.nextLine();
        }
        return date;

    }

    private String SerialNumber() {

        String serialNo = "";
        while (!VerifyInputs.verifySerialNoExist(serialNo)) {
            System.out.println(LineGenerator.generateLine("Enter serial number of item that has been delivered: "));
            System.out.print("Serial Number: ");
            serialNo = scanner.nextLine();
        }
        return serialNo;
    }

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
