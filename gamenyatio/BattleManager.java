import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void startBattle(String playerName, Pokemon playerPokemon, Pokemon rivalPokemon, String rivalName) {
        System.out.println("\n--- Pertarungan Dimulai ---");
        System.out.println(playerName + " mengirim " + playerPokemon.name + "!");
        System.out.println(rivalName + " mengirim " + rivalPokemon.name + "!");

        // Reset buff awal
        playerPokemon.resetBuffs();
        rivalPokemon.resetBuffs();

        while (playerPokemon.isAlive() && rivalPokemon.isAlive()) {
            // Giliran pemain
            System.out.println("\n--- Giliran " + playerName + " ---");
            System.out.println("Pilih jurus:");
            List<Move> moves = playerPokemon.moves;
            for (int i = 0; i < moves.size(); i++) {
                System.out.println((i + 1) + ". " + moves.get(i).name + " (" + moves.get(i).type + ")");
            }

            int moveChoice = scanner.nextInt() - 1;
            scanner.nextLine(); // buang newline
            Move playerMove = moves.get(moveChoice);

            DialogueManager.sayAuto(playerName, playerPokemon.name + " menggunakan " + playerMove.name + "!");
            processMove(playerMove, playerPokemon, rivalPokemon, playerName);

            if (!rivalPokemon.isAlive()) break;

            // Giliran lawan (NPC)
            System.out.println("\n--- Giliran " + rivalName + " ---");
            List<Move> rivalMoves = rivalPokemon.moves;
            Move rivalMove = rivalMoves.get(random.nextInt(rivalMoves.size()));
            DialogueManager.sayAuto(rivalName, rivalPokemon.name + " menggunakan " + rivalMove.name + "!");
            processMove(rivalMove, rivalPokemon, playerPokemon, rivalName);

            // Status setelah ronde
            System.out.println("\nStatus:");
            System.out.println(playerPokemon.name + " HP: " + playerPokemon.hp + ", ATK: " + playerPokemon.attack + ", DEF: " + playerPokemon.defense);
            System.out.println(rivalPokemon.name + " HP: " + rivalPokemon.hp + ", ATK: " + rivalPokemon.attack + ", DEF: " + rivalPokemon.defense);
        }

        System.out.println("\n--- Pertarungan Selesai ---");
        if (playerPokemon.isAlive()) {
            System.out.println(playerName + " menang!");
        } else {
            System.out.println(rivalName + " menang!");
        }
    }

    private static void processMove(Move move, Pokemon attacker, Pokemon defender, String attackerName) {
        switch (move.effect) {
            case "increase_attack":
                if (attacker.attackBuffCount < 2) {
                    attacker.attack += 2;
                    attacker.attackBuffCount++;
                    DialogueManager.sayAuto(null, attacker.name + " meningkatkan attack!");
                } else {
                    DialogueManager.sayAuto(null, attacker.name + " tidak bisa meningkatkan attack lagi!");
                }
                break;

            case "decrease_attack":
                if (defender.attackDebuffCount < 2) {
                    defender.attack = Math.max(1, defender.attack - 2);
                    defender.attackDebuffCount++;
                    DialogueManager.sayAuto(null, defender.name + " kehilangan 2 attack!");
                } else {
                    DialogueManager.sayAuto(null, defender.name + " tidak bisa kehilangan attack lagi!");
                }
                break;

            case "increase_defense":
                if (attacker.defenseBuffCount < 2) {
                    attacker.defense += 2;
                    attacker.defenseBuffCount++;
                    DialogueManager.sayAuto(null, attacker.name + " meningkatkan defense!");
                } else {
                    DialogueManager.sayAuto(null, attacker.name + " tidak bisa meningkatkan defense lagi!");
                }
                break;

            case "decrease_defense":
                if (defender.defenseDebuffCount < 2) {
                    defender.defense = Math.max(1, defender.defense - 2);
                    defender.defenseDebuffCount++;
                    DialogueManager.sayAuto(null, defender.name + " kehilangan 2 defense!");
                } else {
                    DialogueManager.sayAuto(null, defender.name + " tidak bisa kehilangan defense lagi!");
                }
                break;

            default:
                int damage = calculateDamage(move, attacker, defender);
                defender.hp -= damage;
                DialogueManager.sayAuto(null, "Serangan menghasilkan " + damage + " damage!");
                break;
        }
    }

    private static int calculateDamage(Move move, Pokemon attacker, Pokemon defender) {
        int damage = move.power + attacker.attack - defender.defense;
        damage = Math.max(1, damage); // minimal damage = 1

        // Type effectiveness
        if (isSuperEffective(move.type, defender.type)) {
            DialogueManager.sayAuto(null, "It's super effective!");
            damage += random.nextInt(4) + 3; // +3 sampai +6
        } else if (isNotVeryEffective(move.type, defender.type)) {
            DialogueManager.sayAuto(null, "It's not very effective...");
            damage /= 2; // kurangi jadi setengah
            if (damage < 1) damage = 1;
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
