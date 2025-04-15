import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Main game loop
        while(true) {
            showTitle(scanner); // Pass scanner to title screen
            
            // Game setup
            TextUtils.typeWriter("\nA voice echoes in your head...", 50);
            TextUtils.typeWriter("The fear and hunger dungeon slowly corrupt your mind...", 40);
            TextUtils.typeWriter("Enter your name: ", 30);
            String playerName = scanner.nextLine();
            
            TextUtils.typeWriter("\nThe chains here rattle as you try to remember your past...", 60);
            Character player = chooseClass(scanner, playerName);
            
            // Create enemies
            Character enemy1 = new Character("Rotten Guard", 60, 12, 0);
            Character boss = new Character("Crippled Warden", 110, 22, 0);
            
            // Battle sequence
            TextUtils.typeWriter("\nThe stench of decay fills the air...", 70);
            TextUtils.typeWriter("A guard shambles toward you!", 30);
            TextUtils.typeWriter("Its rusted sword gleams dully...", 50);
            
            BattleManager battleManager = new BattleManager(scanner);
            battleManager.battle(player, enemy1);
            
            if (player.isAlive()) {
                TextUtils.typeWriter("\nYou run desperately, searching for an escape from this hell...", 70);
                TextUtils.typeWriter("Your thoughts fracture as you try to comprehend this twisted dungeon", 30);
                TextUtils.typeWriter("A terrifying presence emerges from the shadows.", 50);
                battleManager.battle(player, boss);
            }
            
            if (player.isAlive()) {
                showVictory(playerName);
                // Will automatically loop back to title screen
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
        TextUtils.typeWriter("\nChoose your wretched past:", 40);
        System.out.println("1. Guard");
        System.out.println("2. Prisoner");
        TextUtils.typeWriter("Enter your choice (1 or 2): ");
        
        int classChoice = getValidClassChoice(scanner);
        
        if (classChoice == 1) {
            TextUtils.typeWriter("\nYou look down on yourself. The uniform still fits.", 40);
            TextUtils.typeWriter("But in here it means nothing.", 50);
            return new Character(playerName, 120, 15, 15);
        } else {
            TextUtils.typeWriter("\nScars all over your body tells a story. The growling in your stomach keeps you sharp.", 40);
            TextUtils.typeWriter("You thought you had control over your choices, but you ended up here.", 40);
            TextUtils.typeWriter("You've survived worse than this.", 50);
            return new Character(playerName, 80, 25, 40);
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