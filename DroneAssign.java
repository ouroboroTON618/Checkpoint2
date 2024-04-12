
import java.util.Scanner;

public class DroneAssign {

    public static Scanner scan = new Scanner(System.in);

    public static String FindDrone(int serialno) {

        // Find drone models id's and their model id info of avaliable drones that meet
        // this info
    	
    	System.out.println("We are in find drone");
        ResultPackage result = QueryManager.getRequiredDrone();
        
        TableDisplayGenerator.GenerateTable(result);
        // single drone id type
        String serialNo_Drone = droneID();

        return serialNo_Drone;
    }

    public static void AssignDrone(int droneNo, int rentalNo, int itemSerialNo, boolean delivery) {

        String result = "";
        if (delivery) {
            String resultL = QueryManager.addNewDeliveryRecord(rentalNo, droneNo, itemSerialNo);
            ResultPackage dataResult = QueryManager.getDeliveryRecords(rentalNo);

            System.out.println(LineGenerator.generateLine("Drone has been assigned"));
            TableDisplayGenerator.GenerateTable(dataResult);

        } else {

            ResultPackage result1 = QueryManager.getDroneType(droneNo);
            String type = LineGenerator.GetFirstDataVal(result1);

            ResultPackage result2 = QueryManager.getManufact(itemSerialNo);
            String manu = LineGenerator.GetFirstDataVal(result2);

            result = QueryManager.addNewReturnRecord(rentalNo, type, droneNo, itemSerialNo, manu);
            // int Drone_serial_no, String Drone_type, int Rental_no,
            // int Item_serial_no, String Item_manuf

            ResultPackage dataResult = QueryManager.getReturnRecords(rentalNo);
            System.out.println(
                    LineGenerator.generateLine(
                            "Your part of the return process has been completed. Please wait for drone to complete the return."));

            System.out.println(LineGenerator.generateLine("Returning Items:"));
            TableDisplayGenerator.GenerateTable(dataResult);

        }

        System.out.println(result);
    }

    public static void BeginDelivery(int droneNo) {

        System.out.println(LineGenerator.generateLine("Drone " + droneNo + " will on it's way!"));
        String resultS = QueryManager.updateDroneDelivery(droneNo);
        ResultPackage result = QueryManager.getDeliveryRecords(droneNo);
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
        
        System.out.println("This is drone ID" );
        return droneID;
    }

    public static void ScheduleDrone(int item_serial_number, int rentalNo, boolean delivery) {
    	String droneId = FindDrone(item_serial_number);
    	
    	System.out.println("This is drone ID");
        int droneSerial = Integer.parseInt(droneId);
        AssignDrone(droneSerial, rentalNo, item_serial_number, delivery);
        BeginDelivery(droneSerial);
    }

    public static void DroneFinished(int droneSerial) {
        String resultUpdate = QueryManager.updateDroneDeliveryStatus(droneSerial);
        ResultPackage result = QueryManager.getDeliveryRecords(droneSerial);
        TableDisplayGenerator.GenerateTable(result);
    }
}
