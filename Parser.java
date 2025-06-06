public class Parser {


    public static Candidate parseCandidate(String name, String email, String resumeText) {

        name = name.trim();
        email = email.trim();
        resumeText = resumeText.trim();

        return new Candidate(name, email, resumeText);
    }


}

