import java.util.HashMap;

public class EquipmentObject {
    public static HashMap<String, String> inputs;

    public EquipmentObject() {
        inputs = new HashMap<>();
        // Initialize the map with column names and "null" values
        inputs.put("Serial_no", "null");
        inputs.put("Manufacturer", "null");
        inputs.put("Rental_no", "null");
        inputs.put("Type", "null");
        inputs.put("Model_no", "null");
        inputs.put("Description", "null");
        inputs.put("Condition", "null");
        inputs.put("Length", "null");
        inputs.put("Width", "null");
        inputs.put("Height", "null");
        inputs.put("Weight", "null");
        inputs.put("Warrant_exp", "null");
        inputs.put("Year", "null");
        inputs.put("Rental_rate", "null");
        inputs.put("Rental_status", "null");
        inputs.put("Purchase_pr", "null");
        inputs.put("Order_no", "null");
        inputs.put("Est_arr", "null");
        inputs.put("Arr", "null");
        inputs.put("Due_date", "null");
        inputs.put("Pickup", "null");
        inputs.put("Addit_fees", "null");
        inputs.put("Return_cond", "null");
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

    public void addType(String type) {
        inputs.put("Type", type);
    }

    public void addModelNo(String modelNo) {
        inputs.put("Model_no", modelNo);
    }

    public void addDescription(String description) {
        inputs.put("Description", description);
    }

    public void addCondition(String condition) {
        inputs.put("Condition", condition);
    }

    public void addLength(String length) {
        inputs.put("Length", length);
    }

    public void addWidth(String width) {
        inputs.put("Width", width);
    }

    public void addHeight(String height) {
        inputs.put("Height", height);
    }

    public void addWeight(String weight) {
        inputs.put("Weight", weight);
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

    public void addEstArr(String estArr) {
        inputs.put("Est_arr", estArr);
    }

    public void addArr(String arr) {
        inputs.put("Arr", arr);
    }

    public void addDueDate(String dueDate) {
        inputs.put("Due_date", dueDate);
    }

    public void addPickup(String pickup) {
        inputs.put("Pickup", pickup);
    }

    public void addAdditFees(String additFees) {
        inputs.put("Addit_fees", additFees);
    }

    public void addReturnCond(String returnCond) {
        inputs.put("Return_cond", returnCond);
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

    public String getType() {
        return inputs.get("Type");
    }

    public String getModelNo() {
        return inputs.get("Model_no");
    }

    public String getDescription() {
        return inputs.get("Description");
    }

    public String getCondition() {
        return inputs.get("Condition");
    }

    public String getLength() {
        return inputs.get("Length");
    }

    public String getWidth() {
        return inputs.get("Width");
    }

    public String getHeight() {
        return inputs.get("Height");
    }

    public String getWeight() {
        return inputs.get("Weight");
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

    public String getEstArr() {
        return inputs.get("Est_arr");
    }

    public String getArr() {
        return inputs.get("Arr");
    }

    public String getDueDate() {
        return inputs.get("Due_date");
    }

    public String getPickup() {
        return inputs.get("Pickup");
    }

    public String getAdditFees() {
        return inputs.get("Addit_fees");
    }

    public String getReturnCond() {
        return inputs.get("Return_cond");
    }

}
