public class Prisoner extends Character {

    public Prisoner(String name) {
        super(name);
        this.className = "Prisoner";
        this.health = 80;
        initializeSkills();
    }

    @Override
    public void initializeSkills() {
        skills.add(new Skill("Backstab", "Menyerang diam-diam dengan damage besar", 1));
        skills.add(new Skill("Smoke Bomb", "Menghindari serangan musuh", 2));
        // Tambah 6 skill lainnya nanti
    }
}
