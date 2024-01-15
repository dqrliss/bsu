package com.example.sem_project_javafx;

import java.util.List;

public class Root {
    private String name;
    private List<Expressions> expressions;

    public void setName(String name) {
        this.name = name;
    }

    public void setExpressions(List<Expressions> expressions) {
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        return "Root{" +
                "name='" + name + '\'' +
                ", expressions=" + expressions +
                '}';
    }
}
