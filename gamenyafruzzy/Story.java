import java.util.Scanner;

public class Story {

    public static void startIntro(Character player) {
        Scanner scanner = new Scanner(System.in);

        TextUtils.typeWriter("\n--- Prolog ---");
        TextUtils.typeWriter("Kamu terbangun di sebuah ruang bawah tanah gelap. Jalan keluar tampak jauh.");

        // Tahap 1 - Pilihan 1
        TextUtils.typeWriter("\n--- Pilihan Pertama ---");
        TextUtils.typeWriter("1. Ambil perisai usang di dekat mayat.");
        TextUtils.typeWriter("2. Hancurkan tembok rapuh untuk jalan pintas.");
        System.out.print("Pilih (1-2): ");
        int choice1 = scanner.nextInt();
        scanner.nextLine();
        giveSkill(player, 1, choice1);

        // Tahap 2 - Pilihan 2
        TextUtils.typeWriter("\n--- Pilihan Kedua ---");
        TextUtils.typeWriter("1. Hadapi makhluk penjaga jalan.");
        TextUtils.typeWriter("2. Bersembunyi dan tunggu kesempatan.");
        System.out.print("Pilih (1-2): ");
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        giveSkill(player, 2, choice2);

        // Tahap 3 - Pilihan 3
        TextUtils.typeWriter("\n--- Pilihan Ketiga ---");
        TextUtils.typeWriter("1. Bersumpah untuk bertahan hidup apapun yang terjadi.");
        TextUtils.typeWriter("2. Hancurkan segala rintangan yang menghadangmu.");
        System.out.print("Pilih (1-2): ");
        int choice3 = scanner.nextInt();
        scanner.nextLine();
        giveSkill(player, 3, choice3);

        // Tampilkan skill
        TextUtils.typeWriter("\n--- Skill yang kamu miliki sekarang ---");
        for (Skill skill : player.getSkills()) {
            TextUtils.typeWriter("- " + skill);
        }

        TextUtils.typeWriter("\n--- Petualangan dimulai! ---");

        // Pertarungan bertahap
        BattleSystem.startBattle(player, new Enemy("Rotten Soldier", 35, 10, 2, 7, 0, 2));
        TextUtils.typeWriter("\nKamu melanjutkan perjalananmu dan bertemu dengan musuh baru...");
        BattleSystem.startBattle(player, new Enemy("Primitive Beast", 28, 3, 10, 4, 7, 3));
        TextUtils.typeWriter("\nMusuh lain muncul di depanmu...");
        BattleSystem.startBattle(player, new Enemy("Guardian", 26, 4, 4, 6, 0, 10));
        TextUtils.typeWriter("\nAkhirnya, kamu sampai pada ujian terakhir...");
        BattleSystem.startBattle(player, new Boss("Dark Lord", 65, 6, 6, 10, 10, 4, "Soul Burn"));

        scanner.close();
    }

    private static void giveSkill(Character player, int stage, int choice) {
        // GUARD
        if (player instanceof Guard) {
            if (stage == 1) {
                if (choice == 1)
                    player.getSkills().add(new Skill("Iron Wall", "Block all damage for 1 turn (100% DEF boost)", 3));
                else
                    player.getSkills().add(new Skill("Counter Stance", "Jika diserang, serang balik 50% p.atk", 2));
            } else if (stage == 2) {
                if (choice == 1)
                    player.getSkills().add(new Skill("Fortify", "Permanently tambah +3 DEF", 4));
                else
                    player.getSkills().add(new Skill("Last Bastion", "Tidak bisa mati 1x, HP tidak bisa turun di bawah 1", 99));
            } else if (stage == 3) {
                if (choice == 1)
                    player.getSkills().add(new Skill("Oath of Steel", "Tambah +5 HP permanen di awal battle", 0)); // Passive
                else
                    player.getSkills().add(new Skill("Bulwark Smash", "Serang 120% p.atk, kurangi evasion musuh -3", 3));
            }
        }
    
        // PRIMITIVE MAN
        else if (player instanceof PrimitiveMan) {
            if (stage == 1) {
                if (choice == 1)
                    player.getSkills().add(new Skill("Savage Blow", "Serang dengan 200% p.atk tapi self-damage 3 HP", 3));
                else
                    player.getSkills().add(new Skill("Reckless Strike", "Serang dua kali, tiap hit 70% p.atk", 2));
            } else if (stage == 2) {
                if (choice == 1)
                    player.getSkills().add(new Skill("Berserk Mode", "+5 p.atk selama 3 turn tapi -3 DEF", 4));
                else
                    player.getSkills().add(new Skill("Fearless Leap", "Serang 130% p.atk dan naikkan evasion +2 selama 1 turn", 2));
            } else if (stage == 3) {
                if (choice == 1)
                    player.getSkills().add(new Skill("Bloodthirst", "Tambah +3 p.atk saat HP < 50%", 0)); // Passive
                else
                    player.getSkills().add(new Skill("Unstoppable Rage", "Tambah +7 p.atk sekali, tidak bisa pakai flask lagi", 99)); // Once per battle
            }
        }
    
        // TODO: Tambahkan implementasi skill untuk Scholarmancer dan Prisoner
    }
}       