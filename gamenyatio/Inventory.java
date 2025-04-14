import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    

    public Inventory() {
        this.items = new ArrayList<>();
        // Contoh isi awal inventory
        items.add(new Item("Potion", "heal", 20));
        items.add(new Item("X Attack", "increase_attack", 0));
    }

    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory empty.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " - " + item.getEffect());
            }
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void useItem(int index, Pokemon target) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            switch (item.getEffect()) {
                case "heal":
                    target.heal(item.getPower());
                    System.out.println(target.name + " healed " + item.getPower() + " HP!");
                    break;
                case "increase_attack":
                    if (target.attackBuffCount < 2) {
                        target.attack += 2;
                        target.attackBuffCount++;
                        System.out.println(target.name + " Increased ATK!");
                    } else {
                        System.out.println(target.name + " can't increased ATK anymore!");
                    }
                    break;
                case "increase_defense":
                    if (target.defenseBuffCount < 2) {
                        target.defense += 2;
                        target.defenseBuffCount++;
                        System.out.println(target.name + " Increased DEF!");
                    } else {
                        System.out.println(target.name + " can't increased DEF anymore!");
                    }
                    break;
                default:
                    System.out.println("Unknown item effect.");
            }
            items.remove(index); // Item hanya bisa dipakai sekali
        } else {
            System.out.println("It's not working.");
        }
    }
    public void useItem(Item item, Pokemon target) {
        int index = items.indexOf(item);
        if (index != -1) {
            useItem(item, target);
        } else {
            System.out.println("Can't find the item.");
        }
    }
    
}
