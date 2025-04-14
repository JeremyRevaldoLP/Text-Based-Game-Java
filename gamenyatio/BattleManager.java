import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void startBattle(String playerName, Pokemon playerPokemon, Pokemon rivalPokemon, String rivalName, boolean isWildBattle) {
        System.out.println("\n--- Battle start! ---");
        System.out.println(playerName + " Sending " + playerPokemon.name + "!");
        System.out.println(rivalName + " Sending " + rivalPokemon.name + "!");

        playerPokemon.resetBuffs();
        rivalPokemon.resetBuffs();

        while (playerPokemon.isAlive() && rivalPokemon.isAlive()) {
            System.out.println("\n---" + playerName + " Turn ---");
            System.out.println("What would you do?");
            System.out.println("1. Attack");
            System.out.println("2. Inventory");
            if (isWildBattle) {
                System.out.println("3. Run");
            }

            int actionChoice = scanner.nextInt();
            scanner.nextLine();

            if (actionChoice == 1) {
                System.out.println("Select your pokemon moves:");
                List<Move> moves = playerPokemon.moves;
                for (int i = 0; i < moves.size(); i++) {
                    System.out.println((i + 1) + ". " + moves.get(i).name + " (" + moves.get(i).type + ")");
                }
                int moveChoice = scanner.nextInt() - 1;
                scanner.nextLine();
                if (moveChoice >= 0 && moveChoice < moves.size()) {
                    Move playerMove = moves.get(moveChoice);
                    DialogueManager.sayAuto(playerName, playerPokemon.name + " Use " + playerMove.name + "!");
                    processMove(playerMove, playerPokemon, rivalPokemon, playerName);
                } else {
                    System.out.println("It's not valid.");
                }
            } else if (actionChoice == 2) {
                InventoryManager.openInventory(playerPokemon, scanner);
            } else if (actionChoice == 3 && isWildBattle) {
                System.out.println(playerName + " Trying to flee!");
                if (random.nextInt(100) < 50) {
                    System.out.println(playerName + " You flee succesfully!");
                    break;
                } else {
                    System.out.println(playerName + " You can't ran away!");
                }
            }

            if (!rivalPokemon.isAlive()) break;

            System.out.println("\n---" + rivalName + " turn---");
            List<Move> rivalMoves = rivalPokemon.moves;
            Move rivalMove = rivalMoves.get(random.nextInt(rivalMoves.size()));
            DialogueManager.sayAuto(rivalName, rivalPokemon.name + " is using " + rivalMove.name + "!");
            processMove(rivalMove, rivalPokemon, playerPokemon, rivalName);

            System.out.println("\nStatus:");
            System.out.println(playerPokemon.name + " HP: " + playerPokemon.hp + ", ATK: " + playerPokemon.attack + ", DEF: " + playerPokemon.defense);
            System.out.println(rivalPokemon.name + " HP: " + rivalPokemon.hp + ", ATK: " + rivalPokemon.attack + ", DEF: " + rivalPokemon.defense);
        }

        System.out.println("\n--- The battle ended ---");
        if (playerPokemon.isAlive()) {
            System.out.println(playerName + " Won!");
        } else {
            System.out.println(rivalName + " Won!");
        }
    }

    private static void processMove(Move move, Pokemon attacker, Pokemon defender, String attackerName) {
        if (move.effect != null) {
            switch (move.effect) {
                case "increase_attack":
                    if (attacker.attackBuffCount < 2) {
                        attacker.attack += 2;
                        attacker.attackBuffCount++;
                        DialogueManager.sayAuto(null, attacker.name + " increasing attack!");
                    } else {
                        DialogueManager.sayAuto(null, attacker.name + " can,t increase your attack anymore!");
                    }
                    return;

                case "decrease_attack":
                    if (defender.attackDebuffCount < 2) {
                        defender.attack = Math.max(1, defender.attack - 2);
                        defender.attackDebuffCount++;
                        DialogueManager.sayAuto(null, defender.name + " losing 2 attack!");
                    } else {
                        DialogueManager.sayAuto(null, defender.name + " can't lose your defense anymore!");
                    }
                    return;

                case "increase_defense":
                    if (attacker.defenseBuffCount < 2) {
                        attacker.defense += 2;
                        attacker.defenseBuffCount++;
                        DialogueManager.sayAuto(null, attacker.name + " increasing defense!");
                    } else {
                        DialogueManager.sayAuto(null, attacker.name + " can't increase your defense anymore!");
                    }
                    return;

                case "decrease_defense":
                    if (defender.defenseDebuffCount < 2) {
                        defender.defense = Math.max(1, defender.defense - 2);
                        defender.defenseDebuffCount++;
                        DialogueManager.sayAuto(null, defender.name + " losing 2 defense!");
                    } else {
                        DialogueManager.sayAuto(null, defender.name + " can't lose your defense anymore!");
                    }
                    return;
            }
        }

        int damage = calculateDamage(move, attacker, defender);
        defender.hp -= damage;
        if (defender.hp < 0) defender.hp = 0;
        DialogueManager.sayAuto(null, "It's dealing " + damage + " damage!");
    }

    private static int calculateDamage(Move move, Pokemon attacker, Pokemon defender) {
        int damage = move.power + attacker.attack - defender.defense;
        damage = Math.max(1, damage);

        if (isSuperEffective(move.type, defender.type)) {
            DialogueManager.sayAuto(null, "It's super effective!");
            damage += random.nextInt(4) + 3;
        } else if (isNotVeryEffective(move.type, defender.type)) {
            DialogueManager.sayAuto(null, "It's not very effective...");
            damage /= 2;
            if (damage < 1) damage = 1;
        }

        if (random.nextDouble() < 0.1) {
            DialogueManager.sayAuto(null, "Critical hit!");
            damage *= 1.5;
        }

        return damage;
    }

    private static boolean isSuperEffective(String attackType, String targetType) {
        return (attackType.equals("Water") && targetType.equals("Fire")) ||
               (attackType.equals("Fire") && targetType.equals("Grass")) ||
               (attackType.equals("Grass") && targetType.equals("Water"));
    }

    private static boolean isNotVeryEffective(String attackType, String targetType) {
        return (attackType.equals("Water") && targetType.equals("Grass")) ||
               (attackType.equals("Fire") && targetType.equals("Water")) ||
               (attackType.equals("Grass") && targetType.equals("Fire"));
    }
}
