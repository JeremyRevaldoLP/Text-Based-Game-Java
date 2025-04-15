public class Enemy {
    protected String name;
    protected int health;
    protected int defense;
    protected int magicDefense;
    protected int physicalAttack;
    protected int magicAttack;
    protected int evasion;

    public Enemy(String name, int health, int defense, int magicDefense, int physicalAttack, int magicAttack, int evasion) {
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.magicDefense = magicDefense;
        this.physicalAttack = physicalAttack;
        this.magicAttack = magicAttack;
        this.evasion = evasion;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public int getPhysicalAttack() {
        return physicalAttack;
    }

    public int getMagicAttack() {
        return magicAttack;
    }

    public int getEvasion() {
        return evasion;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int attack() {
        return physicalAttack; // menggunakan physical attack sebagai dasar serangan
    }

    @Override
    public String toString() {
        return name + " (HP: " + health + ")";
    }
}
