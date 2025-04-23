import java.util.Scanner;

public class BattleManager {
    private Scanner scanner;
    private boolean playerChoseDefend = false;
    
    public BattleManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void battle(Character player, Character enemy) {
        while (player.isAlive() && enemy.isAlive()) {
            // Player's turn
            playerTurn(player, enemy);
            if (!enemy.isAlive()) break;
            
            // Enemy's turn
            enemyTurn(player, enemy);

            // Reduce cooldowns and durations for both sides
            player.reduceCooldowns();
            enemy.reduceCooldowns();
        }

        if (player.isAlive()) {
            TextUtils.typeWriter("\n" + player.name + " defeated the " + enemy.name);
        } else {
            showDefeatMessage(enemy.name, player);
        }
    }

    private void showStatus(Character player, Character enemy) {
        // New player and enemy status
        System.out.println("\n====================================");
        System.out.printf("%-17s | HP: %3d/%-3d | Evasion: %d\n", player.name + " the " + player.characterClass, player.health, player.maxHealth, player.evasion);
        // Show unlocked skill (only one per run)
        if (player.unlockedSkillName != null && player.cooldowns.containsKey(player.unlockedSkillName)) {
            int cooldown = player.cooldowns.get(player.unlockedSkillName);
            String cooldownDisplay = cooldown > 0 ? cooldown + "T" : "Ready";
            String displayName = player.unlockedSkillDescription != null ? player.unlockedSkillDescription : player.unlockedSkillName;
        
            System.out.printf("Skill: %-40s | Cooldown: %s\n", displayName, cooldownDisplay);
        }

        System.out.println("------------------------------------");
        System.out.printf("%-17s | HP: %d\n", enemy.name, enemy.health);
        System.out.println("====================================");
    }
    

    private void playerTurn(Character player, Character enemy) {
        // Removed old player and enemy status
        // IF I WANT TO KEEP TERMINAL CLEARING DELETE THIS COMMENT, But keep this ofc --> TextUtils.clearScreen(); 
        showStatus(player, enemy);

        TextUtils.typeWriter("What do you do? (1. Attack, 2. Defend, 3. Use skill): ");
        
        int choice = getValidChoice(1, 3);
        
        if (choice == 1) {
            player.attack(enemy);
            playerChoseDefend = false;
        } else if (choice == 2) {
            playerChoseDefend = true;
            TextUtils.typeWriter("\n" + player.name + " braces themself");
        } else if (choice == 3) {
            boolean skillUsed = player.useSkill(enemy);
            if (!skillUsed) {
                playerTurn(player, enemy); // Retry turn if skill can't be used
            } else {
                playerChoseDefend = false; // If enemy turn, you don’t accidentally give the player a defense bonus if they used a skill instead of defending.
            }
        }
    }

    private void enemyTurn(Character player, Character enemy) {
        if (enemy.isAlive()) {
            // First check for passive evasion
            if (player.tryEvade()) {
                TextUtils.typeWriter("\n" + player.name + " dodged the " + enemy.name + "'s attack");
                playerChoseDefend = false; // Reset defense even if evaded
                return;
            }
            
            // example: if enemy attack attribute is like 20, its gonna multiply it by 0.85 so naturally damage output will not be 100% its gonna reduce to 85%
            int damage = enemy.attack;
            if (playerChoseDefend) {
                damage = (int)(damage * 0.55); // 45% maybe idk
            }
            
            TextUtils.typeWriter("\nThe " + enemy.name + " attacks " + player.name + " for " + damage + " damage");
            player.takeDamage(damage);
            playerChoseDefend = false; // Reset after being hit
        }
    }

    private void showDefeatMessage(String enemyName, Character player) {
        System.out.println("\n====================================");
        TextUtils.typeWriter(enemyName + " walks away", 50);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        TextUtils.typeWriter(player.name + "'s corpse rots in the dark...", 40);
        System.out.println("====================================");
    }

    private int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}