class Solution {

    public static int compute_penalty(String log, int closing_time) {

        // Split the log by space
        String[] hours = log.split(" ");

        int penalty = 0;

        // Store is OPEN before closing_time
        for (int i = 0; i < closing_time; i++) {
            if (hours[i].equals("N")) {
                penalty++;
            }
        }

        // Store is CLOSED from closing_time onwards
        for (int i = closing_time; i < hours.length; i++) {
            if (hours[i].equals("Y")) {
                penalty++;
            }
        }

        return penalty;
    }

    // Test the function
    public static void main(String[] args) {
        String log = "Y Y N Y";
        int closing_time = 2;

        System.out.println(compute_penalty(log, closing_time)); // Output: 1
    }
}
