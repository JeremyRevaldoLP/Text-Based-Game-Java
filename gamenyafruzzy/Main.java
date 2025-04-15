import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TextUtils.typeWriter("=== Takut dan Lapar ===");
        System.out.print("Masukkan nama karaktermu: ");
        String name = scanner.nextLine();

        TextUtils.typeWriter("\nPilih kelas karaktermu:");
        TextUtils.typeWriter("1. Guard");
        TextUtils.typeWriter("2. Primitive Man");
        TextUtils.typeWriter("3. Scholarmancer");
        TextUtils.typeWriter("4. Prisoner");
        System.out.print("Pilihanmu (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Buat hapus newline

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
                TextUtils.typeWriter("Pilihan tidak valid. Kamu menjadi Guard secara default.");
                player = new Guard(name);
        }

        TextUtils.typeWriter("\nKarakter berhasil dibuat!");
        System.out.println(player);

        // Lanjut ke cerita
        Story.startIntro(player);

        // Agar tidak terjadi memory leak
        scanner.close();
    }
}