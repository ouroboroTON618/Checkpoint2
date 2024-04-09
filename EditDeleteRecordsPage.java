
import java.util.*;

public class EditDeleteRecordsPage {
    private String type;
    private Scanner scanner;
    private HashMap<Integer, String> optionMap;

    public EditDeleteRecordsPage() {

        optionMap = new HashMap<>();
        CreateMap();

        scanner = new Scanner(System.in);
        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Edit/Delete Records Page"));

        MenuItems();

    }

    private void CreateMap() {
        // Provided list of strings
        String[] strings = {
                "Serial_no", "Manufacturer", "Rental_no", "Type", "Model_no",
                "Description", "Condition", "Length", "Width", "Height", "Weight",
                "Warrant_exp", "Year", "Rental_rate", "Rental_status", "Purchase_pr",
                "Order_no", "Est_arr", "Arr", "Due_date", "Pickup", "Addit_fees",
                "Return_cond"
        };

        // Populate the map with keys incrementing by 1 and strings as values
        for (int i = 0; i < strings.length; i++) {
            optionMap.put(i + 1, strings[i]);
        }
    }

    private void MenuItems() {

        System.out.println(LineGenerator.generateLine("Edit/Delete Records"));
        System.out.println(LineGenerator.generateLine("Option(A): Edit Existing Record"));
        System.out.println(LineGenerator.generateLine("Option(B): Delete Existing Record"));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or M to return to Main Page"));
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
        return;
    }

    private void DeleteOption() {
        System.out.println(LineGenerator.generateLine("Delete Records"));
        System.out.println(LineGenerator.generateLine("(H): View Existing Records"));
        System.out.println(LineGenerator.generateLine("Otherwise: Enter serial number of record to delete:"));

        LineGenerator.MainOrChange();
        System.out.print("Option: ");
        String menuOption = scanner.nextLine();
        while (menuOption.toLowerCase().equals("h")) {
            // display the equipment list and its serial numbers.
            System.out.println(LineGenerator.generateLine("Otherwise: Enter serial number of record to delete:"));
            LineGenerator.MainOrChange();
            System.out.print("Option: ");
            menuOption = scanner.nextLine();
        }

        DeleteOptionSelect(menuOption);
        return;
    }

    private void DeleteOptionSelect(String menuOption) {
        // if menu option is in the table of existing serial numbers, then run delete on
        // it
        // place holder code for now until we query the existing serial numbers
        if (true) {
            DeleteExistingRecord(menuOption);
        } else if (menuOption.toLowerCase().equals("m")) {
            Main.DisplayMenuPrompt();
        } else if (menuOption.toLowerCase().equals("s")) {
            MenuItems();
        } else {
            System.out.println(
                    "This is not a Serial Number. Please try again or check the list of valid serial numbers.");
            DeleteOption();
        }
        return;

    }

    private void DeleteExistingRecord(String menuOption) {
        // Implement Later
    }

    private void EditOption() {
        System.out.println(LineGenerator.generateLine("Edit Record"));
        System.out.println(LineGenerator.generateLine("(H): View Existing Records"));
        LineGenerator.MainOrChange();
        System.out.println(LineGenerator.generateLine("Otherwise: Enter serial number of record to edit"));

        String menuOption = scanner.nextLine();
        while (menuOption.toLowerCase().equals("h")) {
            // display the equipment list and its serial numbers.
            System.out.println("Display Place holder table that shows equip list and its serial numbers");
            LineGenerator.MainOrChange();
            System.out.println(LineGenerator.generateLine("Otherwise: Enter serial number of record to edit"));
            System.out.print("Option: ");
            menuOption = scanner.nextLine();

        }
        EditOptionSelect(menuOption);
        return;
    }

    private void EditOptionSelect(String menuOption) {
        // if menu option is in the table of existing serial numbers, then run edit on
        // it
        // place holder code for now until we query the existing serial numbers
        if (menuOption.toLowerCase().equals("m")) {
            Main.DisplayMenuPrompt();
        } else if (menuOption.toLowerCase().equals("s")) {
            MenuItems();
        } else if (true) {
            EditExistingRecord(menuOption);
        } else {
            System.out.println(
                    "This is not a Serial Number. Please try again or check the list of valid serial numbers.");
            EditOption();
        }
        return;
    }

    private void EditExistingRecord(String menuOption) {

        // Get name of menu option, current placeholder is implemented
        String equipName = "Equipment Name Placeholder(SerialNumber)";
        String[] editableFields = ParseEditableFields(menuOption);

        System.out.println(LineGenerator.generateLine("Edit" + equipName));
        System.out.println(LineGenerator.generateLine("Please Enter the Exact Name of the Field to Edit"));

        DisplayTable(menuOption, editableFields);
        LineGenerator.MainOrChange();

        String fieldOption = scanner.nextLine();
        EditField(menuOption, fieldOption);
        return;
    }

    private void EditField(String menuOption, String fieldOption) {
        System.out.println(LineGenerator.generateLine("To Cancel: (C)"));
        System.out.print("Change data to: ");

        String updateValue = scanner.nextLine();

        if (updateValue.toLowerCase().equals("c")) {
            EditExistingRecord(menuOption);

        } else {
            String isSuccessMessage = UpdateData(updateValue, fieldOption);
            System.out.println(LineGenerator.generateLine(isSuccessMessage));
        }
        return;
    }

    private String UpdateData(String updateValue, String fieldOption) {
        // Call the query manager to update it and pass it a specific value
        // have the query manager return a success message or a error message of what
        // went wrong.
        if (Main.databaseEnabled) {
            String result = QueryManager.updateFieldRecords(fieldOption, updateValue);
            return result;
        } else {
            return "Database Disabled: Dta Updated!";
        }

    }

    private String[] ParseEditableFields(String menuOption) {
        // make a call to query manager to get the column names of editable fields.
        String[] placeholder = { "field1", "field2", "field3" };
        return placeholder;
    }

    private List<List<String>> GetSerialNumberSpecificTable(String menuOption) {

        // this will be a call to a query manager and the return type of the query
        // manager will ber String[][]
        String[][] placeholder = {
                { "data1", "data1", "data1" },
                { "data2", "data2", "data2" },
                { "data3", "data3", "data3" } };

        List<List<String>> placeholderList = new ArrayList<>();
        for (String[] row : placeholder) {
            List<String> rowList = new ArrayList<>();
            for (String value : row) {
                rowList.add(value);
            }
            placeholderList.add(rowList);
        }
        return placeholderList;
    }

    private void DisplayTable(String menuOption, String[] placeholder) {
        System.out.println(LineGenerator.generateLine("Table"));
        List<List<String>> tableData = GetSerialNumberSpecificTable(menuOption);
        TableDisplayGenerator.GenerateTableWithData(placeholder, tableData);
        return;
    }
}
