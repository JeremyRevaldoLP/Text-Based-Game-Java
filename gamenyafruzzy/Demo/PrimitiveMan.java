public class PrimitiveMan extends Character {

    public PrimitiveMan(String name) {
        super(name);
        applyClassBuffs("PrimitiveMan");
        initializeSkills();
    }

    @Override
    public void initializeSkills() {
        skills.add(new Skill("Bloodthirst", "Tambah +3 p.atk saat HP di bawah 50%", 0)); // Passive

        skills.add(new Skill("Savage Blow", "Serang dengan 200% p.atk tapi self-damage 3 HP", 3));

        skills.add(new Skill("Berserk Mode", "+5 p.atk selama 3 turn tapi -3 defense", 4));
        
        skills.add(new Skill("Reckless Strike", "Serang dua kali, tapi tiap hit hanya 70% p.atk", 2));
    }
}
