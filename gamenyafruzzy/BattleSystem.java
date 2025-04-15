import java.util.Scanner;

public class BattleSystem {

    public static void startBattle(Character player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);

        TextUtils.typeWriter("\n--- Pertarungan Dimulai ---");
        TextUtils.typeWriter("Musuh muncul: " + enemy.getName() + " dengan HP " + enemy.getHealth());

        while (player.getHealth() > 0 && enemy.isAlive()) {
            TextUtils.typeWriter("\nGiliranmu:");
            TextUtils.typeWriter("1. Serang biasa");
            TextUtils.typeWriter("2. Gunakan skill");

            System.out.print("Pilih aksi (1-2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                TextUtils.typeWriter("Kamu mengayunkan senjatamu ke arah " + enemy.getName() + "!");
                enemy.takeDamage(15); // Basic attack
                TextUtils.typeWriter("🗡️ " + enemy.getName() + " menerima 15 damage. HP tersisa: " + enemy.getHealth());
            } else if (choice == 2) {
                if (player.getSkills().isEmpty()) {
                    TextUtils.typeWriter("Kamu belum memiliki skill!");
                } else {
                    TextUtils.typeWriter("Pilih skill:");
                    for (int i = 0; i < player.getSkills().size(); i++) {
                        TextUtils.typeWriter((i + 1) + ". " + player.getSkills().get(i).toString());
                    }

                    System.out.print("Pilih skill (1-" + player.getSkills().size() + "): ");
                    int skillIndex = scanner.nextInt() - 1;
                    if (skillIndex >= 0 && skillIndex < player.getSkills().size()) {
                        Skill chosen = player.getSkills().get(skillIndex);
                        TextUtils.typeWriter("Kamu menggunakan \"" + chosen.getName() + "\"!");
                        enemy.takeDamage(25); // Skill damage sementara
                        TextUtils.typeWriter("" + enemy.getName() + " menerima 25 damage. HP tersisa: " + enemy.getHealth());
                    } else {
                        TextUtils.typeWriter("Skill tidak valid, kamu gagal menyerang.");
                    }
                }
            } else {
                TextUtils.typeWriter("Kamu bingung dan tidak melakukan apa-apa...");
            }

            if (!enemy.isAlive()) {
                TextUtils.typeWriter("Kamu mengalahkan " + enemy.getName() + "!");
                break;
            }

            // Giliran musuh
            TextUtils.typeWriter("\n" + enemy.getName() + " mengayunkan serangannya!");
            int damage = enemy.attack();
            player.health -= damage;
            if (player.health < 0) player.health = 0;
            TextUtils.typeWriter("Kamu menerima " + damage + " damage. HP tersisa: " + player.health);
        }

        if (player.getHealth() <= 0) {
            TextUtils.typeWriter("Kamu tumbang dalam pertarungan ini...");
        }

        // Agar tidak terjadi memory leak
        scanner.close();
    }
}
