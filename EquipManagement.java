import java.util.Scanner;

public class EquipManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = scanner.nextInt();

        while (!exit) {
            System.out.println("Welcome to the equipment renting store!");
            System.out.println("Choose one of the following menu options:");
            System.out.println("1. Add new records");
            System.out.println("2. Edit/delete records");
            System.out.println("3. Search");

            switch(option) {
                case 1:
                    exit = true;
                    break;
            }
        }

        scanner.close();
    }
}