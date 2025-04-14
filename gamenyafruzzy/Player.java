// Player.java

import java.util.Scanner;

public class Player extends Character {
    private String playerClass;

    public Player(String name, String playerClass) {
        super(name, 0, 0, 0, 0, 0, 0, 0); // Nilai akan ditentukan berdasarkan class
        this.playerClass = playerClass;
        initializeStatsAndSkills();
    }

    private void initializeStatsAndSkills() {
        switch (playerClass.toLowerCase()) {
            case "guard":
                maxHP = 30;
                currentHP = maxHP;
                pAtk = 6;
                mAtk = 2;
                pDef = 12;
                mDef = 6;
                evasion = 2;
                flaskCount = 2;

                addSkill(new Skill("Iron Wall", "Block all damage for 1 turn (100% defense boost)", 3));
                addSkill(new Skill("Counter Stance", "Counter with 50% p.atk if attacked this turn", 2));
                addSkill(new Skill("Fortify", "Permanently +3 defense", 4));
                addSkill(new Skill("Last Bastion", "Survive fatal hit once, cannot go below 1 HP", -1)); // -1 for once per battle
                addSkill(new Skill("Bulwark Smash", "120% p.atk, reduce enemy evasion -3", 3));
                addSkill(new Skill("Intercept", "Next attack deals 150% damage if HP < 50%", 3));
                addSkill(new Skill("Oath of Steel", "Passive: +5 HP at battle start", 0));
                addSkill(new Skill("Shield Charge", "Attack +2 def for 1 turn", 2));
                break;

            case "primitiveman":
                maxHP = 28;
                currentHP = maxHP;
                pAtk = 14;
                mAtk = 1;
                pDef = 5;
                mDef = 2;
                evasion = 3;
                flaskCount = 1;

                addSkill(new Skill("Bloodthirst", "Passive: +3 p.atk if HP < 50%", 0));
                addSkill(new Skill("Savage Blow", "200% p.atk, take 3 self-damage", 3));
                addSkill(new Skill("Berserk Mode", "+5 p.atk for 3 turns, -3 defense", 4));
                addSkill(new Skill("Reckless Strike", "Attack twice at 70% p.atk", 2));
                addSkill(new Skill("Adrenaline Surge", "Passive: Heal 3 HP if taking >5 damage", 0));
                addSkill(new Skill("Wild Roar", "Reduce enemy p.def -4", 3));
                addSkill(new Skill("Fearless Leap", "130% p.atk, +2 evasion (1 turn)", 2));
                addSkill(new Skill("Unstoppable Rage", "+7 p.atk, disable flask", -1));
                break;

            case "scholarmancer":
                maxHP = 22;
                currentHP = maxHP;
                pAtk = 2;
                mAtk = 13;
                pDef = 4;
                mDef = 10;
                evasion = 4;
                flaskCount = 2;

                addSkill(new Skill("Arcane Focus", "Next magic attack +50% damage", 2));
                addSkill(new Skill("Mana Barrier", "Nullify magic damage for 1 turn", 3));
                addSkill(new Skill("Brainpower", "+5 m.atk once", -1));
                addSkill(new Skill("Chrono Pause", "Free attack, enemy cannot act this turn", 4));
                addSkill(new Skill("Fire Sigil", "Magic 120% + reduce defense -2", 2));
                addSkill(new Skill("Mirror Image", "50% chance to dodge for 1 turn", 2));
                addSkill(new Skill("Eldritch Pulse", "Magic 200%, lose 2 HP", 3));
                addSkill(new Skill("Insight", "Passive: Reveal enemy stats", 0));
                break;

            case "prisoner":
                maxHP = 26;
                currentHP = maxHP;
                pAtk = 10;
                mAtk = 3;
                pDef = 4;
                mDef = 4;
                evasion = 6;
                flaskCount = 0;

                addSkill(new Skill("Backstab", "200% p.atk if enemy HP < 50%", 3));
                addSkill(new Skill("Fade", "Avoid all damage this turn, can't attack", 2));
                addSkill(new Skill("Bleak Strike", "120% p.atk, permanently +1 evasion", 4));
                addSkill(new Skill("Sudden Death", "10% instant kill (once)", -1));
                addSkill(new Skill("Shadow Reflexes", "+5 evasion for 3 turns", 3));
                addSkill(new Skill("Poison Dagger", "100% p.atk, poison 2 HP/turn for 2 turns", 3));
                addSkill(new Skill("Vicious Gamble", "300% p.atk, 50% chance to miss", 4));
                addSkill(new Skill("Ghoststep", "All attacks miss you this turn", 3));
                break;

            default:
                System.out.println("Unknown class. Defaulting to Guard.");
                playerClass = "Guard";
                initializeStatsAndSkills();
                break;
        }
    }

    public String getPlayerClass() {
        return playerClass;
    }

    @Override
    public void displayStats() {
        System.out.println("\n===== " + name + " the " + capitalize(playerClass) + " =====");
        System.out.println("HP: " + currentHP + "/" + maxHP);
        System.out.println("P.ATK: " + pAtk);
        System.out.println("M.ATK: " + mAtk);
        System.out.println("P.DEF: " + pDef);
        System.out.println("M.DEF: " + mDef);
        System.out.println("Evasion: " + evasion);
        System.out.println("Flasks: " + flaskCount);
        System.out.println("Skills:");
        listSkills();
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
