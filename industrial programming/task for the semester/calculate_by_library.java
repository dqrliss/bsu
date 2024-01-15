package com.example.sem_project_javafx;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class calculate_by_library extends calculate_abstract{
    @Override
    double calculate(String expression) {
        Expression exp = new ExpressionBuilder(expression).build();
        double result = exp.evaluate();

        return result;
    }
}