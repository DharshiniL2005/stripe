import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BraceExpansionII {

    public static List<String> braceExpansionII(String expression) {

        Set<String> result = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(expression);

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            int left = -1, right = 0;

            while (right < curr.length() && curr.charAt(right) != '}') {
                if (curr.charAt(right) == '{') {
                    left = right;
                }
                right++;
            }

            if (left == -1) {
                result.add(curr);
                continue;
            }

            String start = curr.substring(0, left);
            String end = curr.substring(right + 1);
            String[] options = curr.substring(left + 1, right).split(",");

            for (String option : options) {
                queue.offer(start + option + end);
            }
        }

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        String input = "{a,b}{c,{d,e}}";
        List<String> result = braceExpansionII(input);

        System.out.println("Brace Expansion II Output:");
        System.out.println(result);
    }
}
