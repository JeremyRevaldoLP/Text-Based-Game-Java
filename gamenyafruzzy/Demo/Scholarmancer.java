public class Scholarmancer extends Character {

    public Scholarmancer(String name) {
        super(name);
        applyClassBuffs("Scholarmancer");
        initializeSkills();
    }

    @Override
    public void initializeSkills() {
        skills.add(new Skill("Arcane Focus", "Serangan magic berikutnya +50% damage", 2));
        
        skills.add(new Skill("Mana Barrier", "Hilangkan damage magic lawan selama 1 turn", 3));

        skills.add(new Skill("Brainpower", "Tambah +5 m.atk sekali saja (tidak bisa diulang)", 99)); // Once per battle

        skills.add(new Skill("Chrono Pause", "Serang tanpa membuat musuh bisa menyerang di turn itu", 4));
    }
}
