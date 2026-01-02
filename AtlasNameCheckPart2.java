import java.util.HashSet;
import java.util.Set;

public class AtlasNameCheckPart2 {

    // Permanent registry of normalized names
    private static Set<String> registeredNames = new HashSet<>();

    public static void main(String[] args) {

        String[] requests = {
                "1 | Llama, Inc.",
                "2 | The Llama LLC",
                "1 | Llama Inc",
                "3 | Llama Corp"
        };

        for (String req : requests) {
            processRequest(req);
        }
    }

    private static void processRequest(String request) {

        String[] parts = request.split("\\|");
        String accountId = parts[0].trim();
        String proposedName = parts[1].trim();

        String normalized = normalize(proposedName);

        if (normalized.isEmpty()) {
            System.out.println(accountId + " | Name Not Available");
            return;
        }

        if (registeredNames.contains(normalized)) {
            System.out.println(accountId + " | Name Not Available");
        } else {
            registeredNames.add(normalized);   // Permanent registration
            System.out.println(accountId + " | Name Available");
        }
    }

    private static String normalize(String name) {

        name = name.toLowerCase();
        name = name.replace("&", " ").replace(",", " ");

        String[] suffixes = {"inc.", "corp.", "llc", "l.l.c.", "llc."};
        for (String s : suffixes) {
            name = name.replaceAll("\\b" + s + "\\b", "");
        }

        name = name.replaceAll("^(the|an|a)\\s+", "");
        name = name.replaceAll("\\s+and\\s+", " ");
        name = name.replaceAll("\\s+", " ").trim();

        return name;
    }
}
