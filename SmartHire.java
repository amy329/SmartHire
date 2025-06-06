import java.util.*;

public class SmartHire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter required skills (comma-separated): ");
        String skillsInput = scanner.nextLine();

        System.out.print("Enter minimum years of experience required: ");
        int minExperience = scanner.nextInt();
        scanner.nextLine();

        String jobDescText = "Looking for a " + jobTitle + " with experience in " + skillsInput +
                ". Minimum " + minExperience + " years of experience required.";
        JobDescription job = new JobDescription(jobTitle, jobDescText);


        System.out.print("How many candidates would you like to enter? ");
        int numCandidates = scanner.nextInt();
        scanner.nextLine();

        List<Candidate> candidateList = new ArrayList<>();


        for (int i = 0; i < numCandidates; i++) {
            System.out.println("\nEnter details for Candidate " + (i + 1));

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Resume Text: ");
            String resume = scanner.nextLine();

            Candidate candidate = Parser.parseCandidate(name, email, resume);
            candidateList.add(candidate);
        }


        List<Candidate> filteredCandidates = new ArrayList<>();
        for (Candidate c : candidateList) {
            if (SpamDetector.isSpam(c.resumeText)) {
                System.out.println("Skipping spam candidate: " + c.name);
                continue;
            }
            filteredCandidates.add(c);
        }


        Matcher matcher = new Matcher();
        List<Candidate> matchedCandidates = matcher.matchCandidates(filteredCandidates, job);


        System.out.println("\nMatched Candidates for Job: " + job.title);

        if (matchedCandidates.isEmpty()) {
            System.out.println("No candidates matched this job.");
        } else {
            for (Candidate c : matchedCandidates) {
                System.out.println("- " + c.name);
                System.out.println("  Email: " + c.email);
                System.out.println("  Experience: " + c.experienceYears + " years\n");
            }
        }


        scanner.close();
    }
}
