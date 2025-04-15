import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TextUtils.typeWriter("=== Takut dan Lapar ===");
        TextUtils.typeWriter("Kegelapan menyelimuti tempat ini...");

        TextUtils.typeWriter("Masukkan nama karaktermu:");
        System.out.print("> ");
        String name = scanner.nextLine();

        TextUtils.typeWriter("\nPilih kelas karaktermu:");
        TextUtils.typeWriter("1. Guard");
        TextUtils.typeWriter("2. Primitive Man");
        TextUtils.typeWriter("3. Scholarmancer");
        TextUtils.typeWriter("4. Prisoner");
        System.out.print("Pilihanmu (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Menghapus newline

        Character player;

        switch (choice) {
            case 1:
                player = new Guard(name);
                break;
            case 2:
                player = new PrimitiveMan(name);
                break;
            case 3:
                player = new Scholarmancer(name);
                break;
            case 4:
                player = new Prisoner(name);
                break;
            default:
                TextUtils.typeWriter("Pilihan tidak dikenal. Kamu terbangun sebagai Guard.");
                player = new Guard(name);
        }

        TextUtils.typeWriter("\nKarakter berhasil diciptakan.");
        TextUtils.typeWriter("Nama: " + player.getName());
        TextUtils.typeWriter("Kelas: " + player.getClass().getSimpleName());
        TextUtils.typeWriter("HP: " + player.getHealth());

        TextUtils.typeWriter("\nPetualanganmu segera dimulai...");

        // Lanjut ke cerita
        Story.startIntro(player);

        // Agar tidak terjadi memory leak
        scanner.close();
    }
}
