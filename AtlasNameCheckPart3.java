import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AtlasNameCheckPart3 {

    // Stores all registered normalized names
    private static Set<String> registeredNames = new HashSet<>();

    // normalizedName -> accountId
    private static Map<String, String> ownerMap = new HashMap<>();

    // normalizedName -> original proposed name
    private static Map<String, String> originalNameMap = new HashMap<>();

    public static void main(String[] args) {

        String[] inputs = {
                "1 | Llama, Inc.",
                "2 | The Llama LLC",
                "3 | And Llama LLC",
                "RECLAIM,1,Llama, Inc.",
                "2 | The Llama LLC"
        };

        for (String line : inputs) {
            process(line);
        }
    }

    private static void process(String line) {

        // -------- Reclaim request --------
        if (line.startsWith("RECLAIM")) {
            handleReclaim(line);
            return;
        }

        // -------- Normal registration --------
        String[] parts = line.split("\\|");
        String accountId = parts[0].trim();
        String proposedName = parts[1].trim();

        String normalized = normalize(proposedName);

        if (normalized.isEmpty() || registeredNames.contains(normalized)) {
            System.out.println(accountId + " | Name Not Available");
            return;
        }

        // Register name
        registeredNames.add(normalized);
        ownerMap.put(normalized, accountId);
        originalNameMap.put(normalized, proposedName);

        System.out.println(accountId + " | Name Available");
    }

    private static void handleReclaim(String line) {

        // Format: RECLAIM,accountId,originalName
        String[] parts = line.split(",", 3);
        String accountId = parts[1].trim();
        String originalName = parts[2].trim();

        String normalized = normalize(originalName);

        // Validate reclaim
        if (!registeredNames.contains(normalized)) return;
        if (!accountId.equals(ownerMap.get(normalized))) return;
        if (!originalName.equals(originalNameMap.get(normalized))) return;

        // Remove registration
        registeredNames.remove(normalized);
        ownerMap.remove(normalized);
        originalNameMap.remove(normalized);
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
