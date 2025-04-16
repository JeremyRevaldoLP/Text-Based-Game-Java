import java.util.ArrayList;

public abstract class Character {
    protected String name;
    protected String className;
    protected int health;
    protected int maxHealth;
    protected int defense;
    protected int magicDefense;
    protected int physicalAttack;
    protected int magicAttack;
    protected int evasion;
    protected ArrayList<Skill> skills;

    public Character(String name) {
        this.name = name;
        this.skills = new ArrayList<>();

        // Base stats
        this.health = 35;
        this.maxHealth = 35;
        this.defense = 5;
        this.magicDefense = 5;
        this.physicalAttack = 5;
        this.magicAttack = 5;
        this.evasion = 5;
    }
    
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }    
    
    public void applyClassBuffs(String className) {
        this.className = className;

        switch (className) {
            case "Guard":
                defense += 7;         // ++
                magicDefense += 3;    // +
                magicAttack -= 3;     // -
                break;

            case "Primitive Man":
                health += 3;          // +
                maxHealth += 3;
                defense += 3;         // +
                magicDefense -= 3;    // -
                physicalAttack += 3;  // +
                magicAttack -= 11;    // ---
                evasion -= 3;         // -
                break;

            case "Scholarmancer":
                health -= 3;          // -
                maxHealth -= 3;
                magicDefense += 7;    // ++
                physicalAttack -= 7;  // --
                magicAttack += 7;     // ++
                break;

            case "Prisoner":
                health -= 7;          // --
                maxHealth -= 7;
                defense -= 3;         // -
                magicDefense -= 3;    // -
                physicalAttack += 11; // +++
                evasion += 7;         // ++
                break;

            default:
                // No change
                break;
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
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

    public abstract void initializeSkills();

    @Override
    public String toString() {
        return "Nama: " + name +
               "\nKelas: " + className +
               "\nHP: " + health + "/" + maxHealth +
               "\nDEF: " + defense +
               "\nM.DEF: " + magicDefense +
               "\nP.ATK: " + physicalAttack +
               "\nM.ATK: " + magicAttack +
               "\nEVA: " + evasion;
    }
}
