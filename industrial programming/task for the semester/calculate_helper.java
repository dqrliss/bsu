package com.example.sem_project_javafx;

public class calculate_helper {
    calculate_abstract calc;

    public void setCalc(calculate_abstract calc) {
        this.calc = calc;
    }

    double calculate(String expression) {
        return calc.calculate(expression);
    }
}
