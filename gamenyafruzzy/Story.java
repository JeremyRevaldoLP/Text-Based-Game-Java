import java.util.Scanner;

public class Story {

    public static void startIntro(Character player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Prolog ---");
        System.out.println("Kamu terbangun di sebuah ruang bawah tanah gelap. Ada tiga jalan di depanmu.");

        System.out.println("1. Masuk ke lorong dengan cahaya redup.");
        System.out.println("2. Masuk ke ruangan dengan bau busuk.");
        System.out.println("3. Ikuti suara bisikan dari kegelapan.");

        System.out.print("Pilih jalurmu (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Kamu menemukan simbol sihir di lantai.");
                player.getSkills().add(new Skill("Arcane Touch", "Sentuhan magis yang melemahkan musuh", 2));
                break;
            case 2:
                System.out.println("Kamu menginjak perangkap dan belajar menghindari bahaya.");
                player.getSkills().add(new Skill("Trap Sense", "Menghindari serangan pertama", 3));
                break;
            case 3:
                System.out.println("Bisikan itu mengajarkanmu teknik terlarang.");
                player.getSkills().add(new Skill("Forbidden Chant", "Skill misterius dengan efek acak", 4));
                break;
            default:
                System.out.println("Kamu ragu dan hanya diam. Tidak ada skill tambahan.");
        }

        System.out.println("\nSkill-skill yang kamu miliki:");
        for (Skill skill : player.getSkills()) {
            System.out.println("- " + skill);
        }

        System.out.println("\n--- Petualangan dimulai... ---");

        // Di sini nanti kita lanjut ke pertarungan musuh biasa
        // BattleSystem.startBattle(player, enemy1);
    }
}
