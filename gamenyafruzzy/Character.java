import java.util.List;
import java.util.ArrayList;

public abstract class Character {
    // Atribut dasar (sesuai permintaan)
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int defense;
    protected int magicDef;
    protected int physAtk;
    protected int magicAtk;
    protected int evasion; // dalam persen (5 = 5%)
    protected int flaskCount;

    // Status efek (contoh)
    protected int tempDefBoost = 0;
    protected int tempEvasionBoost = 0;
    protected boolean cantDie = false;

    // Constructor
    public Character(String name, int hp, int defense, int magicDef, 
                    int physAtk, int magicAtk, int evasion, int flaskCount) {
        this.name = name;
        this.maxHp = this.hp = hp;
        this.defense = defense;
        this.magicDef = magicDef;
        this.physAtk = physAtk;
        this.magicAtk = magicAtk;
        this.evasion = evasion;
        this.flaskCount = flaskCount;
    }

    // === Method Dasar ===
    public boolean useFlask() {
        if (flaskCount > 0 && hp < maxHp) {
            hp = Math.min(hp + 5, maxHp);
            flaskCount--;
            System.out.println(name + " memakai flask! +5 HP.");
            return true;
        }
        System.out.println("Flask tidak bisa digunakan!");
        return false;
    }

    public void takeDamage(int damage) {
        if (cantDie && hp - damage < 1) {
            hp = 1;
            cantDie = false;
            System.out.println(name + " bertahan di 1 HP!");
        } else {
            hp -= damage;
            System.out.println(name + " menerima " + damage + " damage!");
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    // Method abstract untuk skill (akan di-override di subclass)
    public abstract void useSkill(int skillIndex, Character target);

    // Method untuk attack dasar
    public void basicAttack(Character target) {
        int hitChance = 100 - target.evasion - target.tempEvasionBoost;
        if (new Random().nextInt(100) < hitChance) {
            int damage = Math.max(1, physAtk - target.defense);
            target.takeDamage(damage);
        } else {
            System.out.println(name + " meleset!");
        }
    }
}