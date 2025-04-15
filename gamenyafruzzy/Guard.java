public class Guard extends Character {

    public Guard(String name) {
        super(name);
        this.className = "Guard";
        this.health = 100;
        initializeSkills();
    }

    @Override
    public void initializeSkills() {
        skills.add(new Skill("Shield Bash", "Membuat musuh pusing selama 1 turn", 0));
        skills.add(new Skill("Defend", "Mengurangi damage musuh 50% di turn ini", 1));
        // Tambahkan 6 skill lainnya nanti
    }
}
