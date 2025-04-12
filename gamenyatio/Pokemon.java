import java.util.List;

public class Pokemon {
    public String name;
    public String type;
    public int hp;
    public int baseAttack;
    public int attack;
    public int defense;
    public List<Move> moves;

    public int attackBuffCount = 0;
    public int defenseBuffCount = 0;
    public int attackDebuffCount = 0;
    public int defenseDebuffCount = 0;

    public Pokemon(String name, String type, int hp, int attack, int defense, List<Move> moves) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.baseAttack = attack;
        this.attack = attack;
        this.defense = defense;
        this.moves = moves;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void resetBuffs() {
        this.attack = this.baseAttack;
        this.attackBuffCount = 0;
        this.defenseBuffCount = 0;
        this.attackDebuffCount = 0;
        this.defenseDebuffCount = 0;
    }
}
