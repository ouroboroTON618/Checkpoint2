import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static String addNewRecord(HashMap<String, String> inputs) {

        String sql = "INSERT INTO EQUIPMENT (Serial_no, Manufacturer, Rental_no, Type, Model_no, Description, Condition, Length, Width, Height, Weight, Warrant_exp, Year, Rental_rate, Rental_status, Purchase_pr, Order_no, Est_arr, Arr, Due_date, Pickup, Addit_fees, Return_cond;) VALUES(?,...,?)";

        try {
            ps = Main.conn.prepareStatement(sql);
            int count = 1;
            for (String x : inputs.values()) {
                count += 1;
                ps.setString(count, x);
            }
            return QueryPrepare.updateQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static ResultPackage getEquipment(String name) {

        String sql = "SELECT * FROM EQUIPMENT WHERE EQUIPMENT.Type = ?;";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setString(1, name);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ResultPackage getEquipmentTypes() {
        String sql = "SELECT Type FROM EQP_TYPE";
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
        String sql = "SELECT * FROM EQP_TYPE WHERE EQP_TYPE.Type = ?;";
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

    public static String updateRentEquipment(String type, String delivery, String returnDate, String pickup,
            String purchase, String memberID, String rentalNo) {
        String sql = "";
        // String sql = "CREATE PROCEDURE RENTAL(
        // IN user_member_id VARCHAR(),
        // IN user_item_manuf VARCHAR()
        // IN user_drone_serial_no VARCHAR(),
        // IN user_drone_type VARCHAR(),
        // IN user_location VARCHAR(),
        // IN user_item_serial_no VARCHAR(),
        // IN user_rental_no VARCHAR(),
        // )
        // BEGIN
        // INSERT INTO RENTAL (Rental_no, Member_id, Date_time)
        // VALUES (â€˜user_rental_noâ€™, â€˜user_member_idâ€™, CURRENT_TIME);
        // UPDATE RENTAL
        // SET Rental_status = â€˜Returnedâ€™
        // WHERE Rental_no = â€˜user_rental_noâ€™;
        // UPDATE DRONE
        // SET Deliv_status = 'En-Route', Location = â€˜user_locationâ€™
        // WHERE Serial_no = â€˜user_drone_serial_noâ€™;
        // INSERT INTO DELIVERY (Drone_serial_no, Drone_type, Rental_no, Item_serial_no,
        // Item_manuf)
        // VALUES (â€˜user_Drone_serial_noâ€™, â€˜user_Drone_typeâ€™,
        // â€˜user_Rental_noâ€™, â€˜user_Item_serial_noâ€™, â€˜user_Item_manufâ€™);
        // UPDATE DRONE
        // SET Deliv_status = 'En-Route', Location = â€˜user_locationâ€™
        // WHERE Serial_no = â€˜user_drone_serial_noâ€™;
        // INSERT INTO RETURN (Drone_serial_no, Drone_type, Rental_no, Item_serial_no,
        // Item_manuf)
        // VALUES (â€˜user_Drone_serial_noâ€™, â€˜user_Drone_typeâ€™,
        // â€˜user_Rental_noâ€™, â€˜user_Item_serial_noâ€™, â€˜user_Item_manufâ€™); ";

        // return QueryPrepare.updateQuery(Main.conn, sql);
        return null;
    }

    public static ResultPackage getRentingCheckouts(int member_ID) {        
        String sql = "SELECT M.Member_id, Count(R.Rental_no) AS Total_Rented_Items "
        		+ "FROM MEMBER AS M NATURAL JOIN RENTAL AS R NATURAL JOIN EQUIPMENT "
        		+ "WHERE M.Member_id = ?";

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
        		+ "FROM EQUIPMENT AS E WHERE E.Arr IS NOT NULL GROUP BY Serial_no, Manufacturer ORDER BY Rental_Hours DESC";
        return QueryPrepare.sqlQuery(Main.conn, sql);
    }

    public static ResultPackage getPopularManufacturer() {
    	   String sql = "SELECT Manufacturer, MAX(Count) AS Count\r\n"
    	   		+ "    FROM (SELECT Manufacturer, COUNT(*) AS Count\r\n"
    	   		+ "          FROM EQUIPMENT\r\n"
    	   		+ "          GROUP BY Manufacturer);";
        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getPopularDrone() {    	
    	String sql = "SELECT Drone_serial_no, Drone_type, Dist, IIF(Count IS NULL, 0, Count) AS Count\r\n"
    			+ "FROM (SELECT Drone_serial_no, Drone_type, SUM(Distance) AS Dist\r\n"
    			+ "    FROM (SELECT * FROM DELIVERY UNION SELECT * FROM RETURN AS R)\r\n"
    			+ "    JOIN DRONE AS DR\r\n"
    			+ "    ON Drone_serial_no = Serial_no\r\n"
    			+ "    AND Drone_type = Type_id\r\n"
    			+ "    GROUP BY Drone_serial_no, Drone_type) NATURAL LEFT JOIN\r\n"
    			+ "    (SELECT Drone_serial_no, Drone_type, COUNT(*) AS Count\r\n"
    			+ "    FROM DELIVERY JOIN DRONE \r\n"
    			+ "    ON Drone_serial_no = Serial_no\r\n"
    			+ "    AND Drone_type = Type_id\r\n"
    			+ "    GROUP BY Drone_serial_no, Drone_type)\r\n"
    			+ "ORDER BY Count DESC\r\n"
    			+ "LIMIT 1;";

        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getItemsCheckedOut() {
    	String sql = "SELECT Member_id, Max(No_items) AS Max_item_count\r\n"
    			+ "FROM (SELECT Member_id, COUNT(*) AS No_items\r\n"
    			+ "FROM EQUIPMENT NATURAL JOIN RENTAL\r\n"
    			+ "GROUP BY Member_id)";

        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getEquipmentByTypeOfEquipment(int year) {    	
    	String sql = "SELECT Type, MIN(Description) AS Description, MIN(Year) AS Year \r\n"
    			+ "FROM EQP_TYPE \r\n"
    			+ "    NATURAL JOIN EQP_ITEM \r\n"
    			+ "    WHERE Year < ?\r\n"
    			+ "    GROUP BY Type;";

        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, year);
//            ps.setString(2, type);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
