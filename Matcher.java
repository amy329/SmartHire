import java.util.ArrayList;
import java.util.List;

public class Matcher {


    public List<Candidate> matchCandidates(List<Candidate> candidates, JobDescription job) {
        List<Candidate> matched = new ArrayList<>();

        for (Candidate candidate : candidates) {
            int score = calculateMatchScore(candidate, job);

            if (score > 0) {
                matched.add(candidate);
            }
        }

        return matched;
    }

    private int calculateMatchScore(Candidate candidate, JobDescription job) {
        int score = 0;

        for (String requiredSkill : job.requiredSkills) {
            for (String candidateSkill : candidate.skills) {
                if (candidateSkill.equalsIgnoreCase(requiredSkill)) {
                    score += 10; // +10 points for every matching skill
                }
            }
        }

        if (candidate.experienceYears >= job.minExperience) {
            score += 20; // +20 points if candidate meets or exceeds experience
        }

        return score;
    }
}
