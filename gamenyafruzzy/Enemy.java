// Enemy.java

public class Enemy extends Character {
    private String type; // "Normal" or "Boss"

    public Enemy(String name, String type) {
        super(name, 0, 0, 0, 0, 0, 0, 0);
        this.type = type.toLowerCase();
        initializeStatsAndSkills();
    }

    private void initializeStatsAndSkills() {
        switch (name.toLowerCase()) {
            case "wild boar":
                maxHP = 18;
                currentHP = maxHP;
                pAtk = 6;
                mAtk = 0;
                pDef = 3;
                mDef = 1;
                evasion = 1;
                flaskCount = 0;
                break;

            case "goblin thug":
                maxHP = 20;
                currentHP = maxHP;
                pAtk = 5;
                mAtk = 0;
                pDef = 2;
                mDef = 2;
                evasion = 2;
                flaskCount = 0;
                break;

            case "forest bandit":
                maxHP = 22;
                currentHP = maxHP;
                pAtk = 7;
                mAtk = 0;
                pDef = 4;
                mDef = 1;
                evasion = 3;
                flaskCount = 0;
                break;

            case "fallen knight":
                // Boss
                maxHP = 40;
                currentHP = maxHP;
                pAtk = 10;
                mAtk = 5;
                pDef = 6;
                mDef = 4;
                evasion = 3;
                flaskCount = 1;

                if (type.equals("boss")) {
                    addSkill(new Skill("Dark Cleave", "150% p.atk attack", 2));
                }
                break;

            default:
                // fallback enemy
                maxHP = 15;
                currentHP = maxHP;
                pAtk = 4;
                mAtk = 0;
                pDef = 2;
                mDef = 1;
                evasion = 1;
                flaskCount = 0;
                break;
        }
    }

    public String getType() {
        return type;
    }

    @Override
    public void displayStats() {
        System.out.println("\n--- Enemy: " + name + " [" + capitalize(type) + "] ---");
        System.out.println("HP: " + currentHP + "/" + maxHP);
        System.out.println("P.ATK: " + pAtk);
        System.out.println("M.ATK: " + mAtk);
        System.out.println("P.DEF: " + pDef);
        System.out.println("M.DEF: " + mDef);
        System.out.println("Evasion: " + evasion);
        System.out.println("Flasks: " + flaskCount);
        if (!skills.isEmpty()) {
            System.out.println("Skill:");
            listSkills();
        }
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
