public class Candidate {
    String name;
    String email;
    String resumeText;
    String[] skills;
    int experienceYears;

    public Candidate(String name, String email, String resumeText) {
        this.name = name;
        this.email = email;
        this.resumeText = resumeText;
        this.skills = extractSkills(resumeText);
        this.experienceYears = extractExperience(resumeText);
    }

    private String[] extractSkills(String text) {
        String[] allSkills = {"Java", "Python", "AI", "Machine Learning", "Teamwork", "SQL"};
        String lowerText = text.toLowerCase();

        return java.util.Arrays.stream(allSkills)
                .filter(skill -> lowerText.contains(skill.toLowerCase()))
                .toArray(String[]::new);
    }

    private int extractExperience(String text) {
        for (int i = 50; i >= 0; i--) {
            if (text.contains(i + " year") || text.contains(i + " years")) {
                return i;
            }
        }
        return 0;
    }
}
