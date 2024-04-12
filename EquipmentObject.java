import java.util.HashMap;

public class EquipmentObject {
    public static HashMap<String, String> inputs;

    public EquipmentObject() {
        inputs = new HashMap<>();
        // Initialize the map with column names and "null" values
//        inputs.put("Serial_no", "null");
//        inputs.put("Manufacturer", "null");
//
//        inputs.put("Model_no", "null");
//
//        inputs.put("Warrant_exp", "null");
//        inputs.put("Year", "null");
//        inputs.put("Rental_rate", "null");
//        inputs.put("Rental_status", "null");
//        inputs.put("Purchase_pr", "null");
//        inputs.put("Order_no", "null");
        
        
        // Initialize the map with column names and "null" values
        inputs.put("Serial_no", "123456");
        inputs.put("Manufacturer", "ava");

        inputs.put("Model_no", "930");

        inputs.put("Warrant_exp", "2024/01/22");
        inputs.put("Year", "2024");
        inputs.put("Rental_rate", "63");
        inputs.put("Rental_status", "Avaliable");
        inputs.put("Purchase_pr", "652");
        inputs.put("Order_no", "12345");
    }

    public boolean checkCompleted() {
        if (inputs.values().contains("null")) {
            return false;
        } else {
            return true;
        }
    }

    public void addSerialNo(String no) {
        inputs.put("Serial_no", no);
    }

    public void addManufacturer(String manufacturer) {
        inputs.put("Manufacturer", manufacturer);
    }

    public void addRentalNo(String rentalNo) {
        inputs.put("Rental_no", rentalNo);
    }

    public void addModelNo(String modelNo) {
        inputs.put("Model_no", modelNo);
    }

    public void addWarrantExp(String warrantExp) {
        inputs.put("Warrant_exp", warrantExp);
    }

    public void addYear(String year) {
        inputs.put("Year", year);
    }

    public void addRentalRate(String rentalRate) {
        inputs.put("Rental_rate", rentalRate);
    }

    public void addRentalStatus(String rentalStatus) {
        inputs.put("Rental_status", rentalStatus);
    }

    public void addPurchasePr(String purchasePr) {
        inputs.put("Purchase_pr", purchasePr);
    }

    public void addOrderNo(String orderNo) {
        inputs.put("Order_no", orderNo);
    }

    public String getSerialNo() {
        return inputs.get("Serial_no");
    }

    public String getManufacturer() {
        return inputs.get("Manufacturer");
    }

    public String getRentalNo() {
        return inputs.get("Rental_no");
    }

    public String getModelNo() {
        return inputs.get("Model_no");
    }

    public String getWarrantExp() {
        return inputs.get("Warrant_exp");
    }

    public String getYear() {
        return inputs.get("Year");
    }

    public String getRentalRate() {
        return inputs.get("Rental_rate");
    }

    public String getRentalStatus() {
        return inputs.get("Rental_status");
    }

    public String getPurchasePr() {
        return inputs.get("Purchase_pr");
    }

    public String getOrderNo() {
        return inputs.get("Order_no");
    }

}
