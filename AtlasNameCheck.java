import java.util.HashSet;
import java.util.Set;

public class AtlasNameCheck {

    // Set to store already registered normalized names
    private static Set<String> registeredNames = new HashSet<>();

    public static void main(String[] args) {

        String[] requests = {
                "1 | Llama, Inc.",
                "2 | The Llama LLC",
                "3 | And Llama LLC",
                "4 | Llama And Friends, Inc."
        };

        for (String req : requests) {
            processRequest(req);
        }
    }

    // Process each name request
    private static void processRequest(String request) {

        String[] parts = request.split("\\|");
        String accountId = parts[0].trim();
        String proposedName = parts[1].trim();

        String normalized = normalize(proposedName);

        if (normalized.isEmpty() || registeredNames.contains(normalized)) {
            System.out.println(accountId + " | Name Not Available");
        } else {
            registeredNames.add(normalized);
            System.out.println(accountId + " | Name Available");
        }
    }

    // Normalize the company name according to rules
    private static String normalize(String name) {

        // 1. Lowercase
        name = name.toLowerCase();

        // 2. Replace '&' and ',' with space
        name = name.replace("&", " ").replace(",", " ");

        // 3. Remove company suffixes
        String[] suffixes = {"inc.", "corp.", "llc", "l.l.c.", "llc."};
        for (String s : suffixes) {
            name = name.replaceAll("\\b" + s + "\\b", "");
        }

        // 4. Remove leading articles
        name = name.replaceAll("^(the|an|a)\\s+", "");

        // 5. Remove "and" if not at the start
        name = name.replaceAll("\\s+and\\s+", " ");

        // 6. Collapse multiple spaces
        name = name.replaceAll("\\s+", " ").trim();

        return name;
    }
}
