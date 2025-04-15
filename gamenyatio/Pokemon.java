import java.util.List;

public class Pokemon {
    public String name;
    public String type;
    public int maxHp;
    public int hp;
    public int attack;
    public int defense;
    public List<Move> moves;
    private Inventory inventory = new Inventory(); // default empty inventory

    public int attackBuffCount = 0;
    public int attackDebuffCount = 0;
    public int defenseBuffCount = 0;
    public int defenseDebuffCount = 0;

    private int exp = 0; // Current experience
    private int level = 1; // Current level
    private int expToLevelUp = 50; // EXP required to level up (initial value)

    public Pokemon(String name, String type, int hp, int attack, int defense, List<Move> moves) {
        this.name = name;
        this.type = type;
        this.maxHp = hp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.moves = moves;
    }

    // Getter and setter methods...

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void resetBuffs() {
        attackBuffCount = 0;
        attackDebuffCount = 0;
        defenseBuffCount = 0;
        defenseDebuffCount = 0;
    }

    // Modified heal method to ensure it's not called twice:
    public void heal(int amount) {
        int previousHp = this.hp;
        this.hp += amount;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
        int healedAmount = this.hp - previousHp;  // This ensures we only print the actual healing amount
        System.out.println(name + " healed " + healedAmount + " HP!");
    }

    // Method to gain experience points and level up
    public void gainExp(int amount) {
        exp += amount;
        System.out.println(name + " gained " + amount + " EXP!");
        while (exp >= expToLevelUp) {
            levelUp();
        }
    }

    // Method to handle leveling up the Pokemon
    private void levelUp() {
        exp -= expToLevelUp; // Remove the required EXP to level up
        level++; // Increment level
        expToLevelUp += 25; // Increase EXP required for the next level by 25 (this is adjustable)
        maxHp += 5; // Increase max HP on level up
        attack += 2; // Increase attack on level up
        defense += 2; // Increase defense on level up
        hp = maxHp; // Heal the Pokemon on level up

        System.out.println(name + " leveled up to level " + level + "!");
    }

    // Getter for level and experience
    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getExpToLevelUp() {
        return expToLevelUp;
    }
}
