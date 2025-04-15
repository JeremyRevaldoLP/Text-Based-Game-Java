import java.util.Scanner;

public class InventoryManager {
    private static Inventory inventory = new Inventory(); // Shared inventory

    // Show inventory items
    public static void displayInventory() {
        inventory.showInventory();
    }

    // This method can be called from BattleManager
    public static void openInventory(Pokemon target, Scanner scanner) {
        boolean usingInventory = true;

        while (usingInventory) {
            System.out.println("\n-- Inventory --");
            inventory.showInventory();
            System.out.println("Select item number to use (or 0 to go back):");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                usingInventory = false;
            } else {
                // Use index-based method to avoid "method not applicable" error
                inventory.useItem(choice - 1, target);
                usingInventory = false;
            }
        }
    }
}
