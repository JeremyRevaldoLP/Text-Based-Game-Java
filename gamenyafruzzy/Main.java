// Main.java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎮 Welcome to the Text Adventure Battle Game!");
        System.out.print("Enter your name, adventurer: ");
        String name = scanner.nextLine();

        Player player = chooseClass(scanner, name);
        System.out.println("\n📖 Let the story begin...\n");
        runClassStory(player, scanner);

        System.out.println("\n🔥 The battle begins now!");

        Enemy[] enemies = {
            new Enemy("Savage Wolf", 20, 5, 1, 0),
            new Enemy("Cave Bandit", 25, 6, 2, 1),
            new Enemy("Dark Guard", 30, 7, 3, 2)
        };

        for (Enemy enemy : enemies) {
            BattleManager bm = new BattleManager(player, enemy);
            bm.startBattle();
        }

        System.out.println("\n👑 You feel a strange presence nearby...");
        System.out.println("A powerful foe approaches!");

        Enemy boss = new Enemy("Dread King", 50, 10, 5, 0);
        boss.addSkill(new Skill("Dark Slash", "Deal 1.5x damage.", 3));
        BattleManager finalBattle = new BattleManager(player, boss);
        finalBattle.startBattle();

        System.out.println("\n🎉 Congratulations, " + player.getName() + "!");
        System.out.println("You've defeated all enemies and completed the story.");
        System.out.println("Thanks for playing!");
    }

    private static Player chooseClass(Scanner scanner, String name) {
        System.out.println("\nChoose your class:");
        System.out.println("1. Guard");
        System.out.println("2. Primitive Man");
        System.out.println("3. Scholarmancer");
        System.out.println("4. Prisoner");
        System.out.print("Enter choice [1-4]: ");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: return new Guard(name);
            case 2: return new PrimitiveMan(name);
            case 3: return new Scholarmancer(name);
            case 4: return new Prisoner(name);
            default:
                System.out.println("Invalid choice. Defaulting to Guard.");
                return new Guard(name);
        }
    }

    private static void runClassStory(Player player, Scanner scanner) {
        System.out.println("📘 Story begins for " + player.getClass().getSimpleName() + "...\n");

        for (int i = 1; i <= 3; i++) {
            System.out.println("Choice " + i + ":");
            System.out.println("1. Option A");
            System.out.println("2. Option B");
            System.out.print("Choose [1-2]: ");
            String input = scanner.nextLine();
            int choice = (input.equals("1")) ? 0 : 1;
            player.learnSkillFromChoice(i, choice); // fungsi ini akan dibuat di masing-masing class
            System.out.println();
        }

        System.out.println("✨ You have obtained your unique class skills!\n");
    }
}
