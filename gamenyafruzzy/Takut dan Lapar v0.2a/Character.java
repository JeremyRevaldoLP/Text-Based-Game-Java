import java.util.HashMap;
import java.util.Map;

public class Character {
    String name;
    int health;
    int maxHealth; // For unique skill purposes only
    int attack;
    int evasion; 
    String characterClass; // "Guard" or "Prisoner"
    public String unlockedSkillName = null;
    public String unlockedSkillDescription = null; // for showing in UI only

    Map<String, Integer> cooldowns = new HashMap<>();
    Map<String, Integer> statuses = new HashMap<>(); // for effects like Iron Wall

    boolean boostedAttackNextTurn = false;
    boolean usedRiskSkill = false;
    boolean usedBleakStrike = false;
    boolean usedLastBastion = false;
    boolean usedLifesteal = false;

    public Character(String name, int health, int attack, int evasion, String characterClass) {
        this.name = name;
        this.health = health;
        this.maxHealth = health; // For unique skill purposes only
        this.attack = attack;
        this.evasion = evasion;
        this.characterClass = characterClass;

        cooldowns = new HashMap<>();
        statuses = new HashMap<>();

        // Initialize cooldowns for skills
        if (characterClass != null)
            if (characterClass.equals("Guard")) {
                // Skills need to be unlocked now
            } else if (characterClass.equals("Prisoner")) {
                // Skills need to be unlocked now
            }
    }

