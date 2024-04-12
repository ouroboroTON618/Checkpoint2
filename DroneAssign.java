
import java.util.Scanner;

public class DroneAssign {

    public static Scanner scan = new Scanner(System.in);

    public static String FindDrone(int serialno) {

        // Find drone models id's and their model id info of avaliable drones that meet
        // this info
    	
    
        ResultPackage result = QueryManager.getRequiredDrone();
        
        TableDisplayGenerator.GenerateTable(result);
        // single drone id type
        String serialNo_Drone = droneID();

        return serialNo_Drone;
    }

    public static void AssignDrone(int droneNo, int rentalNo, int itemSerialNo, boolean delivery) {

        String result = "";
        if (delivery) {
        	System.out.println();
        	System.out.println("Assigning Drone....");
        	System.out.println();
            String resultL = QueryManager.addNewDeliveryRecord(rentalNo, droneNo, itemSerialNo);
            System.out.println(result);
            
            ResultPackage dataResult = QueryManager.getDroneDeliveryStatus(rentalNo);

            if(!dataResult.getData().isEmpty()) {
                System.out.println(LineGenerator.generateLine("Drone has been assigned"));
                System.out.println();
            	TableDisplayGenerator.GenerateTable(dataResult);
            }

        } else {
        	result = QueryManager.addNewReturnRecord(droneNo, rentalNo, itemSerialNo);

            ResultPackage dataResult = QueryManager.getReturnRecords(rentalNo);
            System.out.println();
            System.out.println(
                    LineGenerator.generateLine(
                            "Your part of the return process has been completed. Please wait for drone to complete the return."));

            System.out.println(LineGenerator.generateLine("Returning Items:"));
            TableDisplayGenerator.GenerateTable(dataResult);
            

        }

        System.out.println(result);
    }

    public static void BeginDelivery(int droneNo) {

        System.out.println(LineGenerator.generateLine("Drone " + droneNo + " will be on it's way!"));
        String resultS = QueryManager.updateDroneDelivery(droneNo);
        ResultPackage result = QueryManager.getDroneDeliveryStatus(droneNo);
        
        if(!result.getData().isEmpty()) {
	        TableDisplayGenerator.GenerateTable(result);
	    }
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

    public static void ScheduleDrone(int item_serial_number, int rentalNo, boolean delivery) {
    	String droneId = FindDrone(item_serial_number);
    	
    	
        int droneSerial = Integer.parseInt(droneId);
        AssignDrone(droneSerial, rentalNo, item_serial_number, delivery);
        BeginDelivery(droneSerial);
    }

    public static void DroneFinished(int droneSerial) {
        String resultUpdate = QueryManager.updateDroneDeliveryStatus(droneSerial);
        ResultPackage result = QueryManager.getDroneDeliveryStatus(droneSerial);
        TableDisplayGenerator.GenerateTable(result);
    }
}
