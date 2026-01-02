import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BraceExpansionI {

    public static String[] expand(String s) {

        List<List<String>> groups = new ArrayList<>();
        int i = 0;

        // -------- STEP 1: PARSE STRING --------
        while (i < s.length()) {

            if (s.charAt(i) == '{') {
                i++; // skip '{'
                List<String> group = new ArrayList<>();

                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        group.add(String.valueOf(s.charAt(i)));
                    }
                    i++;
                }

                Collections.sort(group);
                groups.add(group);
                i++; // skip '}'

            } else {
                groups.add(Arrays.asList(String.valueOf(s.charAt(i))));
                i++;
            }
        }

        // -------- STEP 2: BFS --------
        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        for (List<String> group : groups) {
            int size = queue.size();

            while (size-- > 0) {
                String curr = queue.poll();
                for (String c : group) {
                    queue.offer(curr + c);
                }
            }
        }

        return queue.toArray(new String[0]);
    }

    // -------- MAIN METHOD --------
    public static void main(String[] args) {
        String input = "{a,b}c{d,e}";
        String[] result = expand(input);

        System.out.println("Brace Expansion I Output:");
        System.out.println(Arrays.toString(result));
    }
}
