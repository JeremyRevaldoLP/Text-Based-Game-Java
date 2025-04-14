public class Move {
    public String name;
    public String type;
    public int power;
    public String effect; // bisa "none", "decrease_attack", "decrease_defense"
    public int accuracy;

    // Konstruktor default tanpa efek, akurasi 100%
    public Move(String name, String type, int power) {
        this(name, type, power, "none", 100);
    }

    // Konstruktor dengan efek, akurasi 100%
    public Move(String name, String type, int power, String effect) {
        this(name, type, power, effect, 100);
    }

    // Konstruktor lengkap
    public Move(String name, String type, int power, String effect, int accuracy) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.effect = effect;
        this.accuracy = accuracy;
    }
}
