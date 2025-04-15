// Prisoner.java

import java.util.*;

public class Prisoner extends Player {

    public Prisoner(String name) {
        super(name, "Prisoner", 25, 7, 3, 6, 3); // HP, P.ATK, DEF, EVA, Flask
        setUpSkills();
    }

    private void setUpSkills() {
        addSkill(new Skill("Backstab", "Deal 200% p.atk if enemy HP is below 50%.", 3));
        addSkill(new Skill("Fade", "Avoid all damage for 1 turn, but cannot attack.", 2));
        addSkill(new Skill("Bleak Strike", "Deal 120% p.atk, and gain +1 evasion for the rest of the battle.", 4));
        addSkill(new Skill("Sudden Death", "10% chance to instantly kill the enemy (once per battle).", -1)); // Once per battle
        addSkill(new Skill("Shadow Reflexes", "Increase evasion by +5 for 3 turns.", 3));
        addSkill(new Skill("Poison Dagger", "Deal 100% p.atk, then reduce enemy HP by 2 per turn for 2 turns.", 3));
        addSkill(new Skill("Vicious Gamble", "Deal 300% p.atk, 50% chance to miss.", 4));
        addSkill(new Skill("Ghoststep", "All attacks against you will miss for 1 turn.", 3));
    }

    @Override
    public void learnSkillFromChoice(int stage, int choice) {
        // Prisoner gets one of two unique skills per choice (total 3 stages)
        Skill chosenSkill = null;

        if (stage == 1) {
            chosenSkill = (choice == 0)
                ? new Skill("Assassin's Instinct", "Gain +5 p.atk for 3 turns after using a skill.", 3)
                : new Skill("Quick Recovery", "Recover 5 HP after every successful attack.", 2);
        } else if (stage == 2) {
            chosenSkill = (choice == 0)
                ? new Skill("Silent Killer", "Deal 150% p.atk and silence the enemy for 2 turns.", 4)
                : new Skill("Cloak of Shadows", "Gain +10 evasion for 1 turn.", 3);
        } else if (stage == 3) {
            chosenSkill = (choice == 0)
                ? new Skill("Executioner", "Deal 250% p.atk if the enemy is below 25% HP.", 4)
                : new Skill("Lurking Danger", "Your attacks deal +3 damage for each skill you've used.", 3);
        }

        if (chosenSkill != null) {
            addSkill(chosenSkill);
            System.out.println("🕵️‍♂️ You learned a new skill: " + chosenSkill.getName());
        }
    }
}
