public class Prisoner extends Character {

    public Prisoner(String name) {
        super(name);
        applyClassBuffs("Prisoner");
        initializeSkills();
    }

    @Override
    public void initializeSkills() {
        skills.add(new Skill("Backstab","Jika HP musuh di bawah 50%, serang dengan 200% p.atk", 3));
        
        skills.add(new Skill("Fade","Hindari semua damage 1 turn, tapi tidak bisa menyerang", 2));
        
        skills.add(new Skill("Bleak Strike","Serang 120% p.atk, lalu +1 evasion (permanen dalam pertarungan)", 4));
        
        skills.add(new Skill("Sudden Death","10% chance instakill (sekali pakai)", 99));  // 99 = once per battle
    }
}
