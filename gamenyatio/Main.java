import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String playerName;

        try (Scanner scanner = new Scanner(System.in)) {
            DialogueManager.say(null, "The story begins as you arrive at Littleroot Town, your hometown.", scanner);

            System.out.print("Enter your character's name: ");
            playerName = scanner.nextLine();

            DialogueManager.say(playerName, "I'm home, Mom!", scanner);
            DialogueManager.say("Mom", "Wow, it's been a while since I’ve seen my child. Have you heard from Professor Jack that you're going to become a Pokémon Trainer?", scanner);
            DialogueManager.say(playerName, "Yes, I know.", scanner);
            DialogueManager.say("Mom", "Alright, put your things away first and then head to his lab.", scanner);
            DialogueManager.say(playerName, "Okay, Mom.", scanner);
            DialogueManager.say(null, "You put away your belongings.", scanner);
            DialogueManager.say(playerName, "Alright, I need to go to the lab now.", scanner);

            DialogueManager.say(null, "Upon arriving at the lab...", scanner);
            DialogueManager.say(playerName, "Hello Professor Jack, long time no see.", scanner);
            DialogueManager.say("Prof. Jack", "Wohoo... " + playerName + ", you're here.", scanner);
            DialogueManager.say(playerName, "Yes, I can’t wait to meet my Pokémon.", scanner);
            DialogueManager.say("Prof. Jack", "Alright, I have 3 Pokémon here. You can choose any one, but only one.", scanner);
            System.out.println("1. Oshawott (water type Pokémon)");
            System.out.println("2. Charmander (fire type Pokémon)");
            System.out.println("3. Sprigatito (grass type Pokémon)");

            System.out.print("Choose your Pokémon (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Pokemon chosen = null;
            Pokemon rivalPokemon = null;

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

            DialogueManager.say(null, "After choosing your first Pokémon, you hold the Pokéball proudly.", scanner);
            DialogueManager.say("Prof. Jack", "That's a great choice! Take good care of it, okay?", scanner);
            DialogueManager.say("Prof. Jack", "As a new trainer, your journey starts here. But...", scanner);
            DialogueManager.say("Prof. Jack", "... you have to battle once before you’re truly ready.", scanner);

            DialogueManager.say(null, "Suddenly, a kid appears from the lab's door.", scanner);
            DialogueManager.say("???", "Heh, so you're the so-called new trainer?", scanner);
            DialogueManager.say(null, "A rival named Irun appears with a confident expression.", scanner);
            DialogueManager.say("Irun", "My name is Irun. If you're serious about being a trainer, show me what you've got!", scanner);
            DialogueManager.say(playerName, "Alright, I'm not afraid!", scanner);
            DialogueManager.say(null, "Your first battle begins!", scanner);

            BattleManager.startBattle(playerName, chosen, rivalPokemon, "Irun", false);
        }
    }
}