    // ========== PASSIVE ==========
    public void applyFortify() {
        if (characterClass != null && characterClass.equals("Guard")) {
            maxHealth += 10;
            health += 10;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        if (statuses.getOrDefault("Iron Wall", 0) > 0) {
            TextUtils.typeWriter(name + "'s still holding up, No damage taken.");
            return; // No damage
        }

        if (statuses.getOrDefault("Last Bastion", 0) > 0) {
            health -= damage;
            if (health < 1) {
                health = 1;
                TextUtils.typeWriter(name + "'s Last Bastion prevents death!");
            }
            return;
        }
    
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public void attack(Character target) {
        int finalDamage = attack;
        if (boostedAttackNextTurn) {
            finalDamage = (int)(attack * 2.5);
            boostedAttackNextTurn = false;
            TextUtils.typeWriter(name + "'s boosted attack strikes for " + finalDamage + " damage!");
        } else {
            TextUtils.typeWriter(name + " attacks for " + finalDamage + " damage.");
        }
        target.takeDamage(finalDamage);
    }

    // if number generated higher than character evasion = evasion failed ofc, will activate if number is lower than character evasion
    public boolean tryEvade() {
        return (int)(Math.random() * 100) < evasion;
    }

    // Skills for the classes
    public void reduceCooldowns() {
        for (String key : cooldowns.keySet()) {
            if (cooldowns.get(key) > 0) {
                cooldowns.put(key, cooldowns.get(key) - 1);
            }
        }

        for (String key : statuses.keySet()) {
            if (statuses.get(key) > 0) {
                statuses.put(key, statuses.get(key) - 1);
            }
        }
    }

    

    public boolean useSkill(Character target) {

        if (characterClass == null) return false;

        if (unlockedSkillName == null) {
            TextUtils.typeWriter("You haven't remembered any skills yet...");
            return false;
        }
        
        if (!cooldowns.containsKey(unlockedSkillName)) {
            TextUtils.typeWriter("You are using your skill.");
            return false;
        }

        // ===== GUARD =====
        if (characterClass.equals("Guard")) {

            // Iron Wall Skill
            if (cooldowns.containsKey("Iron Wall")) {
                int cd = cooldowns.get("Iron Wall");
                if (cd == 0) {
                    statuses.put("Iron Wall", 2); // lasts this turn + next
                    cooldowns.put("Iron Wall", 3); // sets cooldown
                    TextUtils.typeWriter(name + " uses Iron Wall! No damage will be taken for this turn and the next one.");
                    return true;
                } else {
                    TextUtils.typeWriter("Iron Wall is on cooldown for " + cd + " more turn(s).");
                    return false;
                }
            }

            // Boost Skill
            if (cooldowns.containsKey("Boost")) {
                int cd = cooldowns.get("Boost");
                if (cd == 0) {
                    boostedAttackNextTurn = true;
                    cooldowns.put("Boost", 3);
                    TextUtils.typeWriter(name + " focuses intensely. The next strike will hit harder!");
                    return true;
                } else {
                    TextUtils.typeWriter("Boost is on cooldown for " + cd + " more turn(s).");
                    return false;
                }
            }

            // For Fortify no need to add here, its a passive therefore no direct input required

            // Last Bastion
            if (cooldowns.containsKey("Last Bastion")) {
                if (usedLastBastion) {
                    TextUtils.typeWriter("Last Bastion weaken your body. Endure.");
                    return false;
                }
                usedLastBastion = true;
                statuses.put("Last Bastion", 2);
                TextUtils.typeWriter(name + " invokes Last Bastion! You cannot fall for the next 2 turns.");
                return true;
            }
        }

        // ===== PRISONER =====
        if (characterClass.equals("Prisoner")) {

            // Backstab
            if (cooldowns.containsKey("Backstab")) {
                int cd = cooldowns.get("Backstab");
                if (cd == 0) {
                    double multiplier = target.health < 0.5 * target.health ? 1.6 : 1.3;
                    int damage = (int)(attack * multiplier);
                    TextUtils.typeWriter(name + " uses Backstab for " + damage + " damage!");
                    target.takeDamage(damage);
                    cooldowns.put("Backstab", 4);
                    return true;
                } else {
                    TextUtils.typeWriter("Backstab is on cooldown for " + cd + " more turn(s).");
                    return false;
                }
            }

            // It's Either You or Me
            if (cooldowns.containsKey("It's Either You or Me")) {
                if (usedRiskSkill) {
                    TextUtils.typeWriter("You've already risked it all once...");
                    return false;
                }
                usedRiskSkill = true;
                boolean playerWins = Math.random() < 0.5;
                if (playerWins) {
                    target.health = 1;
                    TextUtils.typeWriter(name + " gambles fate! The enemy is brought to the brink of death!");
                } else {
                    this.health = 1;
                    TextUtils.typeWriter(name + " gambles fate... and is nearly killed by the outcome.");
                }
                return true;
            }

            // Bleak Strike
            if (cooldowns.containsKey("Bleak Strike")) {
                if (usedBleakStrike) {
                    TextUtils.typeWriter(name + "'s form blurs, and so as their vision. Endure.");
                    return false;
                }
                usedBleakStrike = true;
                int damage = (int)(attack * 1.2);
                TextUtils.typeWriter(name + " unleashes a Bleak Strike! It deals " + damage + " damage.");
                evasion += 10;
                TextUtils.typeWriter(name + "'s form blurs. Evasion increased by 10 for the rest of this fight.");
                target.takeDamage(damage);
                return true;
            }

            // Lifesteal
            if (cooldowns.containsKey("Lifesteal")) {
                if (usedLifesteal) {
                    TextUtils.typeWriter("You're consumed by greed...");
                    this.health = 0;
                    TextUtils.typeWriter("You tried to steal life again — and lost your own.");
                    return true; // still counts as used
                }
            
                usedLifesteal = true;
                target.takeDamage(attack);
                int healed = Math.min(attack, maxHealth - health);
                health += healed;
                TextUtils.typeWriter(name + " drains the enemy for " + attack + " damage and heals " + healed + " HP.");
                return true;
            }

        }

        return false;
    }

}

/*
==============================
        SKILL REFERENCE
==============================

== GUARD SKILLS ==
1. Iron Wall
   - Effect: Block all damage for 2 turns (this turn + next)
   - Cooldown: 3 turns

2. Boost
   - Effect: Next attack deals +50% damage
   - Cooldown: 3 turns

3. Fortify
   - Effect: Permanently increase Max HP by +10
   - Cooldown: Passive (always active)

4. Last Bastion
   - Effect: Cannot die for 2 turns (HP cannot fall below 1)
   - Cooldown: Once per battle

== PRISONER SKILLS ==
1. Backstab
   - Effect: Deals +30% damage (or +60% if enemy HP < 50%)
   - Cooldown: 4 turns

2. It's Either You or Me
   - Effect: 50% chance to reduce enemy HP to 1, or reduce your own HP to 1
   - Cooldown: Once per battle

3. Bleak Strike
   - Effect: Deal 120% damage, then gain +10 evasion for the rest of the fight
   - Cooldown: Once per battle

4. Lifesteal
   - Effect: Deal normal attack damage and heal for the same amount
   - If used twice in one battle: the user dies instantly
   - Cooldown: Once per battle
*/