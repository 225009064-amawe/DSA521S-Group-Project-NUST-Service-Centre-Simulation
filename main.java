import java.util.Scanner;
import java.util.Random;

class Student {
    String studentNo, name, serviceType;
    int estimatedTime;
    Student next;

    Student(String studentNo, String name, String serviceType, int estimatedTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedTime = estimatedTime;
        this.next = null;
    }
}

class Queue {
    Student front, rear;

    void enqueue(Student s) {
        Student newNode = new Student(s.studentNo, s.name, s.serviceType, s.estimatedTime);
        if (front == null && rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    void dequeue() {
        if (front == null) {
            System.out.println("Queue is empty");
        } else {
            System.out.println("Served: " + front.name);
            front = front.next;
            if (front == null) rear = null;
        }
    }

    void peek() {
        if (front == null) System.out.println("Queue is empty");
        else System.out.println("Front of queue: " + front.name);
    }

    boolean isEmpty() {
        return front == null;
    }

    void displayQueue() {
        if (front == null) {
            System.out.println("Queue is empty");
        } else {
            Student temp = front;
            int pos = 1;
            while (temp != null) {
                System.out.println(pos + ". " + temp.studentNo + " | " + temp.name + " | "
                        + temp.serviceType + " | " + temp.estimatedTime + " min");
                temp = temp.next;
                pos++;
            }
        }
    }
}

class StudentLinkedList {
    Student head;

    void insertAtBeginning(String no, String name, String service, int time) {
        Student newNode = new Student(no, name, service, time);
        newNode.next = head;
        head = newNode;
    }

    void insertAtEnd(String no, String name, String service, int time) {
        Student newNode = new Student(no, name, service, time);
        if (head == null) {
            head = newNode;
        } else {
            Student temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    void insertAtPosition(String no, String name, String service, int time, int position) {
        if (position <= 1) {
            insertAtBeginning(no, name, service, time);
            return;
        }
        Student newNode = new Student(no, name, service, time);
        Student temp = head;
        int i = 1;
        while (i < position - 1 && temp != null) {
            temp = temp.next;
            i++;
        }
        if (temp == null) {
            System.out.println("Invalid position - inserted at end.");
            insertAtEnd(no, name, service, time);
        } else {
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    void deleteStudent(String studentNo) {
        if (head == null) {
            System.out.println("List is empty");
        } else if (head.studentNo.equals(studentNo)) {
            head = head.next;
            System.out.println("Deleted: " + studentNo);
        } else {
            Student temp = head;
            while (temp.next != null && !temp.next.studentNo.equals(studentNo)) {
                temp = temp.next;
            }
            if (temp.next == null) {
                System.out.println("Student not found");
            } else {
                temp.next = temp.next.next;
                System.out.println("Deleted: " + studentNo);
            }
        }
    }

    Student searchStudent(String studentNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.studentNo.equals(studentNo)) return temp;
            temp = temp.next;
        }
        return null;
    }

    void displayStudents() {
        if (head == null) {
            System.out.println("No records found.");
            return;
        }
        Student temp = head;
        int pos = 1;
        while (temp != null) {
            System.out.println(pos + ". " + temp.studentNo + " | " + temp.name + " | "
                    + temp.serviceType + " | " + temp.estimatedTime + " min");
            temp = temp.next;
            pos++;
        }
    }
}

class IntStack {
    int[] stack;
    int top;
    int size;

    IntStack(int size) {
        this.size = size;
        stack = new int[size];
        top = -1;
    }

    void push(int value) {
        if (top == size - 1) System.out.println("Stack full");
        else stack[++top] = value;
    }

    int pop() {
        if (top == -1) { System.out.println("Stack empty"); return 0; }
        return stack[top--];
    }

    int peek() {
        if (top == -1) { System.out.println("Stack empty"); return 0; }
        return stack[top];
    }

    boolean isEmpty() { return top == -1; }

    void display() {
        if (top == -1) {
            System.out.println("Stack empty");
            return;
        }
        System.out.print("Stack (top->bottom): ");
        for (int i = top; i >= 0; i--) System.out.print(stack[i] + " ");
        System.out.println();
    }
}

class PostfixEvaluator {
    static int evaluate(String expression) {
        IntStack stack = new IntStack(100);
        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = 0;
                if (token.equals("+")) result = operand1 + operand2;
                else if (token.equals("-")) result = operand1 - operand2;
                else if (token.equals("*")) result = operand1 * operand2;
                else if (token.equals("/")) result = operand1 / operand2;
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}

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

class SortingAlgorithms {

    static int selectionComparisons, selectionSwaps;
    static int insertionComparisons, insertionShifts;
    static int mergeComparisons;
    static int quickComparisons;

    static void selectionSort(int[] arr) {
        selectionComparisons = 0;
        selectionSwaps = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                selectionComparisons++;
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                selectionSwaps++;
            }
        }
    }

    static void insertionSort(int[] arr) {
        insertionComparisons = 0;
        insertionShifts = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                insertionComparisons++;
                arr[j + 1] = arr[j];
                insertionShifts++;
                j--;
            }
            if (j >= 0) insertionComparisons++;
            arr[j + 1] = key;
        }
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            mergeComparisons++;
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            quickComparisons++;
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

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