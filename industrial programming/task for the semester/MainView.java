package com.example.spring_semtask;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
@Route("sem_task")
public class MainView extends VerticalLayout {
    private String input_file_name;
    private String output_file_name;
    private String file_content;
    //@Autowired
    public MainView () {

        TextArea text1 = new TextArea("input file name", "enter name of file");

        Button button1 = new Button("decrypt");
        Button button2 = new Button("dearchive");
        Button button3 = new Button("decrypt and dearchive");
        Button button4 = new Button("dearchive and decrypt");

        Button button11 = new Button("check for new input file name");
        TextArea text2 = new TextArea("updated input file name");

        TextArea text3 = new TextArea("output file name", "enter name of file");

        Button button5 = new Button("process the information");

        Button button6 = new Button("encrypt");
        Button button7 = new Button("archive");
        Button button8 = new Button("encrypt and archive");
        Button button9 = new Button("archive and encrypt");

        Button button12 = new Button("check for new output file name");
        TextArea text4 = new TextArea("updated output file name");

        Button button10 = new Button("write to output file");

        HorizontalLayout layout1 = new HorizontalLayout();
        layout1.add(text1);
        add(layout1);

        HorizontalLayout layout2 = new HorizontalLayout();
        layout2.add(button1, button2, button3, button4);
        add(layout2);

        HorizontalLayout layout8 = new HorizontalLayout();
        layout8.add(button11);
        add(layout8);

        HorizontalLayout layout7 = new HorizontalLayout();
        layout7.add(text2);
        add(layout7);

        HorizontalLayout layout4 = new HorizontalLayout();
        layout4.add(text3);
        add(layout4);

        HorizontalLayout layout3 = new HorizontalLayout();
        layout3.add(button5);
        add(layout3);

        HorizontalLayout layout5 = new HorizontalLayout();
        layout5.add(button6, button7, button8, button9);
        add(layout5);

        HorizontalLayout layout9 = new HorizontalLayout();
        layout9.add(button12);
        add(layout9);

        HorizontalLayout layout10 = new HorizontalLayout();
        layout10.add(text4);
        add(layout10);

        HorizontalLayout layout6 = new HorizontalLayout();
        layout6.add(button10);
        add(layout6);

        button1.addClickListener(event -> {
        });
        button2.addClickListener(event -> {
        });
        button3.addClickListener(event -> {
        });
        button4.addClickListener(event -> {
        });
        button5.addClickListener(event -> {
            input_file_name = text2.getValue();
            output_file_name = text3.getValue();
            process pr;
            try {
                pr = new process(input_file_name, output_file_name);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pr.replace();

            StringBuilder content = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new FileReader(output_file_name))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Содержимое файла " + content);
        });


        button6.addClickListener(event -> {
        });
        button7.addClickListener(event -> {
        });
        button8.addClickListener(event -> {
        });
        button9.addClickListener(event -> {
        });
        button10.addClickListener(event -> {
            output_file_name = text3.getValue();

        });
        button11.addClickListener(event -> {
            if (input_file_name == null) {
                input_file_name = text1.getValue();
            }
            text2.setValue(input_file_name);
        });
        button12.addClickListener(event -> {
            if (output_file_name == null) {
                output_file_name = text3.getValue();
            }
            text4.setValue(output_file_name);
        });
    }
}
