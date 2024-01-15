package com.example.sem_project_javafx;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    private final IOFileInfo ioFileInfo = new IOFileInfo();

    @FXML
    private ToggleGroup calcMethod;

    @FXML
    private ToggleGroup inputArchiveType;

    @FXML
    private ToggleGroup inputDataType;

    @FXML
    private ToggleGroup inputEncryptionMethod;

    @FXML
    private TextField inputFileNameID;

    @FXML
    private Label message;

    @FXML
    private ToggleGroup outputArchiveType;

    @FXML
    private ToggleGroup outputDataType;

    @FXML
    private ToggleGroup outputEncryptionMethod;

    @FXML
    private TextField outputFileNameID;

    @FXML
    private Button processButton;
    private ProgramManager programManager = new ProgramManager();

    public Controller() throws NoSuchAlgorithmException {
    }

    @FXML
    void initialize() {
        processButton.setOnAction(event -> {
            try {
                message.setOpacity(0);

                programManager = new ProgramManager();

                RadioButton selectedInputType = (RadioButton) inputDataType.getSelectedToggle();
                if(selectedInputType != null) {
                    ioFileInfo.inputFileData = selectedInputType.getText();
                }

                selectedInputType = (RadioButton) outputDataType.getSelectedToggle();
                if(selectedInputType != null) {
                    ioFileInfo.outputFileData = selectedInputType.getText();
                }

                selectedInputType = (RadioButton) calcMethod.getSelectedToggle();
                if(selectedInputType != null) {
                    if (selectedInputType.getText().equals("without using regexps")) {
                        ioFileInfo.numberOfCalculationMethod = "1";
                    }
                    else if (selectedInputType.getText().equals("using regexps")) {
                        ioFileInfo.numberOfCalculationMethod = "2";
                    }
                    else if (selectedInputType.getText().equals("using another library")) {
                        ioFileInfo.numberOfCalculationMethod = "3";
                    }
                }

                selectedInputType = (RadioButton) inputEncryptionMethod.getSelectedToggle();
                if(selectedInputType != null) {
                    ioFileInfo.inputEncryptionMethod = String.valueOf(selectedInputType.getText().charAt(0));
                }

                selectedInputType = (RadioButton) outputEncryptionMethod.getSelectedToggle();
                if(selectedInputType != null) {
                    ioFileInfo.outputEncryptionMethod = String.valueOf(selectedInputType.getText().charAt(0));
                }

                selectedInputType = (RadioButton) inputArchiveType.getSelectedToggle();
                if (selectedInputType != null) {
                    ioFileInfo.inArchiveData = selectedInputType.getText();
                }

                selectedInputType = (RadioButton) outputArchiveType.getSelectedToggle();
                if (selectedInputType != null) {
                    ioFileInfo.outArchiveData = selectedInputType.getText();
                }

                ioFileInfo.inputFileName = inputFileNameID.getText();

                ioFileInfo.outputFileName = outputFileNameID.getText();

                programManager.manageInput(ioFileInfo);
                programManager.manageOutput(ioFileInfo);

            } catch (NoSuchAlgorithmException | IOException | InvalidAlgorithmParameterException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        });
    }

}