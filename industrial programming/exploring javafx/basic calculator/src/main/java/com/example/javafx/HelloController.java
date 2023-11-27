package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField display;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";

    @FXML
    private void handleDigit(ActionEvent event) {
        Button source = (Button) event.getSource();
        currentInput += source.getText();
        display.setText(currentInput);
    }

    @FXML
    private void handleOperator(ActionEvent event) {
        Button source = (Button) event.getSource();
        operator = source.getText();
        firstOperand = Double.parseDouble(currentInput);
        currentInput = "";
    }

    @FXML
    private void handleClear() {
        currentInput = "";
        firstOperand = 0;
        operator = "";
        display.clear();
    }

    @FXML
    private void handleEquals() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = "";
            operator = "";
        }
    }
}