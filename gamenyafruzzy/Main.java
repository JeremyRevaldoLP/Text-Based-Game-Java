import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== GAME TITLE ===");
        System.out.println("1. Start Game");
        System.out.println("2. Exit");
        System.out.print("> Pilih: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Bersihkan newline

        if (choice == 1) {
            startGame();
        } else {
            confirmExit();
        }
    }

    private static void startGame() {
        System.out.println("\n--- Cerita Pendahuluan ---");
        System.out.println("Kamu terbangun di ruang gelap...");
        System.out.println("Suara gemerisik terdengar dari lorong.\n");

        // Contoh pertarungan pertama (nanti dipindah ke BattleManager)
        System.out.println("Musuh muncul!");
        simpleBattle();

        // Contoh exit dari dalam game
        System.out.println("\nLanjutkan petualangan? (Y/N)");
        String input = scanner.nextLine().toUpperCase();
        if (input.equals("N")) {
            confirmExit();
        }
    }

    private static void simpleBattle() {
        // Sementara pakai sistem sederhana
        System.out.println("1. Serang");
        System.out.println("2. Keluar");
        System.out.print("> Aksi: ");

        int action = scanner.nextInt();
        if (action == 2) {
            confirmExit();
        } else {
            System.out.println("Kamu menyerang! Musuh terluka.");
        }
    }

    private static void confirmExit() {
        System.out.print("Yakin ingin keluar? (Y/N): ");
        String input = scanner.nextLine().toUpperCase();
        if (input.equals("Y")) {
            System.out.println("\nGame berakhir. Sampai jumpa!");
            System.exit(0);
        }
    }
}