package com.example.gifts_spring;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route("/hello")
public class MainView extends HorizontalLayout {
    public MainView() {
        var checkBox = new Checkbox("вы постоянный покупатель?");
        add (new VerticalLayout(checkBox));

        var labelFabric = new Text("Производитель");
        var comboBoxFabric = new ComboBox<String>("выберите производителя");
        //add(new VerticalLayout(/*labelFabric*/ comboBoxFabric));
        List<String> items = new ArrayList<>(List.of());

        factory factory_ = new factory();
        factory_.setName("неман");

        factory factory1 = new factory();
        factory1.setName("турист");

        List<factory> allFactories = new ArrayList<>();

        ArrayList<gift> list1 = new ArrayList<>();

        gift gift1 = new gift();
        gift1.cost = 15.0;
        gift1.name = "котлета новогодняя";

        gift gift2 = new gift();
        gift2.cost = 107.0;
        gift2.name = "пюре древнее";

        gift gift33 = new gift();
        gift33.cost = 38.0;
        gift33.name = "корнишон маринованный";

        list1.add(gift1);
        list1.add(gift2);
        list1.add(gift33);

        factory_.giftsAmount = 3;
        factory_.giftsList = list1;

        ArrayList<gift> list2 = new ArrayList<>();

        gift gift3 = new gift();
        gift3.cost = 53.0;
        gift3.name = "чешуйница из майнкрафта";

        gift gift4 = new gift();
        gift4.cost = 10.0;
        gift4.name = "мармелад красносельский";

        list2.add(gift3);
        list2.add(gift4);

        factory1.giftsAmount = 2;
        factory1.giftsList = list2;

        items.add(factory_.name);
        items.add(factory1.name);

        comboBoxFabric.setItems(items);

        allFactories.add(factory_);
        allFactories.add(factory1);

        //var labelProducts = new Text("подарки");
        var comboBoxProducts = new ComboBox<String>("выберите подарок");

        add (new VerticalLayout(/*labelProducts*/ comboBoxFabric, comboBoxProducts));
        List<String> itemsProduct = Arrays.asList(
                new String("Item 1"),
                new String("Item 2"),
                new String("Item 3")
        );
        comboBoxProducts.setItems(itemsProduct);
        comboBoxProducts.setAutofocus(true); //
        List<String> forComboboxNeman = new ArrayList<>();
        forComboboxNeman.add(gift1.name);
        forComboboxNeman.add(gift2.name);
        forComboboxNeman.add(gift33.name);
        List<String> forTourist = new ArrayList<>();
        forTourist.add(gift3.name);
        forTourist.add(gift4.name);
        comboBoxFabric.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            if (selectedValue == "турист") comboBoxProducts.setItems(forComboboxNeman);
            else comboBoxProducts.setItems(forTourist);
        });

        var labelCost = new Text ("Итоговая стоимость ");
        var cost = new Text ("0");
        double koef = 1;
        add (new HorizontalLayout(labelCost, cost));
        comboBoxProducts.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            for (factory allFactory : allFactories) {
                for (int j = 0; j < allFactory.giftsAmount; j++) {
                    if (selectedValue == allFactory.giftsList.get(j).name && checkBox.getValue()) labelCost.setText(Double.toString(allFactory.giftsList.get(j).cost * 0.9));
                    else if (selectedValue == allFactory.giftsList.get(j).name) labelCost.setText(Double.toString(allFactory.giftsList.get(j).cost));
                }
            }
        });
    }
}
