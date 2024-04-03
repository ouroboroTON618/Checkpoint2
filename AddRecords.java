
import java.util.*;

public class AddRecords {

    private Scanner scanner;
    private HashMap<String, String> inputs;
    private HashSet<String> skipInputs;

    public AddRecords() {
        CreateMap();

        scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------");
        System.out.println("-----------------------Add Records Page----------------------");
        MenuItems();

    }

    private void CreateMap() {
        inputs = new HashMap<>();

        // Initialize the map with column names and "null" values
        inputs.put("Serial_no", "null");
        inputs.put("Manufacturer", "null");
        inputs.put("Rental_no", "null");
        inputs.put("Type", "null");
        inputs.put("Model_no", "null");
        inputs.put("Description", "null");
        inputs.put("Condition", "null");
        inputs.put("Length", "null");
        inputs.put("Width", "null");
        inputs.put("Height", "null");
        inputs.put("Weight", "null");
        inputs.put("Warrant_exp", "null");
        inputs.put("Year", "null");
        inputs.put("Rental_rate", "null");
        inputs.put("Rental_status", "null");
        inputs.put("Purchase_pr", "null");
        inputs.put("Order_no", "null");
        inputs.put("Est_arr", "null");
        inputs.put("Arr", "null");
        inputs.put("Due_date", "null");
        inputs.put("Pickup", "null");
        inputs.put("Addit_fees", "null");
        inputs.put("Return_cond", "null");

        skipInputs = new HashSet<>(
                Arrays.asList("Return_cond", "Addit_fees", "Pickup", "Due_date", "Arr", "Est_arr", "Order_no"));

    }

    private void MainOrChange() {
        System.out.println(
                "-------------------------Please Select (S) to return to Options Menu-------------------");
        System.out.println(
                "-------------------------Please Select a Menu Option Or M to return to Main Page-------------------");

    }

    private void MenuItems() {

        System.out.println("---------------------------Add Records -------------------------------");
        System.out.println("--------------------------Option(A): Add" + "------------------------");

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
                Add();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void Add() {
        HashMap<String, String> temp = new HashMap<>(inputs);

        for (String x : temp.keySet()) {

            if (!skipInputs.contains(x)) {
                System.out.println("Enter a value for " + x + ": ");
                String input = scanner.nextLine();
                inputs.put(x, input);
            }
        }
        String result = QueryManager.addNewRecord(inputs);
        System.out.println(result);
        MenuItems();

    }

}
