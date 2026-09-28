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
