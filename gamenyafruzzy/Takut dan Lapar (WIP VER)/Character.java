public class Character {
    String name;
    int health;
    int attack;
    int evasion; 
    
    public Character(String name, int health, int attack, int evasion) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.evasion = evasion;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public void attack(Character target) {
        target.takeDamage(attack);
    }

    // if number generated higher than character evasion = evasion failed ofc will activate if number is lower than character evasion
    public boolean tryEvade() {
        return (int)(Math.random() * 100) < evasion;
    }
}