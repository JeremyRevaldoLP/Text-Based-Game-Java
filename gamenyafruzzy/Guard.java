public class Guard extends Character {

    public Guard(String name) {
        super(name);
        applyClassBuffs("Guard");
        initializeSkills();
    }

    @Override
    public void initializeSkills() {
        skills.add(new Skill("Iron Wall", "Block all damage for 1 turn (100% defense boost for 1 turn)", 3));
        skills.add(new Skill("Counter Stance", "Jika diserang di turn ini, serang balik dengan 50% p.atk", 2));
        skills.add(new Skill("Fortify", "Permanently tambah +3 defense hingga akhir pertempuran", 4));
        skills.add(new Skill("Last Bastion", "Tidak bisa mati 1x, HP tidak bisa turun di bawah 1 (sekali pakai)", 99)); // Once per battle
        skills.add(new Skill("Bulwark Smash", "Serang musuh dengan 120% p.atk dan kurangi evasion musuh -3", 3));
        skills.add(new Skill("Intercept", "Jika HP di bawah 50%, serangan berikutmu jadi 150% damage", 3));
        skills.add(new Skill("Oath of Steel", "Tambah +5 HP secara permanen di awal battle", 0)); // Passive
        skills.add(new Skill("Shield Charge", "Serang dan naikkan defense +2 untuk 1 turn", 2));
    }
}
