import java.util.List;

public class Pokemon {
    public String name;
    public String type;
    public int maxHp;
    public int hp;
    public int attack;
    public int defense;
    public List<Move> moves;
    private Inventory inventory = new Inventory(); // default kosong
    private int currentHp;



    public int attackBuffCount = 0;
    public int attackDebuffCount = 0;
    public int defenseBuffCount = 0;
    public int defenseDebuffCount = 0;

    public Pokemon(String name, String type, int hp, int attack, int defense, List<Move> moves) {
        this.name = name;
        this.type = type;
        this.maxHp = hp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.moves = moves;
        this.inventory = new Inventory();
    }

    // Getter dan setter lainnya...

    public Inventory getInventory() {
        return inventory;
    }

    // Method untuk menggunakan item
    public void useItem(Item item) {
        if (item.getHealAmount() > 0) {
            this.hp += item.getHealAmount();
            System.out.println(this.name + " sembuh " + item.getHealAmount() + " HP!");
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void resetBuffs() {
        attackBuffCount = 0;
        attackDebuffCount = 0;
        defenseBuffCount = 0;
        defenseDebuffCount = 0;
    }

    public void heal(int amount) {
        this.currentHp += amount;
        if (this.currentHp > this.maxHp) {
            this.currentHp = this.maxHp;
        }
        System.out.println(name + " healed " + amount + " HP!");
    }
    
    
}
