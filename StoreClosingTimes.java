import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StoreClosingTimes {


    static int compute_penalty(String log, int closing_time) {
        int penalty = 0;

        for (int i = 0; i < closing_time; i++) {
            if (log.charAt(i) == 'N') penalty++;
        }

        for (int i = closing_time; i < log.length(); i++) {
            if (log.charAt(i) == 'Y') penalty++;
        }

        return penalty;
    }

    static int bestClosingTime(String log) {
        int penalty = 0;

        for (char c : log.toCharArray()) {
            if (c == 'Y') penalty++;
        }

        int minPenalty = penalty;
        int bestTime = 0;

        for (int i = 0; i < log.length(); i++) {
            if (log.charAt(i) == 'Y') penalty--;
            else penalty++;

            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestTime = i + 1;
            }
        }

        return bestTime;
    }

    static List<Integer> getAllClosing(String input) {
        List<Integer> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        String[] tokens = input.split(" ");

        for (String token : tokens) {

            if (token.equals("BEGIN")) {
                stack.push("BEGIN");
            }
            else if (token.equals("Y") || token.equals("N")) {
                stack.push(token);
            }
            else if (token.equals("END")) {

                StringBuilder log = new StringBuilder();

                while (!stack.isEmpty() && !stack.peek().equals("BEGIN")) {
                    log.append(stack.pop());
                }

                if (!stack.isEmpty()) stack.pop(); // remove BEGIN

                log.reverse();
                result.add(bestClosingTime(log.toString()));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String input =
            "BEGIN BEGIN Y Y N Y END Y Y N N END Y N Y N END";

        System.out.println(getAllClosing(input)); // [2, 2, 1]
    }
}