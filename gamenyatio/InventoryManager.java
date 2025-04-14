import java.util.Scanner;

public class InventoryManager {

    public static void openInventory(Pokemon pokemon, Scanner scanner) {
        System.out.println("--- Inventory ---");
        pokemon.getInventory().showInventory();

        if (!pokemon.getInventory().getItems().isEmpty()) {
            System.out.print("What would you do? (insert the number): ");
            int choice = scanner.nextInt() - 1;
            if (choice >= 0 && choice < pokemon.getInventory().getItems().size()) {
                Item item = pokemon.getInventory().getItems().get(choice);
                pokemon.getInventory().useItem(item, pokemon);
            }
        }
    }
}
