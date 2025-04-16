public class Skill {
    private String name;
    private String description;
    private int cooldown;

    public Skill(String name, String description, int cooldown) {
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
    }

    @Override
    public String toString() {
        return name + " - " + description + " (CD: " + cooldown + ")";
    }

    public String getName() {
        return name;
    }

    public int getCooldown() {
        return cooldown;
    }
}
