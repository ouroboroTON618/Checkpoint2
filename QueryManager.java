import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryManager {
    // Every single query call to the database will be called here:

    // methods follow the format of public static
    // GetTableFields(requiredParamters1,.....);
    private static PreparedStatement ps;

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
        String sql = "SELECT Serial_no, Manufacturer, DATED IFF(second, Arr, IF (Pickup IS NULL, GETDATE(), Pickup))/3600.0 AS Rental_Hours FROM ITEM WHERE I.Arr IS NOT NULL) GROUP BY Serial_no, Manufacturer ORDER BY Total_Rental_Hours DESC)";
        return QueryPrepare.sqlQuery(Main.conn, sql);
    }

    public static ResultPackage getPopularManufacturer() {

        String sql = "SELECT COUNT(DISTINCT Serial_no) AS Count_dist_item FROM ITEM GROUP BY Manufacturer) HAVING MAX(Count_dist_item)) ";
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

        String sql = "SELECT D.Drone_serial_no, D.Drone_type, SUM(D.Distance) AS D_dist " +
                "FROM DELIVERY AS D " +
                "JOIN DRONE AS DR " +
                "ON D.Drone_serial_no = DR.Serial_no " +
                "AND D.Drone_type = DR.Type_id " +
                "GROUP BY D.Drone_serial_no, D.Drone_type; " +

                "SELECT R.Drone_serial_no, R.Drone_type, SUM(R.Distance) AS R_dist " +
                "FROM RETURN AS R " +
                "JOIN DRONE AS DR " +
                "ON R.Drone_serial_no = DR.Serial_no " +
                "AND R.Drone_type = DR.Type_id " +
                "GROUP BY R.Drone_serial_no, R.Drone_type; " +

                "SELECT D.Drone_serial_no, D.Drone_type, (D_dist + R_dist) AS Total_miles " +
                "FROM DELIVERY_ITEMS AS D " +
                "NATURAL JOIN RETURN_ITEMS " +
                "ORDER BY D.D_dist DESC;";

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
