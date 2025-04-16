import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String playerName;

        // Initializing the chosen Pokemon variable here
        Pokemon chosen = null;
        Pokemon rivalPokemon = null;  // Deklarasikan di luar if-else
  

        try (Scanner scanner = new Scanner(System.in)) {
            DialogueManager.say(null, "The story begins as you arrive at Littleroot Town, your hometown.", scanner);

            System.out.print("Enter your character's name: ");
            playerName = scanner.nextLine();

            DialogueManager.say(playerName, "I'm home, Mom!", scanner);
            DialogueManager.say("Mom", "Wow, it's been a while since I’ve seen my child. Have you heard from Professor Jack that you're going to become a Pokemon Trainer?", scanner);
            DialogueManager.say(playerName, "Yes, I know.", scanner);
            DialogueManager.say("Mom", "Alright, put your things away first and then head to his lab.", scanner);
            DialogueManager.say(playerName, "Okay, Mom.", scanner);
            DialogueManager.say(null, "You put away your belongings.", scanner);
            DialogueManager.say(playerName, "Alright, I need to go to the lab now.", scanner);

            DialogueManager.say(null, "Upon arriving at the lab...", scanner);
            DialogueManager.say(playerName, "Hello Professor Jack, long time no see.", scanner);
            DialogueManager.say("Prof. Jack", "Wohoo... " + playerName + ", you're here.", scanner);
            DialogueManager.say(playerName, "Yes, I can’t wait to meet my Pokemon.", scanner);
            DialogueManager.say("Prof. Jack", "Alright, I have 3 Pokemon here. You can choose any one, but only one.", scanner);
            System.out.println("1. Oshawott (water type Pokemon)");
            System.out.println("2. Charmander (fire type Pokemon)");
            System.out.println("3. Sprigatito (grass type Pokemon)");

            System.out.print("Choose your Pokemon (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Assign chosen Pokemon based on the player's choice
            if (choice == 1) { // Oshawott (Water)
                chosen = new Pokemon("Oshawott", "Water", 80, 12, 8,
                    Arrays.asList(
                        new Move("Water Gun", "Water", 15),
                        new Move("Tackle", "Normal", 12),
                        new Move("Sword Dance", "Normal", 0, "increase_attack"),
                        new Move("Tail Whip", "Normal", 0, "decrease_defense")
                    )
                );

                // Irun chooses Grass type (counter to Water)
                rivalPokemon = new Pokemon("Sprigatito", "Grass", 80, 12, 8,
                    Arrays.asList(
                        new Move("Leafage", "Grass", 15),
                        new Move("Scratch", "Normal", 12),
                        new Move("Growth", "Normal", 0, "increase_attack"),
                        new Move("Growl", "Normal", 0, "decrease_attack")
                    )
                );

            } else if (choice == 2) { // Charmander (Fire)
                chosen = new Pokemon("Charmander", "Fire", 80, 12, 8,
                    Arrays.asList(
                        new Move("Ember", "Fire", 15),
                        new Move("Scratch", "Normal", 12),
                        new Move("Sword Dance", "Normal", 0, "increase_attack"),
                        new Move("Growl", "Normal", 0, "decrease_attack")
                    )
                );

                // Irun chooses Water type (counter to Fire)
                rivalPokemon = new Pokemon("Oshawott", "Water", 80, 12, 8,
                    Arrays.asList(
                        new Move("Water Gun", "Water", 15),
                        new Move("Tackle", "Normal", 12),
                        new Move("Sword Dance", "Normal", 0, "increase_attack"),
                        new Move("Tail Whip", "Normal", 0, "decrease_defense")
                    )
                );

            } else { // Sprigatito (Grass)
                chosen = new Pokemon("Sprigatito", "Grass", 80, 12, 8,
                    Arrays.asList(
                        new Move("Leafage", "Grass", 15),
                        new Move("Scratch", "Normal", 12),
                        new Move("Growth", "Normal", 0, "increase_attack"),
                        new Move("Growl", "Normal", 0, "decrease_attack")
                    )
                );

                // Irun chooses Fire type (counter to Grass)
                rivalPokemon = new Pokemon("Charmander", "Fire", 80, 12, 8,
                    Arrays.asList(
                        new Move("Ember", "Fire", 15),
                        new Move("Scratch", "Normal", 12),
                        new Move("Sword Dance", "Normal", 0, "increase_attack"),
                        new Move("Growl", "Normal", 0, "decrease_attack")
                    )
                );
            }

            DialogueManager.say(null, "After choosing your first Pokemon, you hold the Pokeball proudly.", scanner);
            DialogueManager.say("Prof. Jack", "That's a great choice! Take good care of it, okay?", scanner);
            DialogueManager.say("Prof. Jack", "As a new trainer, your journey starts here. But...", scanner);
            DialogueManager.say("Prof. Jack", "... you have to battle once before you’re truly ready.", scanner);

            DialogueManager.say(null, "Suddenly, a kid appears from the lab's door.", scanner);
            DialogueManager.say("???", "Heh, so you're the so-called new trainer?", scanner);
            DialogueManager.say(null, "A rival named Irun appears with a confident expression.", scanner);
            DialogueManager.say("Irun", "My name is Irun. If you're serious about being a trainer, show me what you've got!", scanner);
            DialogueManager.say(playerName, "Alright, I'm not afraid!", scanner);
            DialogueManager.say(null, "Your first battle begins!", scanner);

            // Use the chosen Pokemon for the battle with the rival
            BattleManager.startBattle(playerName, chosen, rivalPokemon, "Irun", false);
            chosen.resetToDefaultStats();

            // After the battle, Professor Jack talks to both players
            DialogueManager.say(null, "=========================", scanner);
            DialogueManager.say("Prof. Jack", "Both of you are doing amazing, here let me heal your pokemon. I hope you two can become Pokemon champions.", scanner);
            DialogueManager.say("Prof. Jack", "But sadly, the Pokemon Champion title is only for one person.", scanner);
            DialogueManager.say("Prof. Jack", "Now go, start your journey and come back when you become a Pokemon Champion.", scanner);
            DialogueManager.say("Prof. Jack", "And if you don't know where to go, I’ve already added the map for the whole Senova region to your PokeDevice.", scanner);

            DialogueManager.say(playerName, "Okay, Professor Jack.", scanner);
            DialogueManager.say("Irun", "Well then, " + playerName + ", I’m going first to the forest. I’ll see you in Floration Town.", scanner);

            DialogueManager.say(null, "You are now free to continue your journey.", scanner);

            
            // Add route selection
            int routeChoice;
            boolean continueJourney = true;

            while (continueJourney) {
                System.out.println("What would you like to do?");
                System.out.println("1. I think I should go to route 138");
                System.out.println("2. I think I should go to route 56");
                System.out.println("3. Check your Pokemon status");

                System.out.print("Choose your route (1 or 3): ");
                routeChoice = scanner.nextInt();

                switch (routeChoice) {
                    case 1:
                        DialogueManager.say(null, "You head towards Route 138. The air is fresh, and the tall grass shakes as you walk. You might encounter wild Pokemon here.", scanner);
                        EncounterWildPokemon(scanner, chosen, playerName); // Pass the chosen Pokemon for battle
                        continueJourney = false; // Exit after the encounter
                        break;
                    case 2:
                        DialogueManager.say(null, "You walk towards Route 56. It’s only the ocean. The sound of waves crashing fills the air.", scanner);
                        // Route 56 narative finished, now give option to go back to Littleroot Town
                        System.out.println("1. Go back to Littleroot Town");
                        int nextRouteChoice = scanner.nextInt();
                        if (nextRouteChoice == 1) {
                            DialogueManager.say(null, "You return to Littleroot Town.", scanner);
                            continueJourney = true; // Go back to the main route selection
                        }
                        break;
                    case 3:
                        System.out.println("\n-- Your Pokemon's Status --");
                        System.out.println("Name : " + chosen.name);
                        System.out.println("Type : " + chosen.type);
                        System.out.println("HP   : " + chosen.hp + " / " + chosen.maxHp);
                        System.out.println("ATK  : " + chosen.attack);
                        System.out.println("DEF  : " + chosen.defense);
                        break; // tetap di loop ini, biar bisa pilih ulang
                    default:
                        System.out.println("Invalid choice. Please choose a valid route.");
                }
            }
        }
    }

    private static void EncounterWildPokemon(Scanner scanner, Pokemon chosen, String playerName) {
        boolean stayInRoute = true;
    
        while (stayInRoute) {
            // Random wild Pokemon
            String[] wildPokemon = {"Pidgey", "Wurmple", "Zigzagoon", "Ralts"};
            int randomIndex = (int) (Math.random() * wildPokemon.length);
            String wildPokemonName = wildPokemon[randomIndex];
    
            DialogueManager.say(null, "You encounter a wild " + wildPokemonName + "!", scanner);
    
            Pokemon wildPokemonInstance = new Pokemon(wildPokemonName, "Normal", 40, 10, 5,
            Arrays.asList(
                new Move("Tackle", "Normal", 10),
                new Move("Bite", "Normal", 0, "increase_attack")  
            ));
    
            BattleManager.startBattle(playerName, chosen, wildPokemonInstance, wildPokemonName, true);
            chosen.resetStats();

            //  CEK apakah Pokemon kamu pingsan
            if (chosen.isFainted()) {
                DialogueManager.say(null, chosen.name + " has fainted!", scanner);
                DialogueManager.say(null, "You rush back to the nearest Pokémon Center in Littleroot Town...", scanner);
                DialogueManager.say("Nurse Joy", "Your Pokémon has been healed. Please take better care next time!", scanner);
                chosen.healToFull();
                stayInRoute = true; 
                return;
            }

            boolean validChoice = false;

            while (!validChoice) {
                System.out.println("\nWhat would you like to do next?");
                System.out.println("1. Continue your adventure to route 138");
                System.out.println("2. Check your Pokémon status");
                System.out.println("3. Leave the game");
            
                int choice = scanner.nextInt(); // Menangkap pilihan pemain
            
                switch (choice) {
                    case 1:
                        System.out.println("You decide to continue your adventure...");
                        validChoice = true; // Lanjutkan ke encounter selanjutnya atau kembali ke menu petualangan
                        break;
                    case 2:
                        System.out.println("\n-- Your Pokémon's Status --");
                        System.out.println("Name : " + chosen.name);
                        System.out.println("Type : " + chosen.type);
                        System.out.println("HP   : " + chosen.hp + " / " + chosen.maxHp);
                        System.out.println("ATK  : " + chosen.attack);
                        System.out.println("DEF  : " + chosen.defense);
                        break;
                    case 3:
                        System.out.println("You left the adventure. Goodbye!");
                        stayInRoute = false; // Keluar dari permainan atau loop
                        validChoice = true; // Keluar dari loop
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            }

            
            while (!validChoice) {
                System.out.println("\nWhat would you do?");
                System.out.println("1. Stay in Route 138");
                System.out.println("2. Check your Pokemon status");
                System.out.println("3. Leave the game");
    
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
    
                switch (choice) {
                    case 1:
                        System.out.println("You decide to stay in Route 138 and continue your adventure...");
                        validChoice = true; // lanjut ke encounter selanjutnya
                        break;
                    case 2:
                        System.out.println("\n-- Your Pokemon's Status --");
                        System.out.println("Name : " + chosen.name);
                        System.out.println("Type : " + chosen.type);
                        System.out.println("HP   : " + chosen.hp + " / " + chosen.maxHp);
                        System.out.println("ATK  : " + chosen.attack);
                        System.out.println("DEF  : " + chosen.defense);
                        break;
                    case 3:
                        System.out.println("You left the adventure. Goodbye!");
                        stayInRoute = false;
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                }
            }
        }
    }}
