class AlgorithmExperiment {

    static int[] generateArray(int size, long seed) {
        Random rand = new Random(seed);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000);
        return arr;
    }

    static int[] generateAlmostSorted(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i;
        int temp = arr[10];
        arr[10] = arr[11];
        arr[11] = temp;
        return arr;
    }

    static void runExperiment() {
        int[] sizes = {20, 50, 100, 500};
        System.out.println("\nALGORITHM EXPERIMENT - RANDOM ARRAYS");
        System.out.printf("%-15s %-8s %-15s %-15s%n", "Algorithm", "Size", "Comparisons", "Time (ns)");
        System.out.println("-----------------------------------------------------------");

        for (int size : sizes) {
            int[] original = generateArray(size, 42L);

            int[] c1 = original.clone();
            SortingAlgorithms.selectionComparisons = 0;
            long start = System.nanoTime();
            SortingAlgorithms.selectionSort(c1);
            long end = System.nanoTime();
            System.out.printf("%-15s %-8d %-15d %-15d%n", "Selection", size,
                    SortingAlgorithms.selectionComparisons, (end - start));

            int[] c2 = original.clone();
            SortingAlgorithms.insertionComparisons = 0;
            start = System.nanoTime();
            SortingAlgorithms.insertionSort(c2);
            end = System.nanoTime();
            System.out.printf("%-15s %-8d %-15d %-15d%n", "Insertion", size,
                    SortingAlgorithms.insertionComparisons, (end - start));

            int[] c3 = original.clone();
            SortingAlgorithms.mergeComparisons = 0;
            start = System.nanoTime();
            SortingAlgorithms.mergeSort(c3, 0, c3.length - 1);
            end = System.nanoTime();
            System.out.printf("%-15s %-8d %-15d %-15d%n", "Merge", size,
                    SortingAlgorithms.mergeComparisons, (end - start));

            int[] c4 = original.clone();
            SortingAlgorithms.quickComparisons = 0;
            start = System.nanoTime();
            SortingAlgorithms.quickSort(c4, 0, c4.length - 1);
            end = System.nanoTime();
            System.out.printf("%-15s %-8d %-15d %-15d%n", "Quick", size,
                    SortingAlgorithms.quickComparisons, (end - start));

            System.out.println("-----------------------------------------------------------");
        }

        System.out.println("\nALMOST-SORTED TEST (size 100)");
        System.out.printf("%-15s %-15s %-15s%n", "Algorithm", "Comparisons", "Time (ns)");
        System.out.println("-------------------------------------------------");

        int[] almost = generateAlmostSorted(100);

        int[] a1 = almost.clone();
        SortingAlgorithms.selectionComparisons = 0;
        long start = System.nanoTime();
        SortingAlgorithms.selectionSort(a1);
        long end = System.nanoTime();
        System.out.printf("%-15s %-15d %-15d%n", "Selection",
                SortingAlgorithms.selectionComparisons, (end - start));

        int[] a2 = almost.clone();
        SortingAlgorithms.insertionComparisons = 0;
        start = System.nanoTime();
        SortingAlgorithms.insertionSort(a2);
        end = System.nanoTime();
        System.out.printf("%-15s %-15d %-15d%n", "Insertion",
                SortingAlgorithms.insertionComparisons, (end - start));

        int[] a3 = almost.clone();
        SortingAlgorithms.mergeComparisons = 0;
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(a3, 0, a3.length - 1);
        end = System.nanoTime();
        System.out.printf("%-15s %-15d %-15d%n", "Merge",
                SortingAlgorithms.mergeComparisons, (end - start));

        int[] a4 = almost.clone();
        SortingAlgorithms.quickComparisons = 0;
        start = System.nanoTime();
        SortingAlgorithms.quickSort(a4, 0, a4.length - 1);
        end = System.nanoTime();
        System.out.printf("%-15s %-15d %-15d%n", "Quick",
                SortingAlgorithms.quickComparisons, (end - start));
    }
}

public class Main {
    static Queue queue = new Queue();
    static StudentLinkedList records = new StudentLinkedList();
    static int[] serviceTimes = new int[500];
    static int servedCount = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // load sample students
        queue.enqueue(new Student("221045678", "Maria", "Registration", 12));
        queue.enqueue(new Student("222034512", "Tomas", "Student Card", 5));
        queue.enqueue(new Student("223041876", "Ndapewa", "Fees", 8));
        queue.enqueue(new Student("221067341", "Simon", "Documents", 4));
        queue.enqueue(new Student("224001234", "Anna", "Registration", 10));
        queue.enqueue(new Student("225005678", "Peter", "Fees", 6));

        records.insertAtEnd("221045678", "Maria", "Registration", 12);
        records.insertAtEnd("222034512", "Tomas", "Student Card", 5);
        records.insertAtEnd("223041876", "Ndapewa", "Fees", 8);
        records.insertAtPosition("221067341", "Simon", "Documents", 4, 2);
        records.insertAtBeginning("224001234", "Anna", "Registration", 10);

