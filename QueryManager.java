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

    public static ResultPackage getSearchEquip(String name) {

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

    public static void updateRentEquipment(String type, String delivery, String returnDate, String pickup) {

    }

    public static ResultPackage getRentingCheckouts(String member_ID) {

        String sql = "SELECT Member_id, Count(Rental_no) AS Total_Rented_Items FROM MEMBER AS M JOIN RENTAL AS R ON M.Member_id = R.Member_id WHERE Member_id = ?";

        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setString(1, member_ID);
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

}
