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