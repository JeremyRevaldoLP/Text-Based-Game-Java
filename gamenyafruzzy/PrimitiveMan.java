// PrimitiveMan.java

import java.util.*;

public class PrimitiveMan extends Player {

    public PrimitiveMan(String name) {
        super(name, "Primitive Man", 35, 8, 5, 3, 2); // HP, P.ATK, DEF, EVA, Flask
        setUpSkills();
    }

    private void setUpSkills() {
        addSkill(new Skill("Bloodthirst", "+3 p.atk when HP is below 50%.", 0)); // passive
        addSkill(new Skill("Savage Blow", "200% p.atk but self-damage 3 HP.", 3));
        addSkill(new Skill("Berserk Mode", "+5 p.atk for 3 turns but -3 defense.", 4));
        addSkill(new Skill("Reckless Strike", "Attack twice, but each hit is 70% p.atk.", 2));
        addSkill(new Skill("Adrenaline Surge", "Heal 3 HP when receiving damage > 5.", 0)); // passive
        addSkill(new Skill("Wild Roar", "Reduce enemy p.def by -4.", 3));
        addSkill(new Skill("Fearless Leap", "130% p.atk and +2 evasion for 1 turn.", 2));
        addSkill(new Skill("Unstoppable Rage", "+7 p.atk once, but cannot use flask again.", -1)); // once per battle
    }

    @Override
    public void learnSkillFromChoice(int stage, int choice) {
        // PrimitiveMan gets one of two unique skills per choice (total 3 stages)
        Skill chosenSkill = null;

        if (stage == 1) {
            chosenSkill = (choice == 0)
                ? new Skill("Wild Fury", "Gain +3 p.atk for each kill.", 3)
                : new Skill("Rage Infusion", "Gain +10 p.atk for 1 turn, but lose 3 defense.", 4);
        } else if (stage == 2) {
            chosenSkill = (choice == 0)
                ? new Skill("Savage Instinct", "Reduce all incoming damage by 30% for 2 turns.", 3)
                : new Skill("Frenzied Assault", "Attack with 150% p.atk, but lose 5 HP.", 2);
        } else if (stage == 3) {
            chosenSkill = (choice == 0)
                ? new Skill("Bloodlust Rampage", "Increase p.atk by +6 for 3 turns.", 3)
                : new Skill("Madness Unleashed", "50% chance to deal double damage, but -3 defense.", 4);
        }

        if (chosenSkill != null) {
            addSkill(chosenSkill);
            System.out.println("🦁 You learned a new skill: " + chosenSkill.getName());
        }
    }
}
