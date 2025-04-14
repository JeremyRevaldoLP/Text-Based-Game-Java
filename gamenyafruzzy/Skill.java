// Skill.java

public class Skill {
    private String name;
    private String description;
    private int cooldown; // cooldown default
    private int currentCooldown; // sisa turn sebelum bisa dipakai lagi
    private boolean isPassive;
    private boolean isOncePerBattle;
    private boolean usedOnce;

    public Skill(String name, String description, int cooldown, boolean isPassive, boolean isOncePerBattle) {
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
        this.isPassive = isPassive;
        this.isOncePerBattle = isOncePerBattle;
        this.usedOnce = false;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public boolean isPassive() {
        return isPassive;
    }

    public boolean isOncePerBattle() {
        return isOncePerBattle;
    }

    public boolean isUsedOnce() {
        return usedOnce;
    }

    // Logic
    public boolean isAvailable() {
        if (isPassive) return false;
        if (isOncePerBattle && usedOnce) return false;
        return currentCooldown == 0;
    }

    public void activate() {
        if (!isPassive && !isAvailable()) {
            System.out.println("Skill " + name + " is not available!");
            return;
        }
        if (isOncePerBattle) usedOnce = true;
        this.currentCooldown = cooldown;
    }

    public void reduceCooldown() {
        if (currentCooldown > 0) currentCooldown--;
    }

    public void resetCooldown() {
        this.currentCooldown = 0;
    }

    public void resetOnceUse() {
        this.usedOnce = false;
    }

    public void resetAll() {
        this.currentCooldown = 0;
        this.usedOnce = false;
    }

    @Override
    public String toString() {
        return name + " - " + description + " (CD: " + cooldown + (isPassive ? ", Passive" : "") + (isOncePerBattle ? ", 1x Battle" : "") + ")";
    }
}
