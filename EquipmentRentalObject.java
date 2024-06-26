import java.util.HashMap;

public class EquipmentRentalObject {

    public static HashMap<String, String> inputs;

    public EquipmentRentalObject() {
        inputs = new HashMap<>();
        // Initialize the map with column names and "null" values
        HashMap<String, Object> inputs = new HashMap<>();
        inputs.put("Type", null);
        inputs.put("Serial_no", null);
        inputs.put("Order_no", null);
        inputs.put("Model_no", null);
        inputs.put("Manufacturer", null);
        inputs.put("Rental_no", null);
        // inputs.put("Purchase_price", null);
        inputs.put("Est_arr", null);
        inputs.put("Due_date", null);
        inputs.put("Customer_cost", null);
    }

    public boolean checkCompleted() {
        if (inputs.values().contains(null)) {
            return false;
        } else {
            return true;
        }
    }

    public String getType() {
        return String.valueOf(inputs.get("Type"));
    }

    public String getSerialNo() {
        return String.valueOf(inputs.get("Serial_no"));
    }

    public String getRentalNo() {
        return String.valueOf(inputs.get("Rental_no"));
    }
    
    public String getManu() {
        return String.valueOf(inputs.get("Manufacturer"));
    }
    public String getOrderNo() {
        return String.valueOf(inputs.get("Order_no"));
    }
    public String getModelNo() {
        return String.valueOf(inputs.get("Model_no"));
    }

    public String getEstArr() {
        Object value = inputs.get("Est_arr");
        return value == null ? null : value.toString();
    }

    public String getDueDate() {
        Object value = inputs.get("Due_date");
        return value == null ? null : value.toString();
    }

    public String getCustomerCost() {
        return String.valueOf(inputs.get("Customer_cost"));
    }

    public void setType(String value) {
        inputs.put("Type", value);
    }

    public void setSerialNo(String value) {
        inputs.put("Serial_no", value);
        
        
        ResultPackage resultOrderNo = QueryManager.getOrderNo(Integer.parseInt(value));
        String val1 = LineGenerator.GetFirstDataVal(resultOrderNo);
        ResultPackage resultModelNo = QueryManager.getModelNo(Integer.parseInt(value));
        String val = LineGenerator.GetFirstDataVal(resultModelNo);
        
        ResultPackage resultManu = QueryManager.getManufact(Integer.parseInt(val));
        String val3 = LineGenerator.GetFirstDataVal(resultManu);
        inputs.put("Order_no",val1 );
        inputs.put("Model_no", val);
        inputs.put("Manufacturer", val3);
        
    }

    public void setRentalNo(String value) {
        inputs.put("Rental_no", value);
    }

    public void setEstArr(String value) {
        inputs.put("Est_arr", value);
    }

    public void setDueDate(String value) {
        inputs.put("Due_date", value);
    }

    public void setCustomerCost(String value) {
        inputs.put("Customer_cost", value);
    }

}
