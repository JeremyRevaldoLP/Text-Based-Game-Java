import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String playerName;

        try (Scanner scanner = new Scanner(System.in)) {
            DialogueManager.say(null, "Cerita dimulai pada saat kamu tiba di Littleroot Town yaitu kampung halamanmu.", scanner);

            System.out.print("Masukkan nama karaktermu: ");
            playerName = scanner.nextLine();

            DialogueManager.say(playerName, "Aku pulang, Ibu!", scanner);
            DialogueManager.say("Mom", "Wah, sudah lama tidak ketemu anakku. Kamu sudah mendengar dari Professor Jack kalau kamu akan menjadi Pokémon Trainer?", scanner);
            DialogueManager.say(playerName, "Ya, aku tahu.", scanner);
            DialogueManager.say("Mom", "Baiklah, simpan dulu barang-barangmu dan pergilah ke laboratoriumnya.", scanner);
            DialogueManager.say(playerName, "Baik, Ibu.", scanner);
            DialogueManager.say(null, "Kamu menyimpan barang-barangmu.", scanner);
            DialogueManager.say(playerName, "Baiklah, aku harus pergi ke lab sekarang.", scanner);

            DialogueManager.say(null, "Sesampainya di lab...", scanner);
            DialogueManager.say(playerName, "Halo Profesor Jack, sudah lama tidak ketemu.", scanner);
            DialogueManager.say("Prof. Jack", "Wohoo... " + playerName + ", kamu sudah sampai.", scanner);
            DialogueManager.say(playerName, "Ya, aku tidak sabar bertemu dengan Pokémonku.", scanner);
            DialogueManager.say("Prof. Jack", "Baiklah, aku mempunyai 3 Pokémon di sini. Kamu bisa memilih yang mana saja, tapi hanya satu saja.", scanner);
            System.out.println("1. Oshawott (water type pokemon)");
            System.out.println("2. Charmander (fire type pokemon)");
            System.out.println("3. Sprigatito (grass type pokemon)");

            System.out.print("Pilih Pokémon kamu (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // buang newline

            Pokemon chosen = null;
            Pokemon rivalPokemon = null;

            // Pilihan Pokémon player dan rival otomatis counter
            if (choice == 1) { // Oshawott
                chosen = new Pokemon("Oshawott", "Water", 80, 12, 8,
                    Arrays.asList(
                        new Move("Water Gun", "Water", 15),
                        new Move("Tackle", "Normal", 10),
                        new Move("Sword Dance", "Normal", 0, "increase_attack"),
                        new Move("Tail Whip", "Normal", 0, "decrease_defense")
                    )
                );
                rivalPokemon = new Pokemon("Sprigatito", "Grass", 80, 12, 8,
                    Arrays.asList(
                        new Move("Leafage", "Grass", 15),
                        new Move("Scratch", "Normal", 10),
                        new Move("Growth", "Normal", 0, "increase_attack"),
                        new Move("Growl", "Normal", 0, "decrease_attack")
                    )
                );
            } else if (choice == 2) { // Charmander
                chosen = new Pokemon("Charmander", "Fire", 80, 12, 8,
                    Arrays.asList(
                        new Move("Ember", "Fire", 15),
                        new Move("Scratch", "Normal", 10),
                        new Move("Sword Dance", "Normal", 0, "increase_attack"),
                        new Move("Growl", "Normal", 0, "decrease_attack")
                    )
                );
                rivalPokemon = new Pokemon("Oshawott", "Water", 80, 12, 8,
                    Arrays.asList(
                        new Move("Water Gun", "Water", 15),
                        new Move("Tackle", "Normal", 10),
                        new Move("Sword Dance", "Normal", 0, "increase_attack"),
                        new Move("Tail Whip", "Normal", 0, "decrease_defense")
                    )
                );
            } else { // Sprigatito
                chosen = new Pokemon("Sprigatito", "Grass", 80, 12, 8,
                    Arrays.asList(
                        new Move("Leafage", "Grass", 15),
                        new Move("Scratch", "Normal", 10),
                        new Move("Growth", "Normal", 0, "increase_attack"),
                        new Move("Growl", "Normal", 0, "decrease_attack")
                    )
                );
                rivalPokemon = new Pokemon("Charmander", "Fire", 80, 12, 8,
                    Arrays.asList(
                        new Move("Ember", "Fire", 15),
                        new Move("Scratch", "Normal", 10),
                        new Move("Sword Dance", "Normal", 0, "increase_attack"),
                        new Move("Tail Whip", "Normal", 0, "decrease_defense")
                    )
                );
            }
            

            DialogueManager.say(null, "Setelah memilih Pokémon pertamamu, kamu memegang Pokéball tersebut dengan rasa bangga.", scanner);
            DialogueManager.say("Prof. Jack", "Itu pilihan yang bagus! Rawat dia baik-baik, ya.", scanner);
            DialogueManager.say("Prof. Jack", "Sebagai pelatih baru, kamu akan memulai perjalananmu dari sini. Tapi...", scanner);
            DialogueManager.say("Prof. Jack", "... kamu harus bertarung sekali dulu sebelum benar-benar siap.", scanner);

            DialogueManager.say(null, "Tiba-tiba, seorang anak muncul dari pintu laboratorium.", scanner);
            DialogueManager.say("???", "Heh, jadi kamu yang katanya pelatih baru?", scanner);
            DialogueManager.say(null, "Seorang rival bernama Irun muncul dengan ekspresi percaya diri.", scanner);
            DialogueManager.say("Irun", "Namaku Irun. Kalau kamu serius mau jadi pelatih, tunjukkan kemampuanmu!", scanner);
            DialogueManager.say(playerName, "Baik, aku tidak takut!", scanner);
            DialogueManager.say(null, "Pertarungan pertamamu dimulai!", scanner);

            BattleManager.startBattle(playerName, chosen, rivalPokemon, "Irun");
        }
    }
}
