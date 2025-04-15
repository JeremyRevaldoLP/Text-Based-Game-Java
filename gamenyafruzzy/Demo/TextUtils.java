public class TextUtils {
    public static void typeWriter(String text, int delayMillis) {
        for (char ch : text.toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Overload dengan delay default
    public static void typeWriter(String text) {
        typeWriter(text, 30); // 30 ms per karakterr
    }
}
