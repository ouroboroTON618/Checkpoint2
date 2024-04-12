

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QueryConnection {
    // This class will set up the entire connection part of the database.

    private static String DATABASE = "RentalServiceFinal.db";

    public static Connection conn;

    public QueryConnection() {
        conn = initializeDB(DATABASE);

        System.out.println("Connection Established");
    }

    public Connection GetConnection() {
        return conn;
    }

    public void closeConnection(boolean exit) {

        if (!exit) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Connects to the database if it exists, creates it if it does not, and returns
     * the connection object.
     * 
     * @param databaseFileName the database file name
     * @return a connection object to the designated database
     */
    public Connection initializeDB(String databaseFileName) {
        /**
         * The "Connection String" or "Connection URL".
         * 
         * "jdbc:sqlite:" is the "subprotocol".
         * (If this were a SQL Server database it would be "jdbc:sqlserver:".)
         */
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null; // If you create this variable inside the Try block it will be out of scope
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                // Provides some positive assurance the connection and/or creation was
                // successful.
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("The connection to the database was successful.");
            } else {
                // Provides some feedback in case the connection failed but did not throw an
                // exception.
                System.out.println("Null Connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("There was a problem connecting to the database.");
        }
        return conn;
    }

}
