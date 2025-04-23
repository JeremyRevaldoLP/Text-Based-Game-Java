import java.util.HashMap;
import java.util.Map;

public class Character {
    String name;
    int health;
    int attack;
    int evasion; 
    String characterClass; // "Guard" or "Prisoner"

    Map<String, Integer> cooldowns = new HashMap<>();
    Map<String, Integer> statuses = new HashMap<>(); // for effects like Iron Wall

    public Character(String name, int health, int attack, int evasion, String characterClass) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.evasion = evasion;
        this.characterClass = characterClass;

        cooldowns = new HashMap<>();
        statuses = new HashMap<>();

        // Initialize cooldowns for skills
        if (characterClass.equals("Guard")) {
             cooldowns.put("Iron Wall", 0);
        } else if (characterClass.equals("Prisoner")) {
            cooldowns.put("Backstab", 0);
        }
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

    // Skills for the classes
    public void reduceCooldowns() {
        for (String key : cooldowns.keySet()) {
            if (cooldowns.get(key) > 0) {
                cooldowns.put(key, cooldowns.get(key) - 1);
            }
        }

        for (String key : statuses.keySet()) {
            if (statuses.get(key) > 0) {
                statuses.put(key, statuses.get(key) - 1);
            }
        }
    }

    public boolean useSkill(Character target) {
        if (characterClass.equals("Guard")) {
            int cd = cooldowns.get("Iron Wall");
            if (cd == 0) {
                statuses.put("Iron Wall", 2); // lasts this turn + next
                cooldowns.put("Iron Wall", 3); // sets cooldown
                TextUtils.typeWriter(name + " uses Iron Wall! No damage will be taken for 2 turns.");
                return true;
            } else {
                TextUtils.typeWriter("Iron Wall is on cooldown for " + cd + " more turn(s).");
                return false;
            }
        }

        if (characterClass.equals("Prisoner")) {
            int cd = cooldowns.get("Backstab");
            if (cd == 0) {
                double multiplier = target.health < 0.5 * target.health ? 1.6 : 1.3;
                int damage = (int)(attack * multiplier);
                TextUtils.typeWriter(name + " uses Backstab for " + damage + " damage!");
                target.takeDamage(damage);
                cooldowns.put("Backstab", 4); // set cooldown
                return true;
            } else {
                TextUtils.typeWriter("Backstab is on cooldown for " + cd + " more turn(s).");
                return false;
            }
        }

        return false;
    }

}