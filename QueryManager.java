import java.sql.Connection;
import java.sql.PreparedStatement;
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

        //String sql = "SELECT Member_id, Count(Rental_no) AS Total_Rented_Items FROM MEMBER AS M JOIN RENTAL AS R ON M.Member_id = R.Member_id WHERE Member_id = ?";
        
        String sql = "SELECT M.Member_id, Count(R.Rental_no) AS Total_Rented_Items FROM MEMBER AS M JOIN RENTAL AS R ON M.Member_id = R.Member_id WHERE M.Member_id = ?";

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

//        String sql = "SELECT COUNT(DISTINCT Serial_no) AS Count_dist_item "
//                + "FROM EQUIPMENT "
//                + "GROUP BY Manufacturer) "
//                + "HAVING MAX(Count_dist_item)) ";
    	   String sql = """
                   SELECT Manufacturer, COUNT(DISTINCT Serial_no) AS Count_dist_item
                   FROM EQUIPMENT
                   GROUP BY Manufacturer
                   ORDER BY Count_dist_item DESC
                   LIMIT 1;
                   """;
        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getPopularDrone() {

//        String sql = "SELECT Serial_no, Manufacturer, DATEDIFF(second, Arr, IF (Pickup IS NULL, GETDATE(), Pickup))/3600.0 AS Rental_Hours "
//                + "FROM ITEM "
//                + "WHERE I.Arr IS NOT NULL) "
//                + "GROUP BY Serial_no, Manufacturer "
//                + "ORDER BY Total_Rental_Hours DESC)";
    	String sql = """
                SELECT Serial_no, Manufacturer,
                (STRFTIME('%s', Pickup) - STRFTIME('%s', Arr)) / 3600.0 AS Rental_Hours
                FROM EQUIPMENT
                WHERE Arr IS NOT NULL AND Pickup IS NOT NULL
                ORDER BY Rental_Hours DESC;
                """;

        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getItemsCheckedOut() {

//        String sql = "SELECT M.First_name, M.Last_name, COUNT() AS Count "
//                + "FROM (EQUIPMENT AS E JOIN RENTAL AS R ON E.Rental_no = R.RENTAL) AS X "
//                + "JOIN MEMBER AS M ON X.Member_id = M.Member_id "
//                + "GROUP BY M.Member_id "
//                + "ORDER BY COUNT() DESC "
//                + "LIMIT 1;"
//                + "SELECT M.First_name, M.Last_name, M.Email "
//                + "FROM MEMBER AS M "
//                + "JOIN (SELECT M.Member_id, COUNT(*) AS Count "
//                + "FROM EQUIPMENT AS E "
//                + "JOIN RENTAL AS R ON E.Rental_no = R.RENTAL "
//                + "JOIN MEMBER AS M ON R.Member_id = M.Member_id "
//                + "GROUP BY M.Member_id) AS Rental_Counts ON M.Member_id = Rental_Counts.Member_id "
//                + "ORDER BY Rental_Counts.Count DESC "
//                + "LIMIT 1;";
        
        String sql = "SELECT M.First_name, M.Last_name, COUNT() AS Count "
                + "FROM (EQUIPMENT AS E JOIN RENTAL AS R ON E.Rental_no = R.Rental_no) AS X "
                + "JOIN MEMBER AS M ON X.Member_id = M.Member_id "
                + "GROUP BY M.Member_id "
                + "ORDER BY COUNT() DESC "
                + "LIMIT 1;"
                + "SELECT M.First_name, M.Last_name, M.Email "
                + "FROM MEMBER AS M "
                + "JOIN (SELECT M.Member_id, COUNT(*) AS Count "
                + "FROM EQUIPMENT AS E "
                + "JOIN RENTAL AS R ON E.Rental_no = R.Rental_no "
                + "JOIN MEMBER AS M ON R.Member_id = M.Member_id "
                + "GROUP BY M.Member_id) AS Rental_Counts ON M.Member_id = Rental_Counts.Member_id "
                + "ORDER BY Rental_Counts.Count DESC "
                + "LIMIT 1;";
        
        return QueryPrepare.sqlQuery(Main.conn, sql);

    }

    public static ResultPackage getEquipmentByTypeOfEquipment(int year, String type) {

//        String sql = "SELECT Description FROM EQUIPMENT WHERE Year < ? AND Type = ? ";
    	String sql = """
                SELECT T.Description
                FROM EQUIPMENT AS E
                JOIN EQP_TYPE AS T ON E.Manufacturer = T.Manufacturer AND E.Model_no = T.Model_no
                WHERE E.Year < ? AND T.Type = ?;
                """;


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

}
