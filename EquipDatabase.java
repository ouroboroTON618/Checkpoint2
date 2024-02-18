import java.util.ArrayList;

public class EquipDatabase {
    ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
    
    public void EquipmentManager() {
        this.equipmentList = new ArrayList<>();
    }

    public void AddEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    public void DeleteEquipment(int serialNumber) {
        equipmentList.removeIf(equipment -> equipment.getSerialNumber() == (serialNumber));
    }
}
