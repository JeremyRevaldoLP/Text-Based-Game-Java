import java.util.Scanner;

public class DialogueManager {

    public static void say(String speaker, String text, Scanner scanner) {
        if (speaker != null) {
            System.out.print(speaker + ": ");
        }

        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (speaker != null) {
            System.out.println("(Press enter to continue...)");
            scanner.nextLine(); // Gunakan scanner yang dikirim dari Main
        }
    }

    // Versi otomatis (tidak butuh Enter)
    public static void sayAuto(String speaker, String text) {
        if (speaker != null) {
            System.out.print(speaker + ": ");
        }

        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();

        try {
            Thread.sleep(700); // jeda ekstra agar terasa alami
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
