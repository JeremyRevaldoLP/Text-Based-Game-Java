import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Main game loop
        while(true) {

            SkillUnlockTracker tracker = new SkillUnlockTracker();

            showTitle(scanner); // Pass scanner to title screen

            // Game setup
            TextUtils.typeWriter("\nA voice echoes in your head...", 50);
            TextUtils.typeWriter("The fear and hunger dungeon slowly corrupt your mind...", 40);
            TextUtils.typeWriter("Enter your name: ", 30);
            String playerName = scanner.nextLine();
            
            TextUtils.typeWriter("\nThe chains here rattle as you try to remember your past...", 60);
            Character player = chooseClass(scanner, playerName);
            
            // Create enemies
            Character enemy1 = new Character("Rotten Guard", 60, 12, 0, null);
            Character enemy2 = new Character("The Maw", 25, 30, 0, null);
            Character boss = new Character("Crippled Warden", 110, 22, 0, null);
            
            // Battle sequence
            BattleManager battleManager = new BattleManager(scanner);

            TextUtils.typeWriter("\nThe smell of decay fills the air...", 70);
            TextUtils.typeWriter("A guard runs toward you!", 30);
            TextUtils.typeWriter("Its rusted sword glows no more...", 50);
            battleManager.battle(player, enemy1);
            
            if (player.isAlive()) {
                TextUtils.typeWriter("\nThe guard's body lies still...");
                TextUtils.typeWriter("You hear a whisper...");
                System.out.println("1. Walk away from the corpse");
                System.out.println("2. Pray for safety");
                TextUtils.typeWriter("How do you feel? (1 or 2): ");
                tracker.choiceAfterGuard = getValidClassChoice(scanner); 

                TextUtils.typeWriter("\nA growl echoes through the halls...", 70);
                TextUtils.typeWriter("a creature crawls from the darkness", 30);
                TextUtils.typeWriter("Its mouth drips with hunger...", 50);
                battleManager.battle(player, enemy2);
            }

            if (player.isAlive()) {
                TextUtils.typeWriter("\nThe Maw melts into the shadow...");
                TextUtils.typeWriter("You feel the dungeons eyes on you.");
                System.out.println("1. Laugh in the darkness");
                System.out.println("2. Remain silent");
                TextUtils.typeWriter("What do you do? (1 or 2): ");
                tracker.choiceAfterMaw = getValidClassChoice(scanner);

                applyUnlockedSkill(player, tracker);

                TextUtils.typeWriter("\nYou run desperately, searching for an escape from this hell...", 70);
                TextUtils.typeWriter("Your thoughts cant comprehend this twisted dungeon", 30);
                TextUtils.typeWriter("A terrifying presence emerges from the shadows.", 50);
                battleManager.battle(player, boss);
            }
            
            if (player.isAlive()) {
                showVictory(playerName);
                // Will automatically loop back to title screen
            }
        }
    }

    private static void applyUnlockedSkill(Character player, SkillUnlockTracker tracker) {
        int code = tracker.getComboCode();
        String className = player.characterClass;

        TextUtils.typeWriter("\nA memory awakens deep inside you...");
        TextUtils.typeWriter("You remember who you are.");
    
        if (className.equals("Guard")) {
            switch (code) {
                case 11: player.cooldowns.put("Iron Wall", 0);
                player.unlockedSkillName = "Iron Wall";
                player.unlockedSkillDescription = "Iron Wall: Block all damage for 2 turns";
                TextUtils.typeWriter("Unlocked skill: " + player.unlockedSkillDescription); 
                break;

                case 12: player.cooldowns.put("Boost", 0); 
                player.unlockedSkillName = "Boost";
                player.unlockedSkillDescription = "Boost: Next attack deals 50% more damage";
                TextUtils.typeWriter("Unlocked skill: " + player.unlockedSkillDescription);
                break;

                case 21: player.applyFortify();
                player.unlockedSkillName = "Fortify=";
                player.unlockedSkillDescription = "Fortify: (Passive): Max HP and HP increased by 10";
                TextUtils.typeWriter("Unlocked passive: " + player.unlockedSkillDescription);
                break; 

                case 22: player.cooldowns.put("Last Bastion", 0);
                player.unlockedSkillName = "Last Bastion";
                player.unlockedSkillDescription = "Last Bastion: Cannot die for 2 turns"; 
                TextUtils.typeWriter("Unlocked skill: " + player.unlockedSkillDescription);
                break;
            }

        } else if (className.equals("Prisoner")) {
            switch (code) {
                case 11: player.cooldowns.put("Backstab", 0); 
                player.unlockedSkillName = "Backstab";
                player.unlockedSkillDescription = "Backstab: Deal bonus damage (more if enemy HP < 50%)";
                TextUtils.typeWriter("Unlocked skill: " + player.unlockedSkillDescription);
                break;

                case 12: player.cooldowns.put("It's Either You or Me", 0); 
                player.unlockedSkillName = "It's Either You or Me";
                player.unlockedSkillDescription = "It's Either You or Me: 50/50 chance to reduce someone to 1 HP";
                TextUtils.typeWriter("Unlocked skill: " + player.unlockedSkillDescription);
                break;

                case 21: player.cooldowns.put("Bleak Strike", 0); 
                player.unlockedSkillName = "Bleak Strike";
                player.unlockedSkillDescription = "Bleak Strike: Deal 120% damage and gain +10 evasion";
                TextUtils.typeWriter("Unlocked skill: " + player.unlockedSkillDescription);
                break;

                case 22: player.cooldowns.put("Lifesteal", 0);
                player.unlockedSkillName = "Lifesteal";
                player.unlockedSkillDescription = "Lifesteal: Deal damage and heal the same amount"; 
                TextUtils.typeWriter("Unlocked skill: " + player.unlockedSkillDescription);
                break;
            }
        }
    }

    private static void showTitle(Scanner scanner) {
        System.out.println("====================================");
        TextUtils.typeWriter("     F E A R  A N D  H U N G E R    ", 40);
        System.out.println("====================================");
        
        try {
            Thread.sleep(1000);
            
            // Menu selection
            TextUtils.typeWriter("\n1. Play", 30);
            TextUtils.typeWriter("2. Quit", 30);
            TextUtils.typeWriter("Enter your choice (1-2): ", 20);
            
            int choice = getValidMenuChoice(scanner);
            
            if (choice == 2) {
                TextUtils.typeWriter("\nThe fear and hunger lets you go...", 50);
                System.exit(0);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // If choose incorrectly
    private static int getValidMenuChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    TextUtils.typeWriter("Please enter 1 or 2: ", 30);
                }
            } catch (NumberFormatException e) {
                TextUtils.typeWriter("Please enter a number (1 or 2): ", 30);
            }
        }
    }

    private static Character chooseClass(Scanner scanner, String playerName) {
        TextUtils.typeWriter("\nChoose your past:", 40);
        System.out.println("1. Guard");
        System.out.println("2. Prisoner");
        TextUtils.typeWriter("Enter your choice (1 or 2): ");
        
        int classChoice = getValidClassChoice(scanner);
        
        if (classChoice == 1) {
            TextUtils.typeWriter("\nYou look down on yourself. The uniform still fits.", 40);
            TextUtils.typeWriter("But in here it means nothing.", 50);
            return new Character(playerName, 120, 15,15, "Guard");
        } else {
            TextUtils.typeWriter("\nScars all over your body tells a story. The growling in your stomach keeps you sharp.", 40);
            TextUtils.typeWriter("You thought you had control over your choices, but you ended up here.", 40);
            TextUtils.typeWriter("You've survived worse than this.", 50);
            return new Character(playerName, 80, 25, 40, "Prisoner");
        }
    }

    // If choose incorrectly
    private static int getValidClassChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    TextUtils.typeWriter("Please enter 1 or 2: ", 30);
                }
            } catch (NumberFormatException e) {
                TextUtils.typeWriter("Please enter a number (1 or 2): ", 30);
            }
        }
    }

    public static void showVictory(String playerName) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TextUtils.typeWriter("The last enemy falls...", 50);
        TextUtils.typeWriter("The hunger remains.", 100);
        TextUtils.typeWriter("The fear lingers.", 100);
        TextUtils.typeWriter("You defeated all and gained nothing.", 70);
    }
}