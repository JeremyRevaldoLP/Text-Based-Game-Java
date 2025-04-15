// Scholarmancer.java

import java.util.*;

public class Scholarmancer extends Player {

    public Scholarmancer(String name) {
        super(name, "Scholarmancer", 30, 4, 6, 5, 4); // HP, M.ATK, DEF, EVA, Flask
        setUpSkills();
    }

    private void setUpSkills() {
        addSkill(new Skill("Arcane Focus", "Next magic attack +50% damage.", 2));
        addSkill(new Skill("Mana Barrier", "Negates magic damage for 1 turn.", 3));
        addSkill(new Skill("Brainpower", "+5 m.atk once (cannot be repeated).", -1)); // once per battle
        addSkill(new Skill("Chrono Pause", "Attack without allowing enemy to retaliate (1x use).", 4));
        addSkill(new Skill("Fire Sigil", "Magic attack 120% + -2 defense to enemy (once).", 2));
        addSkill(new Skill("Mirror Image", "50% chance enemy's attack misses for 1 turn.", 2));
        addSkill(new Skill("Eldritch Pulse", "Magic attack 200%, but take 2 HP damage yourself.", 3));
        addSkill(new Skill("Insight", "View full enemy stats.", 0)); // passive
    }

    @Override
    public void learnSkillFromChoice(int stage, int choice) {
        // Scholarmancer gets one of two unique skills per choice (total 3 stages)
        Skill chosenSkill = null;

        if (stage == 1) {
            chosenSkill = (choice == 0)
                ? new Skill("Mystic Shield", "Reduce all incoming damage by 50% for 1 turn.", 3)
                : new Skill("Arcane Burst", "Increase magic damage by +6 for 2 turns.", 4);
        } else if (stage == 2) {
            chosenSkill = (choice == 0)
                ? new Skill("Arcane Reflection", "Reflect 30% of damage back to attacker for 1 turn.", 3)
                : new Skill("Mana Surge", "Regenerate 5 HP and gain +3 m.atk for 1 turn.", 2);
        } else if (stage == 3) {
            chosenSkill = (choice == 0)
                ? new Skill("Elemental Fury", "Deal magic damage 150% to all enemies.", 4)
                : new Skill("Time Warp", "Rewind the turn, allowing you to act again.", 5);
        }

        if (chosenSkill != null) {
            addSkill(chosenSkill);
            System.out.println("🔮 You learned a new skill: " + chosenSkill.getName());
        }
    }
}
