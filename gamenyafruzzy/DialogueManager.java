import java.util.Scanner;

public class DialogueManager {

    private static Scanner scanner = new Scanner(System.in);

    // Menampilkan dialog dengan format yang lebih bagus
    public static void showDialogue(String speaker, String dialogue) {
        System.out.println("\n" + speaker + ": " + dialogue);
    }

    // Menampilkan dialog dengan pilihan
    public static int showChoiceDialogue(String speaker, String dialogue, String[] choices) {
        System.out.println("\n" + speaker + ": " + dialogue);
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i]);
        }

        System.out.print("\nPilih (1-" + choices.length + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline setelah input
        return choice;
    }

    // Menampilkan percakapan panjang dengan jeda
    public static void showLongDialogue(String speaker, String[] dialogues) {
        for (String dialogue : dialogues) {
            showDialogue(speaker, dialogue);
            waitForInput();
        }
    }

    // Fungsi untuk menunggu input agar ada jeda sebelum melanjutkan
    private static void waitForInput() {
        System.out.print("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine(); // Menunggu Enter untuk melanjutkan
    }

    // Menutup scanner ketika tidak diperlukan lagi
    public static void close() {
        scanner.close();
    }
}
