package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//без файла корнструктором создать список производителей и подарков
//репозиторий список элементов в памяти и отобразить из через комбо боксы
//бд потом с сервера либо веб интерфейс подсоединить джейсоновский файл
//в конструкторе написать или в сеттере производителей