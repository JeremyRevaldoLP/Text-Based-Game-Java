public class Item {
    private String name;
    private String description;
    private int healAmount;  // Untuk item yang menyembuhkan
    private String effect;   // Misal: "increase_attack", "increase_defense"
    private int power;       // Berapa besar pengaruh efek

    // Constructor untuk item penyembuh (misal Potion)
    public Item(String name, String description, int healAmount) {
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
        this.effect = "heal";
        this.power = healAmount;
    }

    // Constructor untuk item dengan efek (misal X Attack)
    public Item(String name, String description, String effect, int power) {
        this.name = name;
        this.description = description;
        this.healAmount = 0;
        this.effect = effect;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
        if (effect != null) {
            return name + " - " + effect + " (" + power + ")";
        } else {
            return name + " - heals " + healAmount + " HP";
        }
    }
}
