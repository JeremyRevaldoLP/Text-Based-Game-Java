import java.util.Scanner;

public class Story {

    public static void startIntro(Character player) {
        Scanner scanner = new Scanner(System.in);

        TextUtils.typeWriter("\n--- Prolog ---");
        TextUtils.typeWriter("Kamu terbangun di sebuah ruang bawah tanah gelap. Ada tiga jalan di depanmu.");

        TextUtils.typeWriter("1. Masuk ke lorong dengan cahaya redup.");
        TextUtils.typeWriter("2. Masuk ke ruangan dengan bau busuk.");
        TextUtils.typeWriter("3. Ikuti suara bisikan dari kegelapan.");

        System.out.print("Pilih jalurmu (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                TextUtils.typeWriter("Kamu menemukan simbol sihir di lantai.");
                player.getSkills().add(new Skill("Arcane Touch", "Sentuhan magis yang melemahkan musuh", 2));
                break;
            case 2:
                TextUtils.typeWriter("Kamu menginjak perangkap dan belajar menghindari bahaya.");
                player.getSkills().add(new Skill("Trap Sense", "Menghindari serangan pertama", 3));
                break;
            case 3:
                TextUtils.typeWriter("Bisikan itu mengajarkanmu teknik terlarang.");
                player.getSkills().add(new Skill("Forbidden Chant", "Skill misterius dengan efek acak", 4));
                break;
            default:
                TextUtils.typeWriter("Kamu ragu dan hanya diam. Tidak ada skill tambahan.");
        }

        TextUtils.typeWriter("\nSkill-skill yang kamu miliki:");
        for (Skill skill : player.getSkills()) {
            TextUtils.typeWriter("- " + skill);
        }

        TextUtils.typeWriter("\n--- Petualangan dimulai... ---");

        // Lanjut ke musuh pertama
        BattleSystem.startBattle(player, new Enemy("Rotten Soldier", 35, 10, 2, 7, 0, 2));

        // Lanjut ke musuh kedua
        TextUtils.typeWriter("\nKamu melanjutkan perjalananmu dan bertemu dengan musuh baru...");
        BattleSystem.startBattle(player, new Enemy("Primitive Beast", 28, 3, 10, 4, 7, 3));

        // Lanjut ke musuh ketiga
        TextUtils.typeWriter("\nMusuh lain muncul di depanmu...");
        BattleSystem.startBattle(player, new Enemy("Guardian", 26, 4, 4, 6, 0, 10));

        // Lanjut ke boss
        TextUtils.typeWriter("\nAkhirnya, kamu sampai pada ujian terakhir...");
        BattleSystem.startBattle(player, new Boss("Dark Lord", 65, 6, 6, 10, 10, 4, "Soul Burn"));

        // Agar tidak terjadi memory leak
        scanner.close();
    }
}
