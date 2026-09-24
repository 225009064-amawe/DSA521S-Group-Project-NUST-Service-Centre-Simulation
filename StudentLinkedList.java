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