
import java.util.Scanner;

public class ReturnRentalPage {

    private String type;
    private Scanner scanner;

    public ReturnRentalPage() {

        scanner = new Scanner(System.in);
        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Rental Return Page"));
        MenuItems();
    }

    private void MenuItems() {

        System.out.println(LineGenerator.generateLine("Option(A): Return Item"));
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
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void ReturnItem() {
    	 System.out.print("Enter the Item's Rental No: ");
         String rentalNo = scanner.nextLine();
         
         
         
    }

//    private void DisplayResults(Type key, String value) {
//       // ResultPackage result = GetQuery(key, value);
//
//        if (result != null) {
//            TableDisplayGenerator.GenerateTableWithData(result.getHeaderFields(), result.getTableData());
//        }
//        MenuItems();
//    }

}
