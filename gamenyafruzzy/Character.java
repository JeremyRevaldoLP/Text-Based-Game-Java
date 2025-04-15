import java.util.ArrayList;

public abstract class Character {
    protected String name;
    protected String className;
    protected int health;
    protected ArrayList<Skill> skills;

    public Character(String name) {
        this.name = name;
        this.skills = new ArrayList<>();
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

    public abstract void initializeSkills();

    @Override
    public String toString() {
        return "Nama: " + name + "\nKelas: " + className + "\nHP: " + health;
    }
}
