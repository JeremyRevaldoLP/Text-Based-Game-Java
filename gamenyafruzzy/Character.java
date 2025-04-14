// Character.java

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    protected String name;
    protected int maxHP;
    protected int currentHP;
    protected int pAtk;
    protected int mAtk;
    protected int pDef;
    protected int mDef;
    protected int evasion;
    protected int flaskCount;

    protected List<Skill> skills;

    public Character(String name, int maxHP, int pAtk, int mAtk, int pDef, int mDef, int evasion, int flaskCount) {
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.pAtk = pAtk;
        this.mAtk = mAtk;
        this.pDef = pDef;
        this.mDef = mDef;
        this.evasion = evasion;
        this.flaskCount = flaskCount;
        this.skills = new ArrayList<>();
    }

    // ================== Basic Getters ==================
    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getPAtk() {
        return pAtk;
    }

    public int getMAtk() {
        return mAtk;
    }

    public int getPDef() {
        return pDef;
    }

    public int getMDef() {
        return mDef;
    }

    public int getEvasion() {
        return evasion;
    }

    public int getFlaskCount() {
        return flaskCount;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    // ================== Battle Logic ==================

    public void takeDamage(int damage) {
        currentHP -= damage;
        if (currentHP < 0) currentHP = 0;
    }

    public void heal(int amount) {
        currentHP += amount;
        if (currentHP > maxHP) currentHP = maxHP;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public void useFlask() {
        if (flaskCount > 0) {
            heal(5);
            flaskCount--;
            System.out.println(name + " used a flask and recovered 5 HP.");
        } else {
            System.out.println(name + " has no flasks left!");
        }
    }

    public void resetBattleStatus() {
        currentHP = maxHP;
        for (Skill s : skills) {
            s.resetAll();
        }
    }

    public void reduceSkillCooldowns() {
        for (Skill s : skills) {
            s.reduceCooldown();
        }
    }

    // ================== Skill Management ==================

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void listSkills() {
        int i = 1;
        for (Skill s : skills) {
            System.out.println(i + ". " + s.toString());
            i++;
        }
    }

    public abstract void displayStats();
}
