package com.centramody.models;

import com.centramody.views.DotView;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

import java.util.Random;


public class Dot {

    private DotView view;

    private Point2D direction;

    private double speed;

    private Random rand;

    public Random getRand() {
        return rand;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Shape getView() {
        return view.getDot();
    }

    public void setView(DotView view) {
        this.view = view;
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }


    public Dot() {

        view = new DotView();
        direction = new Point2D(20.0, 20.0);
        speed = 2;
        rand = new Random();
    }

    public Dot(DotView view, Point2D direction, double speed) {

        this.view = view;
        this.direction = direction;
        this.speed = speed;
        rand = new Random();
    }

    public void move() {

        if(rand.nextInt(11) < 5) {
            getView().setRotate(getView().getRotate() + rand.nextInt(51) - 25);
        }
        setDirection(new Point2D(Math.cos(Math.toRadians(getView().getRotate())),
                Math.sin(Math.toRadians(getView().getRotate()))));

        double x = (getView().getTranslateX() + getDirection().getX());
        double y = (getView().getTranslateY() + getDirection().getY());

        if(x < 495 && x > 0) {
            getView().setTranslateX(getView().getTranslateX() + (getDirection().getX() / speed));
        }
        if(y < 495 && y > 0) {
            getView().setTranslateY(getView().getTranslateY() + (getDirection().getY() / speed));
        }
    }

    @Override
    public String toString() {
        return "Dot{" +
                "view=" + view +
                ", direction=" + direction +
                ", speed=" + speed +
                ", rand=" + rand +
                '}';
    }
}
