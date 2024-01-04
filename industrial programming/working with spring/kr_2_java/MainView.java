package com.example.kr_2;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Route("kr")
public class MainView extends VerticalLayout {

    private final calculate_latin calc1;
    private final calculate_union calc2;

    @Autowired
    public MainView (calculate_latin calc1, calculate_union calc2) {
        this.calc1 = calc1;
        this.calc2 = calc2;

        TextArea text1 = new TextArea("first text");
        TextArea text2 = new TextArea("second text");

        Button button1 = new Button("calculate latin");
        Button button2 = new Button("calculate latin");

        TextArea text3 = new TextArea("first latin");
        TextArea text4 = new TextArea("second latin");

        Button button3 = new Button("calculate union");
        TextArea text5 = new TextArea("union");

        VerticalLayout layout1 = new VerticalLayout();
        layout1.add(text1, button1, text3);

        VerticalLayout layout2 = new VerticalLayout();
        layout2.add();

        VerticalLayout layout3 = new VerticalLayout();
        layout3.add(text2, button2, text4);

        HorizontalLayout layout4 = new HorizontalLayout();
        layout4.add(layout1, layout2, layout3);

        VerticalLayout layout5 = new VerticalLayout();
        layout5.add();

        VerticalLayout layout6 = new VerticalLayout();
        layout6.add(button3, text5);

        VerticalLayout layout7 = new VerticalLayout();
        layout7.add();

        HorizontalLayout layout8 = new HorizontalLayout();
        layout8.add(layout5, layout6, layout7);

        VerticalLayout layout9 = new VerticalLayout();
        layout9.add(layout4, layout8);

        add(layout9);

        button1.addClickListener(event -> {
            String str = text1.getValue();
            text3.setValue(String.valueOf(calc1.latin(str)));
        });

        button2.addClickListener(event -> {
            String str = text2.getValue();
            text4.setValue(String.valueOf(calc1.latin(str)));
        });

        button3.addClickListener(event -> {
            String str1 = text1.getValue();
            String str2 = text2.getValue();
            text5.setValue(String.valueOf(calc2.uni(str1, str2)));
        });
    }
}