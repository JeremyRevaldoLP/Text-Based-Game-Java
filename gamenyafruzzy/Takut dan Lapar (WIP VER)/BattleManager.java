import java.util.Scanner;

public class BattleManager {
    private Scanner scanner;
    private boolean playerChoseDefend = false;
    
    public BattleManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void battle(Character player, Character enemy) {
        System.out.println("\n====================================");
        System.out.println(enemy.name + 
                         " (Health: " + enemy.health + ")");

        while (player.isAlive() && enemy.isAlive()) {
            // Player's turn
            playerTurn(player, enemy);
            if (!enemy.isAlive()) break;
            
            // Enemy's turn
            enemyTurn(player, enemy);
        }

        if (player.isAlive()) {
            TextUtils.typeWriter("\n" + player.name + " defeated the " + enemy.name);
        } else {
            showDefeatMessage(enemy.name, player);
        }
    }

    private void playerTurn(Character player, Character enemy) {
        TextUtils.typeWriter("\n" + player.name + ": " + player.health + " HP");
        TextUtils.typeWriter(enemy.name + ": " + enemy.health + " HP");
        TextUtils.typeWriter("What do you do? (1. Attack, 2. Defend): ");
        
        int choice = getValidChoice(1, 2);
        
        if (choice == 1) {
            // Attack
            TextUtils.typeWriter("\n" + player.name + " attacks the " + enemy.name + " for " + player.attack + " damage");
            player.attack(enemy);
            playerChoseDefend = false;
        } else {
            // Defend
            playerChoseDefend = true;
            TextUtils.typeWriter("\n" + player.name + " braces themself");
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
                damage = (int)(damage * 0.85);
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