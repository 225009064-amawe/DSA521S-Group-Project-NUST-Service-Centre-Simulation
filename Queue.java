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