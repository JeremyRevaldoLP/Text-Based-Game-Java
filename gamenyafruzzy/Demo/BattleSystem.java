import java.util.Scanner;

public class BattleSystem {

    public static void startBattle(Character player, Enemy enemy, Scanner scanner) {
        TextUtils.typeWriter("\n--- Pertarungan Dimulai ---");
        TextUtils.typeWriter("Musuh muncul: " + enemy.getName() + " dengan HP " + enemy.getHealth());

        while (player.getHealth() > 0 && enemy.isAlive()) {
            TextUtils.typeWriter("\nGiliranmu:");
            TextUtils.typeWriter("1. Serang biasa");
            TextUtils.typeWriter("2. Gunakan skill");

            int choice = getValidInput(scanner, 1, 2);

            if (choice == 1) {
                // Basic attack based on physical attack stat
                int damage = calculatePhysicalDamage(player, enemy);
                TextUtils.typeWriter("Kamu menyerang " + enemy.getName() + "!");
                enemy.takeDamage(damage);
                TextUtils.typeWriter(enemy.getName() + " menerima " + damage + " damage. HP tersisa: " + enemy.getHealth());
            } 
            else if (choice == 2) {
                if (player.getSkills().isEmpty()) {
                    TextUtils.typeWriter("Kamu belum memiliki skill!");
                } else {
                    TextUtils.typeWriter("Pilih skill:");
                    for (int i = 0; i < player.getSkills().size(); i++) {
                        TextUtils.typeWriter((i + 1) + ". " + player.getSkills().get(i));
                    }
                    
                    int skillIndex = getValidInput(scanner, 1, player.getSkills().size()) - 1;
                    Skill chosen = player.getSkills().get(skillIndex);
                    TextUtils.typeWriter("Kamu menggunakan \"" + chosen.getName() + "\"!");
                    
                    // Handle different skill effects
                    int damage = handleSkillEffect(player, enemy, chosen);
                    if (damage > 0) {
                        enemy.takeDamage(damage);
                        TextUtils.typeWriter(enemy.getName() + " menerima " + damage + " damage. HP tersisa: " + enemy.getHealth());
                    }
                }
            }

            if (!enemy.isAlive()) {
                TextUtils.typeWriter("Kamu mengalahkan " + enemy.getName() + "!");
                break;
            }

            // Enemy turn
            int enemyDamage = enemy.attack() - (player.getDefense() / 2); // Reduce damage by defense
            if (enemyDamage < 0) enemyDamage = 0;
            player.takeDamage(enemyDamage);
            TextUtils.typeWriter("\n" + enemy.getName() + " menyerangmu!");
            TextUtils.typeWriter("Kamu menerima " + enemyDamage + " damage. HP tersisa: " + player.getHealth());
        }

        if (player.getHealth() <= 0) {
            TextUtils.typeWriter("Kamu tumbang dalam pertarungan ini...");
        }
    }

    private static int calculatePhysicalDamage(Character player, Enemy enemy) {
        // Base damage + physical attack minus some defense
        int baseDamage = 10;
        int damage = baseDamage + player.getPhysicalAttack() - (enemy.getDefense() / 3);
        return Math.max(1, damage); // Always do at least 1 damage
    }

    private static int handleSkillEffect(Character player, Enemy enemy, Skill skill) {
        switch (skill.getName()) {
            case "Iron Wall":
                // Defense boost implementation would go here
                return 0;
            case "Counter Stance":
                // Counter implementation would go here
                return player.getPhysicalAttack();
            case "Savage Blow":
                return (int)(player.getPhysicalAttack() * 2.0);
            case "Arcane Focus":
                return (int)(player.getMagicAttack() * 1.5);
            // Add other skill cases here
            default:
                return (int)(player.getPhysicalAttack() * 1.25); // Default skill damage
        }
    }

    private static int getValidInput(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("Pilih (" + min + "-" + max + "): ");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input >= min && input <= max) {
                    return input;
                }
            } else {
                scanner.nextLine();
            }
            TextUtils.typeWriter("Input tidak valid, coba lagi.");
        }
    }
}