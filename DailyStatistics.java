class DailyStatistics {
    static void displayStatistics(int[] serviceTimes, int count) {
        if (count == 0) {
            System.out.println("No students served yet.");
            return;
        }
        int totalTime = 0;
        int highest = serviceTimes[0];
        int lowest = serviceTimes[0];
        int longServices = 0;

        for (int i = 0; i < count; i++) {
            totalTime += serviceTimes[i];
            if (serviceTimes[i] > highest) highest = serviceTimes[i];
            if (serviceTimes[i] < lowest) lowest = serviceTimes[i];
            if (serviceTimes[i] > 10) longServices++;
        }

        double average = (double) totalTime / count;

        System.out.println("Total students served: " + count);
        System.out.println("Total service time: " + totalTime + " min");
        System.out.println("Average service time: " + String.format("%.2f", average) + " min");
        System.out.println("Highest service time: " + highest + " min");
        System.out.println("Lowest service time: " + lowest + " min");
        System.out.println("Services longer than 10 minutes: " + longServices);
    }
}