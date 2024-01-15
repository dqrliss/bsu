package com.example.sem_project_javafx;

public abstract class calculate_abstract {
    abstract double calculate(String expression);

    protected static boolean hasPrecedence(char operator1, char operator2) {
        return (operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-');
    }

    protected static double performOperation(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("division by zero");
                }
                return operand1 / operand2;
        }
        throw new IllegalArgumentException("unknown operator " + operator);
    }
}
