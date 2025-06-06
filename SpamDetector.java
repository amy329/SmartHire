import java.util.List;

public class SpamDetector {


    private static final List<String> spamKeywords = List.of(
            "fake", "test", "spam", "dummy", "lorem ipsum", "no experience"
    );

    public static boolean isSpam(String resumeText) {
        String text = resumeText.toLowerCase();

        for (String keyword : spamKeywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }

        if (text.length() < 50) {
            return true;
        }

        return false;
    }
}

