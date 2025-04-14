import java.util.Arrays;
import java.util.List;

public class PrimitiveMan extends Character {
    private int[] skillCooldowns = new int[8];
    private boolean berserkActive = false;
    private int berserkTurnsRemaining = 0;
    private boolean usedUnstoppableRage = false;

    private static final List<String> SKILL_NAMES = Arrays.asList(
        "Bloodthirst", "Savage Blow", "Berserk Mode", "Reckless Strike",
        "Adrenaline Surge", "Wild Roar", "Fearless Leap", "Unstoppable Rage"
    );

    public PrimitiveMan() {
        super("Primitive Man",
            38,  // HP (35+3)
            8,   // DEF (5+3)
            2,   // M.DEF (5-3)
            8,   // P.ATK (5+3)
            0,   // M.ATK (5-11 = 0 minimum)
            2,   // EVA (5-3)
            1    // Flask
        );
    }

    @Override
    public void useSkill(int skillIndex, Character target) {
        if (skillCooldowns[skillIndex] > 0) {
            System.out.println(SKILL_NAMES.get(skillIndex) + " is on cooldown!");
            return;
        }

        switch (skillIndex) {
            case 0: // Bloodthirst (Passive)
                if (hp < maxHp / 2) {
                    physAtk += 3;
                    System.out.println(name + " enters bloodthirst! (+3 ATK)");
                }
                break;

            case 1: // Savage Blow (200% ATK, self-damage)
                int damage = (int)(physAtk * 2.0);
                target.takeDamage(damage);
                takeDamage(3);
                skillCooldowns[1] = 3;
                break;

            case 2: // Berserk Mode (+5 ATK, -3 DEF)
                berserkActive = true;
                berserkTurnsRemaining = 3;
                physAtk += 5;
                defense = Math.max(1, defense - 3);
                System.out.println(name + " goes BERSERK! (+5 ATK, -3 DEF)");
                skillCooldowns[2] = 4;
                break;

            case 3: // Reckless Strike (2x 70% ATK)
                for (int i = 0; i < 2; i++) {
                    damage = (int)(physAtk * 0.7);
                    target.takeDamage(damage);
                }
                skillCooldowns[3] = 2;
                break;

            case 4: // Adrenaline Surge (Passive)
                // Ditangani saat menerima damage di BattleManager
                break;

            case 5: // Wild Roar (-4 DEF musuh)
                target.defense = Math.max(1, target.defense - 4);
                System.out.println(target.name + "'s DEF shattered! (-4 DEF)");
                skillCooldowns[5] = 3;
                break;

            case 6: // Fearless Leap (130% ATK, +2 EVA)
                damage = (int)(physAtk * 1.3);
                target.takeDamage(damage);
                evasion += 2;
                System.out.println(name + " gains +2 EVA!");
                skillCooldowns[6] = 2;
                break;

            case 7: // Unstoppable Rage (+7 ATK, no flask)
                if (!usedUnstoppableRage) {
                    physAtk += 7;
                    usedUnstoppableRage = true;
                    flaskCount = 0; // Block flask usage
                    System.out.println(name + " becomes UNSTOPPABLE! (+7 ATK, no flask)");
                } else {
                    System.out.println("Can only use once per battle!");
                }
                break;
        }
    }

    // Update status tiap turn
    public void updateBattleEffects() {
        // Berserk duration
        if (berserkActive && --berserkTurnsRemaining <= 0) {
            berserkActive = false;
            physAtk -= 5;
            defense += 3;
            System.out.println(name + " calms down.");
        }

        // Update cooldowns
        for (int i = 0; i < skillCooldowns.length; i++) {
            if (skillCooldowns[i] > 0) skillCooldowns[i]--;
        }
    }

    public void displaySkills() {
        System.out.println("\n=== Primitive Man Skills ===");
        for (int i = 0; i < SKILL_NAMES.size(); i++) {
            String cdInfo = (skillCooldowns[i] > 0) ? "[CD: " + skillCooldowns[i] + "]" : "";
            System.out.println((i + 1) + ". " + SKILL_NAMES.get(i) + " " + cdInfo);
        }
    }

    // Untuk Adrenaline Surge (dipanggil saat menerima damage)
    public void checkAdrenaline(int damageReceived) {
        if (damageReceived > 5) {
            hp += 3;
            System.out.println(name + " heals 3 HP from adrenaline!");
        }
    }
}