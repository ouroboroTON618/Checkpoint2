import java.util.Scanner;

public class UsefulReportsPage {

    private Scanner scanner;

    public UsefulReportsPage() {
        scanner = new Scanner(System.in);

        System.out.println(LineGenerator.generateLine(""));
        System.out.println(LineGenerator.generateLine("Useful Reports Page"));
        MenuItems();
    }

    private void MenuItems() {

        System.out.println(LineGenerator.generateLine("Useful Reports"));
        System.out.println(LineGenerator.generateLine("Option (A): Renting Checkouts"));
        System.out.println(LineGenerator.generateLine("Option (B): Popular Item"));
        System.out.println(LineGenerator.generateLine("Option (C): Popular Manufacturer"));
        System.out.println(LineGenerator.generateLine("Option (D): Popular Drone"));
        System.out.println(LineGenerator.generateLine("Option (E): Items Checked Out"));
        System.out.println(LineGenerator.generateLine("Option (F): Equipment by Type of equipment"));
        System.out.println(LineGenerator.generateLine("Please Select a Menu Option Or (M) to return to Main Page"));

        MenuSelect();
    }

    private void MenuSelect() {
        System.out.print("Option: ");
        String menuOption = scanner.nextLine();
        char option = menuOption.toLowerCase().charAt(0);
        switch (option) {
            case 'm':
                Main.DisplayMenuPrompt();
                break;
            case 'a':
                RentingCheckouts();
                break;
            case 'b':
                PopularItem();
                break;
            case 'c':
                PopularManufacturer();
                break;
            case 'd':
                PopularDrone();
                break;
            case 'e':
                ItemsCheckedOut();
                break;
            case 'f':
                EquipmentByTypeOfEquipment();
                break;
            default:
                System.out.println("Invalid Input: Please Enter a valid input");
                MenuSelect();
                break;
        }
    }

    private void RentingCheckouts() {

        String member_ID = MemberID();


        if (Main.databaseEnabled) {
            ResultPackage result = QueryManager.getRentingCheckouts(Integer.parseInt(member_ID));

            TableDisplayGenerator.GenerateTable(result);

        } else {
            System.out.println("Database Disabled: Renting Checkouts Query is success: " + member_ID);
        }
    }

    private void PopularItem() {

        if (Main.databaseEnabled) {
            ResultPackage result = QueryManager.getItemsCheckedOut();
            TableDisplayGenerator.GenerateTable(result);
        } else {
            System.out.println("Database Disabled: Popular Item Query Success");
        }
    }

    private void PopularManufacturer() {

        if (Main.databaseEnabled) {
            ResultPackage result = QueryManager.getPopularManufacturer();
            TableDisplayGenerator.GenerateTable(result);
        } else {
            System.out.println("Database Disabled: Popular Manufacturer Query Success.");
        }
    }

    private void PopularDrone() {

        if (Main.databaseEnabled) {
            ResultPackage result = QueryManager.getPopularDrone();
            TableDisplayGenerator.GenerateTable(result);
        } else {
            System.out.println("Database Disabled: Popular Drone Query Success");
        }
    }

    private void ItemsCheckedOut() {
        if (Main.databaseEnabled) {
            ResultPackage result = QueryManager.getItemsCheckedOut();
            TableDisplayGenerator.GenerateTable(result);
        } else {
            System.out.println("Database Disabled: Items Checkout Query Success");
        }
    }

    private void EquipmentByTypeOfEquipment() {

        String year = Year();
        String type = EquipType();
        if (Main.databaseEnabled) {
            ResultPackage result = QueryManager.getEquipmentByTypeOfEquipment(Integer.parseInt(year), type);
            // call table generator
            TableDisplayGenerator.GenerateTable(result);
        } else {
            System.out.println("Database Disabled: Equipment Type Query Success: ( " + year + " : " + type + " )");
        }
    }

    private String Year() {
        String year = "";
        while (!VerifyInputs.verifyYear(year)) {
            System.out.println(LineGenerator.generateLine("Enter the Year(YYYY)"));
            System.out.print("Year: ");
            year = scanner.nextLine();
        }
        return year;

    }

    private String EquipType() {
        String type = "";
        while (!VerifyInputs.VerifyEquipmentType(type)) {
            System.out.println(LineGenerator.generateLine("Enter the Type of Equipment(Type)"));
            System.out.print("Type: ");
            type = scanner.nextLine();
        }
        return type;
    }

    private String MemberID() {
        String memberID = "";
        while (!VerifyInputs.VerifyMemberID(memberID)) {
            System.out.println(LineGenerator.generateLine("Enter the member ID"));
            System.out.println("Member ID: ");
            memberID = scanner.nextLine();
        }
        return memberID;
    }

}
