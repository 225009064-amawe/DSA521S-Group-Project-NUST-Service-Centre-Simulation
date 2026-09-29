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