import java.util.Arrays;
import java.util.List;

public class Guard extends Character {
    // Cooldown tracker untuk 8 skill
    private int[] skillCooldowns = new int[8];
    
    // Nama skill untuk UI
    private static final List<String> SKILL_NAMES = Arrays.asList(
        "Iron Wall", "Counter Stance", "Fortify", "Last Bastion",
        "Bulwark Smash", "Intercept", "Oath of Steel", "Shield Charge"
    );

    public Guard() {
        super("Guard", 
            35,   // HP (base)
            12,   // DEF (5+7)
            8,    // M.DEF (5+3)
            5,    // P.ATK (base)
            2,    // M.ATK (5-3)
            5,    // EVA (base)
            2     // Flask
        );
    }

    @Override
    public void useSkill(int skillIndex, Character target) {
        if (skillCooldowns[skillIndex] > 0) {
            System.out.println("Skill '" + SKILL_NAMES.get(skillIndex) + "' dalam cooldown!");
            return;
        }

        switch (skillIndex) {
            case 0: // Iron Wall (100% DEF boost 1 turn)
                tempDefBoost = defense;
                System.out.println(name + ": DEF meningkat jadi " + (defense + tempDefBoost) + "!");
                skillCooldowns[0] = 3;
                break;

            case 1: // Counter Stance (balik serangan)
                System.out.println(name + " bersiap membalas serangan!");
                // Implementasi counter logic di BattleManager
                skillCooldowns[1] = 2;
                break;

            case 2: // Fortify (+3 DEF permanen)
                defense += 3;
                System.out.println(name + ": DEF +3 permanen!");
                skillCooldowns[2] = 4;
                break;

            case 3: // Last Bastion (1x survive)
                cantDie = true;
                System.out.println(name + ": Tidak bisa mati 1x!");
                skillCooldowns[3] = 999; // Once per battle
                break;

            case 4: // Bulwark Smash (120% ATK, -3 EVA musuh)
                int damage = (int)(physAtk * 1.2);
                target.takeDamage(damage);
                target.evasion = Math.max(0, target.evasion - 3);
                System.out.println(target.name + ": EVA turun 3!");
                skillCooldowns[4] = 3;
                break;

            case 5: // Intercept (150% damage jika HP < 50%)
                if (hp < maxHp / 2) {
                    damage = (int)(physAtk * 1.5);
                    target.takeDamage(damage);
                } else {
                    System.out.println("HP belum di bawah 50%!");
                }
                skillCooldowns[5] = 3;
                break;

            case 6: // Oath of Steel (+5 HP pasif)
                maxHp += 5;
                hp += 5;
                System.out.println(name + ": Max HP +5!");
                skillCooldowns[6] = 0; // No cooldown
                break;

            case 7: // Shield Charge (serang + DEF +2)
                basicAttack(target);
                tempDefBoost += 2;
                System.out.println(name + ": DEF +2 untuk 1 turn!");
                skillCooldowns[7] = 2;
                break;
        }
    }

    // Update cooldown tiap turn
    public void updateCooldowns() {
        for (int i = 0; i < skillCooldowns.length; i++) {
            if (skillCooldowns[i] > 0) skillCooldowns[i]--;
        }
    }

    // Untuk menampilkan skill di menu battle
    public void displaySkills() {
        System.out.println("\n=== Skill Guard ===");
        for (int i = 0; i < SKILL_NAMES.size(); i++) {
            String cdInfo = (skillCooldowns[i] > 0) ? "[CD: " + skillCooldowns[i] + "]" : "";
            System.out.println((i + 1) + ". " + SKILL_NAMES.get(i) + " " + cdInfo);
        }
    }
}