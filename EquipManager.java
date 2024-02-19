import java.util.ArrayList;

public class EquipManager {
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

    public Equipment SearchEquipment(int serialNumber) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getSerialNumber() == (serialNumber)) {
                System.out.println("Equipment found!");
                System.out.println();
                return equipment;
            }
        }

        System.out.println("ERROR: Equipment NOT found");
        return null;
    }
}
