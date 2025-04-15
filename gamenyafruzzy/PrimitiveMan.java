public class PrimitiveMan extends Character {

    public PrimitiveMan(String name) {
        super(name);
        this.className = "Primitive Man";
        this.health = 90;
        initializeSkills();
    }

    @Override
    public void initializeSkills() {
        skills.add(new Skill("Wild Swing", "Serangan brutal dengan akurasi rendah", 0));
        skills.add(new Skill("Rage", "Meningkatkan damage selama 2 turn", 2));
        // Tambah 6 skill lainnya nanti
    }
}
