import java.util.ArrayList;
import java.util.List;

class Solution {

    public static List<String> expandString(String input) {
        List<String> result = new ArrayList<>();

        int open = input.indexOf('{');
        int close = input.indexOf('}');

        if (open == -1 || close == -1 || close < open) {
            result.add(input);
            return result;
        }

        String middle = input.substring(open + 1, close);

        if (middle.isEmpty()) {
            result.add(input);
            return result;
        }

        String[] tokens = middle.split(",", -1);

        if (tokens.length < 2) {
            result.add(input);
            return result;
        }

        String prefix = input.substring(0, open);
        String suffix = input.substring(close + 1);

        for (String token : tokens) {
            result.add(prefix + token + suffix);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(expandString("sun{mars}rotation"));
        System.out.println(expandString("minimum{}change"));
        System.out.println(expandString("hello-world"));
        System.out.println(expandString("hello-{-world"));
        System.out.println(expandString("hello-}-weird-{-world"));
        System.out.println(expandString("/2022/{jan,feb,march}/report"));
    }
}