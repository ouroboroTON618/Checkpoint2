
import java.util.Scanner;

public class DroneAssign {

    public static Scanner scan = new Scanner(System.in);

    public static String FindDrone(int serialno) {

        // get item weight from serial number
        String itemWeight = QueryManager.getItemWeight(serialno);
        // get warehouse
        String warehouse = QueryManager.getWarehouse(USER_INFO.MEMBER_ID);
        // get distance of the item
        String dist = QueryManager.getItemWeight(serialno);
        // Find drone models id's and their model id info of avaliable drones that meet
        // this info
        ResultPackage result = QueryManager.getRequiredDrone(itemWeight, warehouse, dist);
        TableDisplayGenerator.GenerateTable(result);

        // single drone id type
        String serialNo_Drone = droneID();

        return serialNo_Drone;
    }

    public static void AssignDrone(int droneNo, int rentalNo, int itemSerialNo) {

        String result = QueryManager.addNewDeliveryRecord(rentalNo, droneNo, itemSerialNo);
        System.out.println(result);
    }

    public static void BeginDelivery(int droneNo) {

        System.out.println("Drone " + droneNo + " will on it's way!");
        ResultPackage result = QueryManager.updateDroneDelivery(droneNo);
        TableDisplayGenerator.GenerateTable(result);
    }

    private static String droneID() {

        String droneID = "";
        while (!VerifyInputs.verifyNoInput(droneID, 0, true)) {
            System.out.println(
                    LineGenerator.generateLine("Please select the drone serial number for the delivery/pickup: "));
            System.out.print("Drone Serial No: ");
            droneID = scan.nextLine();
        }
        return droneID;
    }

    public static void ScheduleDrone(int item_serial_number, int rentalNo) {
        int droneSerial = Integer.parseInt(FindDrone(item_serial_number));
        AssignDrone(droneSerial, rentalNo, item_serial_number);
        BeginDelivery(droneSerial);
    }

    public static void DroneFinished(int droneSerial) {
        ResultPackage result = QueryManager.updateDroneDeliveryStatus(droneSerial);
        TableDisplayGenerator.GenerateTable(result);
    }
}
