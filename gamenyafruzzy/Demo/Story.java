import java.util.Scanner;

public class Story {
    // Variables to track player choices
    private static int choice1;
    private static int choice2;

    public static void startIntro(Character player) {
        Scanner scanner = new Scanner(System.in);

        TextUtils.typeWriter("\n--- Prolog ---");
        TextUtils.typeWriter("Kamu terbangun di sebuah ruang bawah tanah gelap. Jalan keluar tampak jauh.");

        // 1st Selection
        TextUtils.typeWriter("\n--- Pilihan Pertama ---");
        TextUtils.typeWriter("1. Ambil perisai usang di dekat mayat.");
        TextUtils.typeWriter("2. Hancurkan tembok rapuh untuk jalan pintas.");
        System.out.print("Pilih (1-2): ");
        choice1 = scanner.nextInt();
        scanner.nextLine();

        // 2nd Selection
        TextUtils.typeWriter("\n--- Pilihan Kedua ---");
        TextUtils.typeWriter("1. Hadapi makhluk penjaga jalan.");
        TextUtils.typeWriter("2. Bersembunyi dan tunggu kesempatan.");
        System.out.print("Pilih (1-2): ");
        choice2 = scanner.nextInt();
        scanner.nextLine();

        // Clear existing skills and add the appropriate ones
        player.getSkills().clear();
        assignSkillsBasedOnChoices(player);

        // Show skill
        TextUtils.typeWriter("\n--- Skill yang kamu miliki sekarang ---");
        for (Skill skill : player.getSkills()) {
            TextUtils.typeWriter("- " + skill);
        }

        TextUtils.typeWriter("\n--- Petualangan dimulai! ---");

        // Fights
        BattleSystem.startBattle(player, new Enemy("Rotten Soldier", 35, 10, 2, 7, 0, 2), scanner);

        TextUtils.typeWriter("\nKamu melanjutkan perjalananmu dan bertemu dengan musuh baru...");
        BattleSystem.startBattle(player, new Enemy("Primitive Beast", 28, 3, 10, 4, 7, 3), scanner);

        TextUtils.typeWriter("\nMusuh lain muncul di depanmu...");
        BattleSystem.startBattle(player, new Enemy("Guardian", 26, 4, 4, 6, 0, 10), scanner);

        TextUtils.typeWriter("\nAkhirnya, kamu sampai pada ujian terakhir...");
        BattleSystem.startBattle(player, new Boss("Dark Lord", 65, 6, 6, 10, 10, 4, "Soul Burn"), scanner);
    }

    private static void assignSkillsBasedOnChoices(Character player) {
        // Add base skills (all characters get these)
        player.getSkills().add(new Skill("Defend", "Meningkatkan defense sebesar 50% untuk 1 turn", 1));

        if (player instanceof Guard) {
            switch (choice1 + "-" + choice2) {
                case "1-1":
                    player.getSkills().add(new Skill("Iron Wall", "Block all damage for 1 turn (100% defense boost for 1 turn)", 3));
                    break;
                case "1-2":
                    player.getSkills().add(new Skill("Counter Stance", "Jika diserang di turn ini, serang balik dengan 50% p.atk", 2));
                    break;
                case "2-1":
                    player.getSkills().add(new Skill("Fortify", "Permanently tambah +3 defense hingga akhir pertempuran", 4));
                    break;
                case "2-2":
                    player.getSkills().add(new Skill("Last Bastion", "Tidak bisa mati 1x, HP tidak bisa turun di bawah 1 (sekali pakai)", 99));
                    break;
            }
        } 
        else if (player instanceof PrimitiveMan) {
            switch (choice1 + "-" + choice2) {
                case "1-1":
                    player.getSkills().add(new Skill("Bloodthirst", "Tambah +3 p.atk saat HP di bawah 50%", 0));
                    break;
                case "1-2":
                    player.getSkills().add(new Skill("Savage Blow", "Serang dengan 200% p.atk tapi self-damage 3 HP", 3));
                    break;
                case "2-1":
                    player.getSkills().add(new Skill("Berserk Mode", "+5 p.atk selama 3 turn tapi -3 defense", 4));
                    break;
                case "2-2":
                    player.getSkills().add(new Skill("Reckless Strike", "Serang dua kali, tapi tiap hit hanya 70% p.atk", 2));
                    break;
            }
        }
        else if (player instanceof Scholarmancer) {
            switch (choice1 + "-" + choice2) {
                case "1-1":
                    player.getSkills().add(new Skill("Arcane Focus", "Serangan magic berikutnya +50% damage", 2));
                    break;
                case "1-2":
                    player.getSkills().add(new Skill("Mana Barrier", "Hilangkan damage magic lawan selama 1 turn", 3));
                    break;
                case "2-1":
                    player.getSkills().add(new Skill("Brainpower", "Tambah +5 m.atk sekali saja (tidak bisa diulang)", 99));
                    break;
                case "2-2":
                    player.getSkills().add(new Skill("Chrono Pause", "Serang tanpa membuat musuh bisa menyerang di turn itu", 4));
                    break;
            }
        }
        else if (player instanceof Prisoner) {
            switch (choice1 + "-" + choice2) {
                case "1-1":
                    player.getSkills().add(new Skill("Backstab", "200% p.atk jika HP musuh <50%", 3)); 
                    break;
                case "1-2": 
                    player.getSkills().add(new Skill("Fade", "Hindari semua damage 1 turn", 2)); 
                    break;
                case "2-1": 
                    player.getSkills().add(new Skill("Bleak Strike", "120% p.atk +1 evasion", 4)); 
                    break;
                case "2-2": 
                    player.getSkills().add(new Skill("Sudden Death", "10% chance instakill", 99)); 
                    break;
            }
        }
    }
}
