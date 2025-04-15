import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
        items.add(new Item("Potion", "Heals 20 HP", 20));
        items.add(new Item("X Attack", "Increases attack", "increase_attack", 2));
        items.add(new Item("X Defense", "Increases defense", "increase_defense", 2));
    }

    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory empty.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.println((i + 1) + ". " + item);
            }
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void useItem(int index, Pokemon target) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);

            if (item.getQuantity() <= 0) {
                System.out.println("You don't have any more " + item.toString());
                return;
            }

            if (item.getHealAmount() > 0) {
                target.heal(item.getHealAmount());
            } else {
                switch (item.getEffect()) {
                    case "increase_attack":
                        if (target.attackBuffCount < 2) {
                            target.attack += item.getPower();
                            target.attackBuffCount++;
                            System.out.println(target.name + " increased ATK!");
                        } else {
                            System.out.println(target.name + " can't increase ATK anymore!");
                        }
                        break;
                    case "increase_defense":
                        if (target.defenseBuffCount < 2) {
                            target.defense += item.getPower();
                            target.defenseBuffCount++;
                            System.out.println(target.name + " increased DEF!");
                        } else {
                            System.out.println(target.name + " can't increase DEF anymore!");
                        }
                        break;
                    default:
                        System.out.println("Unknown item effect.");
                }
            }

            item.decreaseQuantity(1);

            if (item.getQuantity() <= 0) {
                items.remove(index);
            }

        } else {
            System.out.println("Invalid item choice.");
        }
    }
}
