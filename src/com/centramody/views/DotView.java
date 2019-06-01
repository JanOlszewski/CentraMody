package com.centramody.views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DotView {

    private Shape dot;

    public Shape getDot() {
        return dot;
    }

    public void setDot(Circle dot) {
        this.dot = dot;
    }

    public DotView() {

        dot = new Circle(8,8, 8, Color.BLACK);
    }

    public DotView(double x, double y, double r, Color color) {

        dot = new Circle(x, y, r, color);
    }
}
