package com.example.presents_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try { for_file.read("presents.txt"); }
        catch (Exception e) { System.out.println("empty file"); }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 250);

        stage.setTitle("presents for new year");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}