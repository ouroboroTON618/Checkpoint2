import java.util.Scanner;

public class RentEquipmentPage {

    private String type;
    private int serialNumber;
    private Scanner scanner;

    public RentEquipmentPage() {
        scanner = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------");
        System.out.println("-----------------------Equipment Rental Page----------------------");

    }

    private void RentEquipment() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("-------------Please Enter an Equipment Type to Rent---------------");
        System.out.println("----------To see what equipment is available: Press H-------------");
        String input = scanner.nextLine();
        InputParser(input.toLowerCase());

    }

    private void InputParser(String input) {
        if (input.equals("h")) {
            System.out.println("Demo Equipment 1");
        }
    }

}