import java.util.Scanner;

public class BattleSystem {

    public static void startBattle(Character player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Pertarungan Dimulai ---");
        System.out.println("Musuh: " + enemy);

        while (player.getHealth() > 0 && enemy.isAlive()) {
            System.out.println("\nGiliranmu:");
            System.out.println("1. Serang biasa");
            System.out.println("2. Gunakan skill");

            System.out.print("Pilih aksi (1-2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Kamu menyerang musuh!");
                enemy.takeDamage(15); // basic attack
            } else {
                System.out.println("Pilih skill:");
                for (int i = 0; i < player.getSkills().size(); i++) {
                    System.out.println((i + 1) + ". " + player.getSkills().get(i));
                }
                System.out.print("Pilih skill (1-" + player.getSkills().size() + "): ");
                int skillIndex = scanner.nextInt() - 1;
                if (skillIndex >= 0 && skillIndex < player.getSkills().size()) {
                    Skill chosen = player.getSkills().get(skillIndex);
                    System.out.println("Kamu menggunakan " + chosen.getName() + "!");
                    enemy.takeDamage(25); // skill damage sementara
                } else {
                    System.out.println("Skill tidak valid, kamu gagal menyerang.");
                }
            }

            if (!enemy.isAlive()) {
                System.out.println("Kamu mengalahkan " + enemy.getName() + "!");
                break;
            }

            // Musuh menyerang balik
            System.out.println(enemy.getName() + " menyerangmu!");
            int damage = enemy.attack();
            player.health -= damage;
            if (player.health < 0) player.health = 0;
            System.out.println("Kamu menerima " + damage + " damage. HP tersisa: " + player.health);
        }

        if (player.getHealth() <= 0) {
            System.out.println("Kamu kalah...");
        }

        // Agar tidak terjadi memory leak
        scanner.close();
    }
}
