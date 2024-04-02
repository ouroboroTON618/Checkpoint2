
import java.util.HashMap;
import java.util.Scanner;

public class EditDeleteRecordsPage {
    private String type;
    private Scanner scanner;
    private EquipManager equipList;

    public EditDeleteRecordsPage(EquipManager equipmentList) {

        equipList = equipmentList;
        scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------");
        System.out.println("-----------------------Edit/Delete Records Page----------------------");
        // MenuItems();

    }

    private void MainOrChange() {
        System.out.println(
                "-------------------------Please Select (S) to return to Options Menu-------------------");
        System.out.println(
                "-------------------------Please Select a Menu Option Or M to return to Main Page-------------------");

    }

    private void MenuItems() {

        System.out.println("---------------------------Edit/Delete Records -------------------------------");
        System.out.println("--------------------------Option(A): Edit Existing Record" + "------------------------");

        System.out.println("--------------------------Option(B): Delete Existing Record " + "------------------------");

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
                EditOption();
                break;
            case 'b':
                DeleteOption();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void DeleteOption() {
        System.out.println("---------------------------Delete Records -------------------------------");
        System.out.println("--------------------------(H): View Existing Records " + "------------------------");

        System.out.println("--------------------------Otherwise: Enter serial number of record to delete: "
                + "------------------------");
        MainOrChange();
        String menuOption = scanner.nextLine();
        while (menuOption.toLowerCase().equals("h")) {
            // display the equipment list and its serial numbers.

            System.out.println("--------------------------Otherwise: Enter serial number of record to delete: "
                    + "------------------------");
            MainOrChange();
            menuOption = scanner.nextLine();

        }

        DeleteOptionSelect(menuOption);

    }

    private void DeleteOptionSelect(String menuOption) {
        // if menu option is in the table of existing serial numbers, then run delete on
        // it
        // place holder code for now until we query the existing serial numbers
        if (true) {
            DeleteExistingRecord(menuOption);
        } else if (menuOption.toLowerCase().equals("m")) {

            // return to main page
        } else if (menuOption.toLowerCase().equals("s")) {
            MenuItems();
        } else {
            System.out.println(
                    "This is not a Serial Number. Please try again or check the list of valid serial numbers.");
            DeleteOption();
        }
    }

    private void DeleteExistingRecord(String menuOption) {
        equipList.DeleteEquipment(Integer.parseInt(menuOption));
    }

    private void EditOption() {
        System.out.println("---------------------------Edit Records -------------------------------");
        System.out.println("--------------------------(H): View Existing Records " + "------------------------");

        System.out.println("--------------------------Otherwise: Enter serial number of record to edit: "
                + "------------------------");

        MainOrChange();

        String menuOption = scanner.nextLine();
        while (menuOption.toLowerCase().equals("h")) {
            // display the equipment list and its serial numbers.

            System.out.println("--------------------------Otherwise: Enter serial number of record to edit: "
                    + "------------------------");
            MainOrChange();

            menuOption = scanner.nextLine();

        }
        EditOptionSelect(menuOption);
    }

    private void EditOptionSelect(String menuOption) {
        // if menu option is in the table of existing serial numbers, then run edit on
        // it
        // place holder code for now until we query the existing serial numbers
        if (true) {
            EditExistingRecord(menuOption);
        } else if (menuOption.toLowerCase().equals("m")) {

            // return to main page
        } else if (menuOption.toLowerCase().equals("s")) {
            MenuItems();
        } else {
            System.out.println(
                    "This is not a Serial Number. Please try again or check the list of valid serial numbers.");
            EditOption();
        }
    }

    private void EditExistingRecord(String menuOption) {

        // Get name of menu option, current placeholder is implemented
        String equipName = "Equipment Name Placeholder(SerialNumber)";
        String[] editableFields = ParseEditableFields(menuOption);
        System.out.println(
                "------------------Edit" + equipName + "--------------------------");
        DisplayTable(menuOption, editableFields);

        System.out.println(
                "------------------Editable (1)" + editableFields[0] + "--------------------------");
        System.out.println(
                "------------------Editable (2)" + editableFields[1] + "--------------------------");
        System.out.println(
                "------------------Editable (3)" + editableFields[2] + "--------------------------");
        System.out.println(
                "------------------Please Choose a Field to Edit: # --------------------------");
        MainOrChange();

        String fieldOption = scanner.nextLine();
        EditField(menuOption, fieldOption);
    }

    private void EditField(String menuOption, String fieldOption) {
        System.out.println(
                "------------------To Cancel: (C)--------------------------");
        System.out.println(
                "------------------Change data to:--------------------------");

        String updateValue = scanner.nextLine();

        if (updateValue.toLowerCase().equals("c")) {
            EditExistingRecord(menuOption);

        } else {
            String isSuccessMessage = UpdateData(updateValue);
            System.out.println("------------------" + isSuccessMessage + "----------------------------");
        }
    }

    private String UpdateData(String updateValue) {
        // Call the query manager to update it and pass it a specific value
        // have the query manager return a success message or a error message of what
        // went wrong.
        return "Successful";
    }

    private String[] ParseEditableFields(String menuOption) {
        // make a call to query manager to get the column names of editable fields.
        String[] placeholder = { "field1", "field2", "field3" };
        return placeholder;
    }

    private String[][] GetSerialNumberSpecificTable(String menuOption) {

        // this will be a call to a query manager and the return type of the query
        // manager will ber String[][]
        String[][] placeholder = {
                { "data1", "data1", "data1" },
                { "data2", "data2", "data2" },
                { "data3", "data3", "data3" } };
        return placeholder;
    }

    private void DisplayTable(String menuOption, String[] placeholder) {
        System.out.println(
                "------------------Table--------------------------");
        String[][] tableData = GetSerialNumberSpecificTable(menuOption);
        TableDisplayGenerator.GenerateTableWithData(placeholder, tableData);
    }
}
