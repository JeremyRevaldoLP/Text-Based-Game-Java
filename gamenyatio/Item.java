public class Item {
    private String name;
    private String description;
    private int healAmount;
    private String effect;
    private int power;
    private int quantity;

    public Item(String name, String description, int healAmount) {
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
        this.effect = null;
        this.power = 0;
        this.quantity = 10;
    }

    public Item(String name, String description, String effect, int power) {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.power = power;
        this.healAmount = 0;
        this.quantity = 5;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }

    public int getHealAmount() {
        return healAmount;
    }

    public String getEffect() {
        return effect;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return name + ": " + description + " (x" + quantity + ")";
    }
}
