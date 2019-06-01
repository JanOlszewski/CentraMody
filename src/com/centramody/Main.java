package com.centramody;

import com.centramody.controllers.MainViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private MainViewController mainViewController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainViewController = new MainViewController();

        primaryStage.setTitle("Centra Mody");
        primaryStage.setScene(mainViewController.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);
    }
}
