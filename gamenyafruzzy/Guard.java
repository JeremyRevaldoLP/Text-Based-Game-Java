// Guard.java

import java.util.*;

public class Guard extends Player {

    public Guard(String name) {
        super(name, "Guard", 40, 7, 10, 2, 2); // HP, P.ATK, DEF, EVA, Flask
        setUpSkills();
    }

    private void setUpSkills() {
        addSkill(new Skill("Iron Wall", "Block all damage for 1 turn (100% defense boost).", 3));
        addSkill(new Skill("Counter Stance", "If attacked, counter with 50% p.atk.", 2));
        addSkill(new Skill("Fortify", "Permanently +3 defense.", 4));
        addSkill(new Skill("Last Bastion", "HP can't drop below 1 once.", -1)); // once per battle
        addSkill(new Skill("Bulwark Smash", "120% p.atk and reduce enemy evasion -3.", 3));
        addSkill(new Skill("Intercept", "Next attack = 150% if HP < 50%.", 3));
        addSkill(new Skill("Oath of Steel", "Permanently +5 HP at start of battle.", 0)); // passive
        addSkill(new Skill("Shield Charge", "Attack and gain +2 DEF for 1 turn.", 2));
    }

    @Override
    public void learnSkillFromChoice(int stage, int choice) {
        // Guard gets one of two unique skills per choice (total 3 stages)
        Skill chosenSkill = null;

        if (stage == 1) {
            chosenSkill = (choice == 0)
                ? new Skill("Resolute Slam", "150% p.atk if enemy attacked last turn.", 3)
                : new Skill("Iron Discipline", "Gain +1 defense each turn (stackable).", 4);
        } else if (stage == 2) {
            chosenSkill = (choice == 0)
                ? new Skill("Guardian’s Charge", "Deal 100% p.atk and taunt enemy.", 2)
                : new Skill("Shield Wall", "Reduce all incoming damage by 50% for 2 turns.", 4);
        } else if (stage == 3) {
            chosenSkill = (choice == 0)
                ? new Skill("Knight’s Pledge", "Revive with 10 HP once if killed.", -1)
                : new Skill("War Cry", "+2 attack and defense for 2 turns.", 3);
        }

        if (chosenSkill != null) {
            addSkill(chosenSkill);
            System.out.println("🛡️ You learned a new skill: " + chosenSkill.getName());
        }
    }
}
