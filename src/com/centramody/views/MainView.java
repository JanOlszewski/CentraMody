package com.centramody.views;

import javafx.scene.layout.Pane;

public class MainView {

    private Pane mainPane;

    public Pane getMainPane() {
        return mainPane;
    }

    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }

    public MainView() {

        mainPane = new Pane();
        mainPane.setPrefSize(500, 500);
        mainPane.setStyle("-fx-background-color: darkgray");
    }
}
