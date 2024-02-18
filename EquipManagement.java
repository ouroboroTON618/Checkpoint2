import java.util.Scanner;

public class EquipManagement {

    public static void MenuPrompt() {
        System.out.println("Welcome to the equipment renting store!");
        System.out.println("Choose one of the following menu options:");
        System.out.println("1. Add new records");
        System.out.println("2. Edit/delete records");
        System.out.println("3. Search");
        System.out.println("4. Rent equipment");
        System.out.println("5. Return equipment");
        System.out.println("6. Schedule delivery of equipment");
        System.out.println("6. Schedule pickup of equipment:");
        System.out.println("7. Exit");
        System.out.print("Option: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option;

        while (!exit) {
            MenuPrompt();
            option = scanner.nextInt();
            System.out.println();

            switch(option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Exiting program...");
                    exit = true;
                    break;
            }
        }

        scanner.close();
    }
}