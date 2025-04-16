public class Boss extends Enemy {
    private String specialSkill;

    public Boss(String name, int health, int defense, int magicDefense, int physicalAttack, int magicAttack, int evasion, String specialSkill) {
        super(name, health, defense, magicDefense, physicalAttack, magicAttack, evasion);
        this.specialSkill = specialSkill;
    }

    public String getSpecialSkill() {
        return specialSkill;
    }

    @Override
    public String toString() {
        return name + " (Boss) - HP: " + health + " - Skill: " + specialSkill;
    }

    // Fungsi untuk serangan boss dengan skill khusus
    public void useSpecialSkill(Character player) {
        // Contoh, bisa sesuaikan dengan efek skill di game
        TextUtils.typeWriter(name + " menggunakan skill: " + specialSkill);
        // Misalnya damage skill
        int skillDamage = (int)(magicAttack * 1.5); // contoh 150% dari magic attack
        player.takeDamage(skillDamage);
    }
}
