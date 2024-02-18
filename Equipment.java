public class Equipment {
    
    private int serialNumber;
    private String manufacturer;
    private String type;
    private int modelNumber;
    private String description;
    private int length;
    private int width;
    private int height;
    private int weight;
    private int warrExpr;
    private int year;
    private int rentRate;
    private boolean status; 

    public Equipment(int serialNumber, String manufacturer, String type) {
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

}
