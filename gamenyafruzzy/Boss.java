public class Boss extends Enemy {
    private String specialSkill;

    public Boss(String name, int health, int damage, String specialSkill) {
        super(name, health, damage);
        this.specialSkill = specialSkill;
    }

    public String getSpecialSkill() {
        return specialSkill;
    }

    @Override
    public String toString() {
        return name + " (Boss) - HP: " + health + " - Skill: " + specialSkill;
    }
}
