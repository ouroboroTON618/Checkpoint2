import java.util.Scanner;

public class EquipmentFactory {
    public static Equipment createEquipment(int serialNumber, String manufacturer, String type) {
        return new Equipment(serialNumber, manufacturer, type);
    }

    public static Equipment GenerateEquip(Scanner scanner) {
        int serialNumber;
        String manufacturer;
        String type;
        System.out.println("Answer the following questions:");
        System.out.println("Serial Number: ");
        serialNumber = scanner.nextInt();

        System.out.println("manufacturer: ");
        manufacturer = scanner.nextLine();

        System.out.println("type: ");
        type = scanner.nextLine();
        
        return createEquipment(serialNumber, manufacturer, type);
    }


}
