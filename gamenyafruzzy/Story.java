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

        // Musuh pertama
        Enemy enemy1 = new Enemy("Rotten Soldier", 50, 10);
        BattleSystem.startBattle(player, enemy1);

        // Musuh kedua
        if (player.getHealth() > 0) {
            Enemy enemy2 = new Enemy("Twisted Hound", 60, 12);
            BattleSystem.startBattle(player, enemy2);
        }

        // Musuh ketiga
        if (player.getHealth() > 0) {
            Enemy enemy3 = new Enemy("Dark Priest", 70, 13);
            BattleSystem.startBattle(player, enemy3);
        }

        // Boss terakhir
        if (player.getHealth() > 0) {
            Boss finalBoss = new Boss("Ancient Horror", 100, 15, "Doom Gaze");
            BattleSystem.startBattle(player, finalBoss);
        }

        // Ending
        if (player.getHealth() > 0) {
            TextUtils.typeWriter("\n--- Kamu berhasil keluar dari kegelapan... Tamat! ---");
        } else {
            TextUtils.typeWriter("\n--- Kegelapan menelammu... Tamat. ---");
        }
        
        // Agar tidak terjadi memory leak
        scanner.close();
    }
}
