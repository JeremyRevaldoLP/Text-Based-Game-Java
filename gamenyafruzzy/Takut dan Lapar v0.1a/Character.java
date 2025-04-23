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

    public boolean tryEvade() {
        return (int)(Math.random() * 100) < evasion;
    }
}