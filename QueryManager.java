import java.sql.Connection;
import java.sql.Date;
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
    public static String getItemWeight(int serialno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemWeight'");
    }

    /**
     * Need the drone serial numbers and their model id numbers and all of their
     * model id information of drones that are can carry the item weight, is located
     * at hte same warehouse and can travel to the user. The drone's Inactive and
     * delivery status must be avaliable
     * May not need the dist attrivute. You can change all values into int's
     * 
     * @param itemWeight
     * @param warehouse
     * @param dist
     * @return
     */
    public static ResultPackage getRequiredDrone(String itemWeight, String warehouse, String dist) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRequiredDrone'");
    }

    /**
     * Get the warehouse of the member based on the given member ID
     * 
     * @param memberId
     * @return
     */
    public static String getWarehouse(USER_INFO memberId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWarehouse'");
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
    public static String addNewReturnRecord(int rentalNo, int droneNo, int itemSerialNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewDeliveryRecord'");
    }

    /**
     * Update drone delivery Inactive status inactive and delivery status to in
     * transt. Make sure these are actually ment for item delivery w kjell/chat i
     * already pinged him. If it isn't for delivery, then ignore
     * 
     * @param droneNo
     * @return
     */
    public static String updateDroneDelivery(int droneNo) {
    	String sql = "UPDATE DELIVERY SET Drone_status = 'In Transit' WHERE Drone_serial_no = ?;";
		
    	 try {
             ps = Main.conn.prepareStatement(sql);
             ps.setInt(1, droneNo);
             return QueryPrepare.updateQuery(Main.conn, ps);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             return null;
         }
    }

    /**
     * Update drone delivery Inactive status inactive and delivery status to in
     * transt. Make sure these are actually ment for item delivery w kjell/chat i
     * already pinged him. If it isn't for delivery, then ignore
     */
    public static String updateDroneDeliveryStatus(int droneSerial) {
    	String sql = "UPDATE DELIVERY SET Drone_status = 'Avaliable' WHERE Drone_serial_no = ?;";
    		
     	 try {
              ps = Main.conn.prepareStatement(sql);
              ps.setInt(1, droneSerial);
              return QueryPrepare.updateQuery(Main.conn, ps);
          } catch (SQLException e) {
              System.out.println(e.getMessage());
              return null;
          }
    }

    /**
     * gET the due date of the item by serial num
     * 
     * @param serialNum
     * @return
     */
    public static ResultPackage getDueDate(int serialNum) {

    	String sql = "SELECT Due_date FROM EQUIPMENT WHERE Serial_no = ?;";
      	 try {
               ps = Main.conn.prepareStatement(sql);
               ps.setInt(1, serialNum);
               return QueryPrepare.sqlQuery(Main.conn, ps);
           } catch (SQLException e) {
               System.out.println(e.getMessage());
               return null;
           }

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

    	String sql = "UPDATE EQUIPMENT SET Pickup = ?, Addit_fees = ? WHERE Serial_no = ?;";
      	 try {
               ps = Main.conn.prepareStatement(sql);
               ps.setDate(1, convertDate(pickUpdate));
               ps.setInt(2, auditFee);
               ps.setInt(3, Integer.parseInt(serialNo));
               return QueryPrepare.updateQuery(Main.conn, ps);
           } catch (SQLException e) {
               System.out.println(e.getMessage());
               return null;
           }
    }

    
    /**
     * Get all relevant info to display the rental info of item serial no in
     * equipment table
     * 
     * @param int1
     * @return
     */
    public static ResultPackage getRentalItemHistory(int serial) {
    	String sql = "SELECT * FROM EQUIPMENT WHERE Serial_no = ?;";
   	 try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, serial);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Select all the serial numbers for that specific rental no.
     * 
     * @param rentalNo
     * @return
     */
    public static ResultPackage getRentalOrder(String rentalNo) {

    	String sql = "SELECT Serial_no FROM RENTAL WHERE Rental_no = ?;";
    	 try {
             ps = Main.conn.prepareStatement(sql);
             ps.setInt(1, Integer.parseInt(rentalNo));
             return QueryPrepare.sqlQuery(Main.conn, ps);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             return null;
         }
    }

    /** --------PUT ON PAUSE---------SKIPPED
     * Insert a new row into the Delivery table. These are the 3 given values, you
     * can get what u need by doing smaller queries with the paramaters provided.
     * //You can get the user's member id for warehouse info at USER_INFO.MEMBER_ID.
     * Look at utilities file
     */
    public static String addNewDeliveryRecord(int rentalNo, int droneNo, int itemSerialNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewDeliveryRecord'");
    }

    
    /**
     * Get all the rows from delivery table that is of the rental No given
     * 
     * @param rentalNo
     * @return
     */

    public static ResultPackage getDeliveryRecords(int rentalNo) {
    	String sql = "SELECT * FROM DELIVERY WHERE Rental_no = ?;";
   	 try {
         ps = Main.conn.prepareStatement(sql);
        
         ps.setInt(1, rentalNo);
         return QueryPrepare.sqlQuery(Main.conn, ps);
     } catch (SQLException e) {
         System.out.println(e.getMessage());
         return null;
     }
    }

    /**
     * Get all rows from the rental table that meets the rental no given
     * 
     * @param rentalNo
     * @return
     */
    public static ResultPackage getReturnRecords(int rentalNo) {
    	String sql = "SELECT * FROM RENTAL WHERE Rental_no = ?;";
    	
      	 try {
            ps = Main.conn.prepareStatement(sql);
           
            ps.setInt(1, rentalNo);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Update the equipment table conditon the serial number paramter.
     * 
     * @param int1
     * @param conditon
     * @return
     */
    public static String updateRentalInfo(int item_serialNo, String conditon) {
    	String sql = "UPDATE EQUIPMENT SET Return_cond = ? WHERE Serial_no = ?;";
    	
   	 try {
         ps = Main.conn.prepareStatement(sql);
         ps.setString(1, conditon);
         ps.setInt(2, item_serialNo);
         return QueryPrepare.updateQuery(Main.conn, ps);
     } catch (SQLException e) {
         System.out.println(e.getMessage());
         return null;
     }
	

    	
    	
    }

    
    /**
     * Get all the serial Numbers in Equipment table. Only 1 column
     * 
     * @return
     */
    public static ResultPackage getSerialNo() {
      
    	String sql = "SELECT Serial_no FROM EQUIPMENT;";
    	 try {
             ps = Main.conn.prepareStatement(sql);
             return QueryPrepare.sqlQuery(Main.conn, ps);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             return null;
         }
    	
    }

    
    /**
     * Update Equipment with the Arr = date for item serial no = serialNo
     * 
     * @param int1
     * @param date
     * @return
     */
    public static String updateDeliveryDateDelivered(int serialNo, int rentalNo, String date) {
       
    	String sql = "UPDATE EQUIPMENT SET Arr = ? WHERE Serial_no = ? AND Rental_no = ?;";
    	 try {
             ps = Main.conn.prepareStatement(sql);
             ps.setInt(1, serialNo);
             ps.setDate(2, convertDate(date));
             ps.setInt(3, rentalNo);
             return QueryPrepare.updateQuery(Main.conn, ps);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             return null;
         }

    	
    	
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
    	String sql = "SELECT Drone_serial_no FROM DELIVERY WHERE Item_serial_no = ? AND Rental_no = ?;";
    	 try {
             ps = Main.conn.prepareStatement(sql);
             ps.setInt(1, serialItem);
             ps.setInt(2, rentalNo);
             return QueryPrepare.sqlQuery(Main.conn, ps);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             return null;
         }
    }
    
    
    

    // ---------------Adding New (SKIPPPPedm COME BACK LATER)
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

    /**
     * Get all the model Numbers from equp_type
     * 
     * @return
     */
    public static ResultPackage getModelNo() {
    	String sql = "SELECT Model_no FROM EQP_TYPE";
    	
   	 try {
            ps = Main.conn.prepareStatement(sql);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /** ---------Not Working------------------
     * Add a new record inside Equipment table
     * 
     * @param item
     * @return
     */
    public static String addRentEquipment(EquipmentRentalObject item) {
        String sql = "INSERT INTO EQUIPMENT (Serial_no, Manufacturer, Rental_no,Model_no,Order_no, Est_arr, Due_date) VALUES (?, ?, ?, ?,?,?,?);";
   
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(item.getSerialNo()));
            ps.setString(2, item.getManu());
            ps.setInt(3, Integer.parseInt(item.getRentalNo()));
            ps.setInt(4, Integer.parseInt(item.getModelNo()));
            ps.setInt(5, Integer.parseInt(item.getOrderNo()));
            ps.setDate(6, convertDate(item.getEstArr()));
            ps.setDate(7, convertDate(item.getDueDate()));
            return QueryPrepare.updateQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Get all the serial numbers of avaliable items based on specific type
     * @param type
     * @return
     */
    public static ResultPackage getSerialByType(String type) {
    	String sql = "SELECT EQP_ITEM.Serial_no, EQP_TYPE.Type,EQP_ITEM.Rental_status,EQP_ITEM.Rental_rate FROM EQP_ITEM INNER JOIN EQP_TYPE ON EQP_ITEM.Manufacturer = EQP_TYPE.Manufacturer AND EQP_ITEM.Model_no = EQP_TYPE.Model_no WHERE EQP_TYPE.Type = ? AND EQP_ITEM.Rental_status = 'Available';";
    	
    	 try {
             ps = Main.conn.prepareStatement(sql);
             ps.setString(1, type);
             return QueryPrepare.sqlQuery(Main.conn, ps);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             return null;
         }
    	
    }

    /**
     * Get the type of the item based in specific serial number
     * 
     * @param int1
     * @return
     */
    public static ResultPackage getTypeBySerial(int serialNo) {
    	
         String sql = "SELECT EQP_TYPE.Type FROM EQP_ITEM INNER JOIN EQP_TYPE ON EQP_ITEM.Manufacturer = EQP_TYPE.Manufacturer AND EQP_ITEM.Model_no = EQP_TYPE.Model_no WHERE EQP_ITEM.Serial_no = ?; ";
         try {
             ps = Main.conn.prepareStatement(sql);
             ps.setInt(1, serialNo);
             return QueryPrepare.sqlQuery(Main.conn, ps);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
             return null;
         }
    	
    	
    	
    }

    /**
     * Get all items with the same rental No from Equipment
     * 
     * @param rentalNo
     * @return
     */
    public static ResultPackage getBulkOrder(String rentalNo) {
        int rental = Integer.parseInt(rentalNo);
        String sql = "SELECT Serial_no,Manufacturer,Rental_no ,Rental_rate,Est_arr,Arr,Due_date,Pickup,Addit_fees,Return_cond FROM EQUIPMENT WHERE Rental_no = ?;";
        try {
            ps = Main.conn.prepareStatement(sql);
            ps.setInt(1, rental);
            return QueryPrepare.sqlQuery(Main.conn, ps);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
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

    /**
     * Gets the model no of the item
     * @param parseInt
     * @return
     */
	public static ResultPackage getModelNo(int serial) {
	     
	        String sql = "SELECT EQUIPMENT.Model_no FROM EQUIPMENT INNER JOIN EQP_ITEM ON EQUIPMENT.Serial_no = EQP_ITEM.Serial_no WHERE EQUIPMENT.Serial_no = ?;";
	        try {
	            ps = Main.conn.prepareStatement(sql);
	            ps.setInt(1, serial);
	            return QueryPrepare.sqlQuery(Main.conn, ps);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return null;
	        }
	}

	/**
	 * Gets the order no 
	 * @param parseInt
	 * @return
	 */
	public static ResultPackage getOrderNo(int parseInt) {
		String sql = "SELECT Order_no FROM EQP_ITEM WHERE Serial_no = ?;";
		  try {
	            ps = Main.conn.prepareStatement(sql);
	            ps.setInt(1, parseInt);
	            return QueryPrepare.sqlQuery(Main.conn, ps);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return null;
	        }
	}

	public static ResultPackage getManufact(int serialNum) {
	
		
		String sql = "SELECT Manufacturer FROM EQP_ITEM WHERE Serial_no = ?;";
				  try {
			            ps = Main.conn.prepareStatement(sql);
			            ps.setInt(1, serialNum);
			            return QueryPrepare.sqlQuery(Main.conn, ps);
			        } catch (SQLException e) {
			            System.out.println(e.getMessage());
			            return null;
			        }
	}

}
