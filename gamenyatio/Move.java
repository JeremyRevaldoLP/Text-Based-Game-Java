public class Move {
    public String name;
    public String type;
    public int power;
    public String effect; // bisa "none", "decrease_attack", "decrease_defense"

    public Move(String name, String type, int power) {
        this(name, type, power, "none");
    }

    public Move(String name, String type, int power, String effect) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.effect = effect;
    }
}
