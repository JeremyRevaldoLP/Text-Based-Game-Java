import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Gunakan DialogueManager untuk interaksi dengan pemain
        Scanner scanner = new Scanner(System.in);

        DialogueManager.showDialogue("Narrator", "=== Takut dan Lapar ===");
        
        // Meminta nama karakter dengan DialogueManager
        DialogueManager.showDialogue("Narrator", "Masukkan nama karaktermu: ");
        String name = scanner.nextLine();

        // Memilih kelas karakter menggunakan DialogueManager
        DialogueManager.showDialogue("Narrator", "\nPilih kelas karaktermu:");
        String[] classes = {"Guard", "Primitive Man", "Scholarmancer", "Prisoner"};
        
        int choice = DialogueManager.showChoiceDialogue("Narrator", "Pilihanmu (1-4):", classes);
        
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
                DialogueManager.showDialogue("Narrator", "Pilihan tidak valid. Kamu menjadi Guard secara default.");
                player = new Guard(name);
        }

        // Menampilkan informasi karakter yang telah dipilih
        DialogueManager.showDialogue("Narrator", "\nKarakter berhasil dibuat!");
        DialogueManager.showDialogue("Narrator", player.toString());

        // Lanjut ke cerita dengan menggunakan DialogueManager
        Story.startIntro(player);

        // Agar tidak terjadi memory leak
        scanner.close();
    }
}
