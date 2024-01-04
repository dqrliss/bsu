package com.example.presents_javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloController {

    @FXML
    private ComboBox<String> manufacturerComboBox;
    @FXML
    private ComboBox<String> giftComboBox;
    @FXML
    private Label costLabel;
    @FXML
    private CheckBox concertCheckBox;
    @FXML
    private CheckBox regularCustomerCheckBox;
    private double cost = 0;
    private double koef = 1;
    private int index = 0;
    @FXML
    private void customer() {
        boolean isConcert = regularCustomerCheckBox.isSelected();
        if (isConcert) {
            koef = 0.9;
            cost = cost * koef;
        }
        else {
            koef = 1;
            cost = cost * 1 / 0.9;
        }
        costLabel.setText(Double.toString(cost));
    }

    public void initialize() {
        List<String> factoryNames = new ArrayList<>();
        for (int i = 0; i < for_file.factories.size(); i++) factoryNames.add(for_file.factories.get(i).name);
        ObservableList<String> factoryNamesList = FXCollections.observableArrayList(factoryNames);
        manufacturerComboBox.setItems(factoryNamesList);
        manufacturerComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { updateGiftComboBox(newValue); });
        giftComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { updateLabelGift(newValue, oldValue, index); });
    }

    private void updateGiftComboBox(String selectedManufacturer) {
        List<String> giftsNames = new ArrayList<>();
        int indexNew = 0;
        for (int i = 0; i < for_file.count; i++) if (Objects.equals(for_file.factories.get(i).name, selectedManufacturer)) indexNew = i;
        index = indexNew;
        for (int i = 0; i < for_file.factories.get(indexNew).quantity; i++) giftsNames.add(for_file.factories.get(indexNew).gifts.get(i).name);
        ObservableList<String> updatedGiftOptions = FXCollections.observableArrayList(giftsNames);
        giftComboBox.setItems(updatedGiftOptions);
        cost = 0;
        costLabel.setText("0.0");
    }
    private void updateLabelGift(String selectedGift, String oldGift, int newIndex)
    {
        System.out.println(cost);
        System.out.println(selectedGift);
        System.out.println(oldGift);
        if (cost != 0.0 || selectedGift == null) for (int i = 0; i < for_file.count; i++) if (Objects.equals(for_file.factories.get(newIndex).gifts.get(i).name, oldGift)) cost -= for_file.factories.get(newIndex).gifts.get(i).cost * koef; for(int i = 0; i < for_file.count; i++) if (Objects.equals(for_file.factories.get(newIndex).gifts.get(i).name, selectedGift)) cost += for_file.factories.get(newIndex).gifts.get(i).cost * koef;
        costLabel.setText(Double.toString(cost));
    }
}