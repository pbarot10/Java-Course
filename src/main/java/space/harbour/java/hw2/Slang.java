import java.util.Scanner;

final class Slang {

    private Slang() {
        // Prevent instantiation
    }

    static String AbbrvFix(final String str) {
        String matchPattern = str.replaceAll("PLZ", "please");
        matchPattern = matchPattern.replaceAll("FYI",
                "for your information");
        matchPattern = matchPattern.replaceAll("GTFO",
                "please, leave me alone");
        matchPattern = matchPattern.replaceAll("ASAP",
                "as soon as possible");
        return matchPattern;
    }

    static String smileFixer(final String str) {
        String[] smilies = new String[]{":\\)", ":\\(",
                "¯\\_(ツ)_/¯"};
        String matchPattern = str.replaceAll(smilies[0], "[smiling]");
        matchPattern = matchPattern.replaceAll(smilies[1], "[sad]");
        matchPattern = matchPattern.replaceAll(smilies[2], "[such is life]");
        return matchPattern;
    }

    public static void main(String[] args) {
        System.out.print("Enter a string: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String result = AbbrvFix(input);
        result = smileFixer(result);

        System.out.println("Result: " + result);
    }
}
