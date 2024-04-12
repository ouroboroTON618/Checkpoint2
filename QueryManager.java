import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QueryManager {
    // Every single query call to the database will be called here:

    // methods follow the format of public static
    // GetTableFields(requiredParamters1,.....);
    private static PreparedStatement ps;

    public static String updateFieldRecords(String fieldOption, String updateValue) {

        String sql = "UPDATE EQUIPMENT SET ? = ?";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setString(1, fieldOption);
            ps.setString(2, updateValue);
            return QueryPrepare.updateQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static ResultPackage getEquipment(String name) {

        String sql = "SELECT * FROM EQUIPMENT WHERE Type = ?;";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setString(1, name);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Display the type, the model number, and equipment information and equipment
     * serial number
     * 
     * @return
     */
    public static ResultPackage getEquipmentTypes() {

        String sql = "SELECT Type FROM EQUIPMENT";
        return QueryPrepare.sqlQuery(Main.conn, sql);
    }

    public static ResultPackage getMember(int member_ID) {

        String sql = "SELECT * FROM MEMBER WHERE Member_id = ?;";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, member_ID);
            ResultPackage result = QueryPrepare.sqlQuery(Main.conn, ps);
            ps.close();

            return result;
        } catch (SQLException e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ResultPackage getRecord(int serial_number) {
        // TODO: Change to equipment
        String sql = "SELECT * FROM EQUIPMENT WHERE EQUIPMENT.Type = ?;";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, serial_number);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ResultPackage getRentalNos() {
        String sql = "SELECT Rental_no FROM RENTAL";
        return QueryPrepare.sqlQuery(Main.conn, sql);
    }

    public static ResultPackage getRentingCheckouts(int member_ID) {

        String sql = "SELECT Member_id, Count(Rental_no) AS Total_Rented_Items FROM MEMBER AS M JOIN RENTAL AS R ON M.Member_id = R.Member_id WHERE Member_id = ?";

        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, member_ID);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ResultPackage getPopularItem() {
        String sql = "SELECT Serial_no, Manufacturer, DATED IFF(second, Arr, IF (Pickup IS NULL, GETDATE(), Pickup))/3600.0 AS Rental_Hours "
                + "FROM EQUIPMENT AS E "
                + "WHERE E.Arr IS NOT NULL) "
                + "GROUP BY Serial_no, Manufacturer "
                + "ORDER BY Rental_Hours DESC)";
        return QueryPrepare.sqlQuery(Main.conn, sql);
    }

    public static ResultPackage getPopularManufacturer() {

        String sql = "SELECT COUNT(DISTINCT Serial_no) AS Count_dist_item "
                + "FROM EQUIPMENT "
                + "GROUP BY Manufacturer) "
                + "HAVING MAX(Count_dist_item)) ";
        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getPopularDrone() {

        String sql = "SELECT Serial_no, Manufacturer, DATEDIFF(second, Arr, IF (Pickup IS NULL, GETDATE(), Pickup))/3600.0 AS Rental_Hours "
                + "FROM ITEM "
                + "WHERE I.Arr IS NOT NULL) "
                + "GROUP BY Serial_no, Manufacturer "
                + "ORDER BY Total_Rental_Hours DESC)";
        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getItemsCheckedOut() {

        String sql = "SELECT M.First_name, M.Last_name, COUNT() AS Count "
                + "FROM (EQUIPMENT AS E JOIN RENTAL AS R ON E.Rental_no = R.RENTAL) AS X "
                + "JOIN MEMBER AS M ON X.Member_id = M.Member_id "
                + "GROUP BY M.Member_id "
                + "ORDER BY COUNT() DESC "
                + "LIMIT 1;"
                + "SELECT M.First_name, M.Last_name, M.Email "
                + "FROM MEMBER AS M "
                + "JOIN (SELECT M.Member_id, COUNT(*) AS Count "
                + "FROM EQUIPMENT AS E "
                + "JOIN RENTAL AS R ON E.Rental_no = R.RENTAL "
                + "JOIN MEMBER AS M ON R.Member_id = M.Member_id "
                + "GROUP BY M.Member_id) AS Rental_Counts ON M.Member_id = Rental_Counts.Member_id "
                + "ORDER BY Rental_Counts.Count DESC "
                + "LIMIT 1;";
        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getEquipmentByTypeOfEquipment(int year, String type) {

        String sql = "SELECT Description FROM EQUIPMENT WHERE Year < ? AND Type = ? ";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setString(2, type);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // ------------------------Shira start here:--------------------------------
    /**
     * Get the item weight for that specific item serial number
     * 
     * @param serialno
     * @return
     */
    public static ResultPackage getItemWeight(int serialno) {
    	String sql = "SELECT Weight\r\n"
    			+ "FROM EQP_TYPE AS TYPE JOIN EQP_ITEM ITEM ON TYPE.Model_no = ITEM.Model_no\r\n"
    			+ "WHERE Serial_no = ?";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, serialno);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Gets Drone Serial num, Type id and Model no of drones that are available
     * 
     * @return
     */
    public static ResultPackage getRequiredDrone() {
        String sql = "SELECT Serial_no, Type_id, Model_no\r\n"
        		+ "FROM DRONE AS d JOIN DRONE_TYPE AS t ON d.Type_id=t.Type_id\r\n"
        		+ "WHERE d.Inactive= 'Active'";
        return QueryPrepare.sqlQuery(Main.conn, sql);
       
    }

    /**
     * Get the closest warehouse of the member based on the given member ID. MIGHT NEED FIX DUE USER_INFO
     * 
     * @param memberId
     * @return
     */
    public static ResultPackage getWarehouse(USER_INFO memberId) {
    	int member_id = (int) memberId.getValue();
        String sql = "SELECT wh.Closest_warehouse\r\n"
        		+ "FROM MEMBER AS m JOIN MEMBER_CLOSE_WH AS wh ON m.Address=wh.Address\r\n"
        		+ "WHERE m.Member_id=?";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, member_id);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Insert a new row into the Return table. These are the 3 given values, you
     * can get what u need by doing smaller queries with the paramaters provided.
     * //You can get the user's member id for warehouse info at USER_INFO.MEMBER_ID.
     * Look at utilities file
     * 
     * @param rentalNo
     * @param droneNo
     * @param itemSerialNo
     * @return
     */
    public static ResultPackage addNewReturnRecord(int Drone_serial_no, String Drone_type, int Rental_no, int Item_serial_no, String Item_manuf) {
        String sql = "INSERT INTO Return (Drone_serial_no, Drone_type, Rental_no, Item_serial_no, Item_manuf) VALUES (?, ?, ?, ?, ?)\r\n"
        		+ "";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, Drone_serial_no);
            ps.setString(2, Drone_type);
            ps.setInt(3, Rental_no);
            ps.setInt(4, Item_serial_no);
            ps.setString(5, Item_manuf);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    /**
     * Update drone delivery Inactive status inactive and delivery status to in
     * transt. Make sure these are actually ment for item delivery w kjell/chat i
     * already pinged him. If it isn't for delivery, then ignore
     * 
     * @param droneNo
     * @return
     */
    public static ResultPackage updateDroneDelivery(int droneNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDroneDelivery'");
    }

    /**
     * Update drone delivery Inactive status inactive and delivery status to in
     * transt. Make sure these are actually ment for item delivery w kjell/chat i
     * already pinged him. If it isn't for delivery, then ignore
     */
    public static ResultPackage updateDroneDeliveryStatus(int droneSerial) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDroneDeliveryStatus'");
    }

    /**
     * gET the due date of the item by serial num
     * 
     * @param serialNum
     * @return
     */
    public static ResultPackage getDueDate(int serialNum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDueDate'");
    }

    /**
     * Update these paramaters in Equipment table for the Serial No
     * 
     * @param serialNo
     * @param pickUpdate
     * @param auditFee
     * @return
     */
    public static String updateScheduleRentalInfo(String serialNo, String pickUpdate, int auditFee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateScheduleRentalInfo'");
    }

    /**
     * Get all relevant info to display the rental info of item serial no in
     * equipment table
     * 
     * @param int1
     * @return
     */
    public static ResultPackage getRentalItemHistory(int serial) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRentalItemHistory'");
    }

    /**
     * Select all the serial numbers for that specific rental no.
     * 
     * @param rentalNo
     * @return
     */
    public static ResultPackage getRentalOrder(String rentalNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRentalOrder'");
    }

    /**
     * Insert a new row into the Delivery table. These are the 3 given values, you
     * can get what u need by doing smaller queries with the paramaters provided.
     * //You can get the user's member id for warehouse info at USER_INFO.MEMBER_ID.
     * Look at utilities file
     */
    public static ResultPackage addNewDeliveryRecord(int rentalNo, int droneNo, int itemSerialNo) {
    	String sql = "INSERT INTO DELIVERY (Drone_serial_no, Drone_type, Rental_no, Item_serial_no, Item_manuf) "
    			+ "VALUES (?, (SELECT Type_id FROM DRONE WHERE Serial_no = ?), ?, ?, (SELECT Manufacturer FROM EQUIPMENT WHERE Serial_no = ?))";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, droneNo);
            ps.setInt(2, droneNo);
            ps.setInt(3, rentalNo);
            ps.setInt(4, itemSerialNo);
            ps.setInt(5, itemSerialNo);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Get all the rows from delivery table that is of the rental No given
     * 
     * @param rentalNo
     * @return
     */

    public static ResultPackage getDeliveryRecords(int rentalNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeliveryRecords'");
    }

    /**
     * Get all rows from the rental table that meets the rental no given
     * 
     * @param rentalNo
     * @return
     */
    public static ResultPackage getReturnRecords(int rentalNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReturnRecords'");
    }

    /**
     * Update the equipment table conditon the serial number paramter.
     * 
     * @param int1
     * @param conditon
     * @return
     */
    public static String updateRentalInfo(int item_serialNo, String conditon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRentalInfo'");
    }

    /**
     * Get all the serial Numbers in Equipment table. Only 1 column
     * 
     * @return
     */
    public static ResultPackage getSerialNo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSerialNo'");
    }

    /**
     * Update Equipment with the Arr = date for item serial no = serialNo
     * 
     * @param int1
     * @param date
     * @return
     */
    public static String updateDeliveryDateDelivered(int serialNo, String date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDeliveryDateDelivered'");
    }

    /**
     * Get the drone serialID in Delivery when item serial no = serial Item and
     * rental number = rental NO
     * 
     * @param serialItem
     * @param rentalNo
     * @return
     */
    public static ResultPackage getDroneID_Item_Rental(int serialItem, int rentalNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDroneID_Item_Rental'");
    }

    // ---------------Adding New
    // Item--------------------------------------------------
    /**
     * Add a new entry into Equip_Item table
     * 
     * Look at Equipment Object for the paramters
     */
    public static String addNewEquipItem(EquipmentObject item) {

        // String sql = "INSERT INTO EQUIPMENT (Serial_no, Manufacturer, Rental_no,
        // Type, Model_no, Description, Condition, Length, Width, Height, Weight,
        // Warrant_exp, Year, Rental_rate, Rental_status, Purchase_pr, Order_no,
        // Est_arr, Arr, Due_date, Pickup, Addit_fees, Return_cond;) VALUES(?,...,?)";

        // try {
        // ps = Main.conn.prepareStatement(sql);
        // int count = 1;

        // return QueryPrepare.updateQuery(Main.conn, ps);
        // } catch (SQLException e) {
        // System.out.println(e.getMessage());
        // return null;
        // }
        return null;

    }

    //-------------------------------tina-------------------------
    /**
     * Get all the model Numbers from equp_type
     * 
     * @return
     */
    public static ResultPackage getModelNo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModelNo'");
    }

    /**
     * Add a new record inside Equipment table
     * 
     * @param item
     * @return
     */
    public static String addRentEquipment(EquipmentRentalObject item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRentEquipment'");
    }

    /**
     * Get all the serial numbers of avaliable items based on specific type
     * 
     * @param type
     * @return
     */
    public static ResultPackage getSerialByType(String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSerialByType'");
    }

    /**
     * Get the type of the item based in specific serial number
     * 
     * @param int1
     * @return
     */
    public static ResultPackage getTypeBySerial(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTypeBySerial'");
    }

    /**
     * Get all items with the same rental No from Equipment
     * 
     * @param rentalNo
     * @return
     */
    public static ResultPackage getBulkOrder(String rentalNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBulkOrder'");
    }
    
    public static Date convertDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate;
        try {
            utilDate = sdf.parse(date);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {

            e.printStackTrace();
            return null;
        } 
    }

}
