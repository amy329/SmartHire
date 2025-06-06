import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class JobDescription {
    String title;
    String descriptionText;
    String[] requiredSkills;
    int minExperience;


    private static final String[] KNOWN_SKILLS = {"Java", "Python", "AI", "Machine Learning", "Teamwork", "SQL", "C++", "JavaScript"};

    public JobDescription(String title, String descriptionText) {
        this.title = title;
        this.descriptionText = descriptionText;
        this.requiredSkills = extractSkills(descriptionText);
        this.minExperience = extractExperience(descriptionText);


        System.out.println("[DEBUG] Extracted job skills: " + Arrays.toString(requiredSkills));
        System.out.println("[DEBUG] Extracted min experience: " + minExperience);
    }


    private String[] extractSkills(String text) {
        List<String> foundSkills = new ArrayList<>();
        String lowerText = text.toLowerCase();

        for (String skill : KNOWN_SKILLS) {
            if (lowerText.contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        return foundSkills.toArray(new String[0]);
    }


    private int extractExperience(String text) {
        for (int i = 50; i >= 0; i--) {
            if (text.contains(i + " year") || text.contains(i + " years")) {
                return i;
            }
        }
        return 0;
    }

    public boolean matches(Candidate candidate) {
        int matchedSkills = 0;

        for (String requiredSkill : requiredSkills) {
            for (String candidateSkill : candidate.skills) {
                if (requiredSkill.equalsIgnoreCase(candidateSkill)) {
                    matchedSkills++;
                    break;
                }
            }
        }

        return matchedSkills >= requiredSkills.length / 2 && candidate.experienceYears >= minExperience;
    }
}