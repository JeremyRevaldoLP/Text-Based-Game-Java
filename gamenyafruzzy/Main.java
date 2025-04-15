import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Fear and Hunger: Lite ===");
        System.out.print("Masukkan nama karaktermu: ");
        String name = scanner.nextLine();

        System.out.println("\nPilih kelas karaktermu:");
        System.out.println("1. Guard");
        System.out.println("2. Primitive Man");
        System.out.println("3. Scholarmancer");
        System.out.println("4. Prisoner");
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
                System.out.println("Pilihan tidak valid. Kamu menjadi Guard secara default.");
                player = new Guard(name);
        }

        System.out.println("\nKarakter berhasil dibuat!");
        System.out.println(player);

        // Lanjut ke cerita
        Story.startIntro(player);
    }
}