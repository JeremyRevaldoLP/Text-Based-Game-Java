// BattleManager.java

import java.util.Scanner;

public class BattleManager {
    private Player player;
    private Enemy enemy;
    private Scanner scanner;

    public BattleManager(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.scanner = new Scanner(System.in);
    }

    public void startBattle() {
        System.out.println("\n⚔️  Battle Start! " + player.getName() + " vs " + enemy.getName() + " ⚔️");

        while (player.isAlive() && enemy.isAlive()) {
            displayStatus();
            playerTurn();
            if (!enemy.isAlive()) {
                System.out.println("🎉 You defeated " + enemy.getName() + "!");
                break;
            }

            enemyTurn();
            player.reduceSkillCooldowns();
            if (!player.isAlive()) {
                System.out.println("💀 You were defeated by " + enemy.getName() + "...");
            }
        }
    }

    private void displayStatus() {
        System.out.println("\n-- Turn Status --");
        System.out.println(player.getName() + ": " + player.getCurrentHP() + "/" + player.getMaxHP() + " HP | Flask: " + player.getFlaskCount());
        System.out.println(enemy.getName() + ": " + enemy.getCurrentHP() + "/" + enemy.getMaxHP() + " HP");
    }

    private void playerTurn() {
        System.out.println("\nYour turn. Choose an action:");
        System.out.println("1. Attack");
        System.out.println("2. Use Skill");
        System.out.println("3. Use Flask");
        System.out.println("4. Exit Battle");

        String input = scanner.nextLine();
        switch (input) {
            case "1":
                basicAttack(player, enemy);
                break;
            case "2":
                useSkillMenu();
                break;
            case "3":
                player.useFlask();
                break;
            case "4":
                confirmExit();
                break;
            default:
                System.out.println("Invalid choice.");
                playerTurn();
        }
    }

    private void enemyTurn() {
        System.out.println("\n" + enemy.getName() + "'s turn!");
        if (!enemy.getSkills().isEmpty()) {
            Skill skill = enemy.getSkills().get(0);
            if (skill.isReady()) {
                System.out.println(enemy.getName() + " uses " + skill.getName() + "!");
                int damage = (int) (enemy.getPAtk() * 1.5);
                player.receiveDamage(damage);
                skill.use();
                return;
            } else {
                skill.tickCooldown();
            }
        }

        basicAttack(enemy, player);
    }

    private void basicAttack(Character attacker, Character defender) {
        int baseDamage = attacker.getPAtk();
        int actualDamage = Math.max(baseDamage - defender.getPDef(), 0);
        System.out.println(attacker.getName() + " attacks for " + actualDamage + " damage!");
        defender.receiveDamage(actualDamage);
    }

    private void useSkillMenu() {
        player.listSkills();
        System.out.print("Choose skill number to use: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index < 1 || index > player.getSkills().size()) {
                System.out.println("Invalid skill.");
                return;
            }

            Skill chosen = player.getSkills().get(index - 1);
            if (!chosen.isReady()) {
                System.out.println("Skill is on cooldown for " + chosen.getCurrentCooldown() + " more turn(s).");
                return;
            }

            System.out.println(player.getName() + " uses " + chosen.getName() + "!");

            // Basic effect simulation - real effect should be in skill logic
            int dmg = (int)(player.getPAtk() * 1.5);
            enemy.receiveDamage(dmg);
            chosen.use();

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private void confirmExit() {
        System.out.print("Are you sure you want to exit battle? (yes/no): ");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("yes")) {
            System.out.println("You ran away from the battle...");
            System.exit(0);
        }
    }
}
