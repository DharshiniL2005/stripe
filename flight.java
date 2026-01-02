import java.util.*;

public class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dp, n);

            for (int[] flight : flights) {
                if (dp[flight[0]] != Integer.MAX_VALUE) {
                    temp[flight[1]] = Math.min(
                        temp[flight[1]],
                        dp[flight[0]] + flight[2]
                    );
                }
            }
            dp = temp;
        }

        return dp[dst] == Integer.MAX_VALUE ? -1 : dp[dst];
    }

    // 🔥 MAIN METHOD FOR VS CODE
    public static void main(String[] args) {

        Solution sol = new Solution();

        int n = 4;
        int[][] flights = {
            {0, 1, 100},
            {1, 2, 100},
            {2, 3, 200},
            {1, 3, 600}
        };

        int src = 0;
        int dst = 3;
        int k = 1;

        int result = sol.findCheapestPrice(n, flights, src, dst, k);

        System.out.println("Cheapest Price = " + result);
    }
}
