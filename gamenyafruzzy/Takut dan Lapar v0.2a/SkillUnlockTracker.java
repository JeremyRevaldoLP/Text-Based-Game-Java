public class SkillUnlockTracker {
    public int choiceAfterGuard = -1;
    public int choiceAfterMaw = -1;

    public int getComboCode() {
        return (choiceAfterGuard * 10) + choiceAfterMaw;
    }
}