        // postfix demo
        System.out.println("Postfix Evaluation of '5 3 + 2 *' = "
                + PostfixEvaluator.evaluate("5 3 + 2 *"));

        // sorting demos on the sample array
        int[] demoArray = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        int[] a = demoArray.clone();
        SortingAlgorithms.selectionSort(a);
        System.out.print("Selection Sort: ");
        SortingAlgorithms.printArray(a);
        System.out.println("Comparisons=" + SortingAlgorithms.selectionComparisons
                + " Swaps=" + SortingAlgorithms.selectionSwaps);

        int[] b = demoArray.clone();
        SortingAlgorithms.insertionSort(b);
        System.out.print("Insertion Sort: ");
        SortingAlgorithms.printArray(b);
        System.out.println("Comparisons=" + SortingAlgorithms.insertionComparisons
                + " Shifts=" + SortingAlgorithms.insertionShifts);

        int[] c = demoArray.clone();
        SortingAlgorithms.mergeComparisons = 0;
        SortingAlgorithms.mergeSort(c, 0, c.length - 1);
        System.out.print("Merge Sort: ");
        SortingAlgorithms.printArray(c);
        System.out.println("Comparisons=" + SortingAlgorithms.mergeComparisons);

        int[] d = demoArray.clone();
        SortingAlgorithms.quickComparisons = 0;
        SortingAlgorithms.quickSort(d, 0, d.length - 1);
        System.out.print("Quick Sort: ");
        SortingAlgorithms.printArray(d);
        System.out.println("Comparisons=" + SortingAlgorithms.quickComparisons);

        // menu
        while (true) {
            System.out.println("\nCAMPUS SERVICE CENTRE");
            System.out.println("1. Add student to waiting queue");
            System.out.println("2. Serve next student");
            System.out.println("3. Display waiting students");
            System.out.println("4. Add student service record");
            System.out.println("5. Display student service records");
            System.out.println("6. Search for student record");
            System.out.println("7. Remove student record");
            System.out.println("8. Display daily statistics");
            System.out.println("9. Sort service times");
            System.out.println("10. Run sorting experiment");
            System.out.println("11. Peek at front of queue");
            System.out.println("12. Check if queue is empty");
            System.out.println("13. Exit");
            System.out.print("Select option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }

            if (choice == 1) {
                System.out.print("Student No: ");
                String no = scanner.nextLine();
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Service Type: ");
                String service = scanner.nextLine();
                System.out.print("Estimated Time (min): ");
                int time = Integer.parseInt(scanner.nextLine());
                queue.enqueue(new Student(no, name, service, time));
                System.out.println("Added to queue.");
            } else if (choice == 2) {
                if (!queue.isEmpty()) {
                    Student served = queue.front;
                    System.out.println("Serving: " + served.name);
                    if (servedCount < serviceTimes.length)
                        serviceTimes[servedCount++] = served.estimatedTime;
                    queue.dequeue();
                } else {
                    System.out.println("Queue is empty.");
                }
            } else if (choice == 3) {
                queue.displayQueue();
            } else if (choice == 4) {
                System.out.print("Student No: ");
                String no = scanner.nextLine();
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Service Type: ");
                String service = scanner.nextLine();
                System.out.print("Estimated Time (min): ");
                int time = Integer.parseInt(scanner.nextLine());
                records.insertAtEnd(no, name, service, time);
                System.out.println("Record added.");
            } else if (choice == 5) {
                records.displayStudents();
            } else if (choice == 6) {
                System.out.print("Student No to search: ");
                String no = scanner.nextLine();
                Student found = records.searchStudent(no);
                if (found != null)
                    System.out.println("Found: " + found.name + " | " + found.serviceType);
                else
                    System.out.println("Not found.");
            } else if (choice == 7) {
                System.out.print("Student No to remove: ");
                records.deleteStudent(scanner.nextLine());
            } else if (choice == 8) {
                DailyStatistics.displayStatistics(serviceTimes, servedCount);
            } else if (choice == 9) {
                if (servedCount == 0) {
                    System.out.println("No service times to sort.");
                } else {
                    int[] copy = new int[servedCount];
                    System.arraycopy(serviceTimes, 0, copy, 0, servedCount);
                    SortingAlgorithms.mergeSort(copy, 0, copy.length - 1);
                    System.out.print("Sorted service times: ");
                    SortingAlgorithms.printArray(copy);
                }
            } else if (choice == 10) {
                AlgorithmExperiment.runExperiment();
            } else if (choice == 11) {
                queue.peek();
            } else if (choice == 12) {
                if (queue.isEmpty())
                    System.out.println("Queue is empty.");
                else
                    System.out.println("Queue is NOT empty.");
            } else if (choice == 13) {
                System.out.println("Goodbye.");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}