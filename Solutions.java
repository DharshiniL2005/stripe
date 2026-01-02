import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solutions {

    
    public int bestClosingTime(String customers) {

        int p = 0;

        
        for (char c : customers.toCharArray()) {
            if (c == 'Y') p++;
        }

        int minPenalty = p;
        int bestTime = 0;

        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                p--;
            } else {
                p++;
            }

            if (p < minPenalty) {
                minPenalty = p;
                bestTime = i + 1;
            }
        }

        return bestTime;
    }

    public List<Integer> getAllClosing(String log) {

        List<Integer> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        String[] tokens = log.split(" ");
        StringBuilder currentStore = new StringBuilder();

        for (String token : tokens) {

            if (token.equals("BEGIN")) {
                stack.push("BEGIN");
                currentStore = new StringBuilder();
            }

            else if (token.equals("END")) {
                if (!stack.isEmpty()) {
                    stack.pop();

                    String storeLog = currentStore.toString();

                    int closingTime = bestClosingTime(storeLog);
                    result.add(closingTime);
                }
            }

            else {
               
                if (!stack.isEmpty()) {
                    currentStore.append(token);
                }
            }
        }

        return result;
    }

   
    public static void main(String[] args) {

        Solutions sol = new Solutions();

        String log =
            "BEGIN BEGIN BEGIN Y Y N Y END Y Y N N END Y N Y N END";

        List<Integer> closingTimes = sol.getAllClosing(log);

        System.out.println(closingTimes);
    }
}
