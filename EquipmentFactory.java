import java.util.Scanner;

public class EquipmentFactory {
    public static Equipment CreateEquipment(int serialNumber, String manufacturer, String type) {
        return new Equipment(serialNumber, manufacturer, type);
    }

    public static Equipment GenerateEquip(Scanner scanner) {
        int serialNumber;
        String manufacturer;
        String type;
        System.out.println("Input equipment details:");
        System.out.println("Serial Number: ");
        serialNumber = scanner.nextInt();

        System.out.println("manufacturer: ");
        manufacturer = scanner.next();

        System.out.println("type: ");
        type = scanner.next();

        return CreateEquipment(serialNumber, manufacturer, type);
    }

}
