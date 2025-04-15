public class Scholarmancer extends Character {

    public Scholarmancer(String name) {
        super(name);
        this.className = "Scholarmancer";
        this.health = 75;
        initializeSkills();
    }

    @Override
    public void initializeSkills() {
        skills.add(new Skill("Fire Orb", "Menyerang musuh dengan sihir api", 1));
        skills.add(new Skill("Mana Shield", "Menahan serangan dengan sihir", 3));
        // Tambah 6 skill lainnya nanti
    }
}
