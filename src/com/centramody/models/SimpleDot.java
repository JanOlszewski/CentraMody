package com.centramody.models;

import com.centramody.views.DotView;
import javafx.geometry.Point2D;

public class SimpleDot extends Dot {

    private Long index;

    private boolean alive;

    private Long timeOfLive;

    private Long coefOfDie;

    private Long coefOfLive;

    public Long getCoefOfLive() {
        return coefOfLive;
    }

    public void setCoefOfLive(Long coefOfLive) {
        this.coefOfLive = coefOfLive;
    }

    public Long getCoefOfDie() {
        return coefOfDie;
    }

    public void setCoefOfDie(Long coefOfDie) {
        this.coefOfDie = coefOfDie;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Long getTimeOfLive() {
        return timeOfLive;
    }

    public void setTimeOfLive(long timeOfLive) {
        this.timeOfLive = timeOfLive;
    }

    public Long getIndex() {
        return index;
    }

    public SimpleDot(Long index, Long coefOfDie, Long coefOfLive) {

        super();
        alive = true;
        timeOfLive = 0L;
        this.index = index;
        this.coefOfDie = coefOfDie;
        this.coefOfLive = ((Long.MAX_VALUE/ coefOfDie)/coefOfLive);
        System.out.println(index + " is new dot");
    }

    public SimpleDot(Long index, DotView view, Point2D direction, double speed, Long coefOfDie, Long coefOfLive) {

        super(view, direction, speed);
        alive = true;
        timeOfLive = 0L;
        this.index = index;
        this.coefOfDie = coefOfDie;
        this.coefOfLive = (Long.MAX_VALUE/coefOfLive);
        System.out.println(index + " is new dot");
    }

    public void addPointTolive() {

        if((timeOfLive + coefOfLive) <= (Long.MAX_VALUE/ coefOfDie)) {

            timeOfLive += coefOfLive;
        }
        else if(timeOfLive < (Long.MAX_VALUE/coefOfDie)) {

            timeOfLive++;
        }
    }

    public void changeColor(FashionCenter fashionCenter1, FashionCenter fashionCenter2) {

        double fcx1 = fashionCenter1.getView().getTranslateX();
        double fcy1 = fashionCenter1.getView().getTranslateY();

        double fcx2 = fashionCenter2.getView().getTranslateX();
        double fcy2 = fashionCenter2.getView().getTranslateY();

        double dist1 = distance(fcx1, fcy1, getView().getTranslateX(), getView().getTranslateY());
        double dist2 = distance(fcx2, fcy2, getView().getTranslateX(), getView().getTranslateY());

        boolean b = false;

        if(dist2 > dist1) {

             b = tryChangeColor(fashionCenter1, dist1);
        }
        if(b == false) {
            tryChangeColor(fashionCenter2, dist2);
        }

        if(dist2 < dist1) {

            b = tryChangeColor(fashionCenter2, dist2);
        }
        if(b == false) {

            tryChangeColor(fashionCenter1, dist1);
        }
    }

    private int iterator = 0;
    private boolean tryChangeColor(FashionCenter fashionCenter, double distance) {

        int dist = (int) distance;

        if(iterator > 500) {
            iterator = 0;
            if (getRand().nextInt(501) > dist) {

                getView().setFill(fashionCenter.getView().getFill());
                return true;
            }
        }
        else { iterator++; }

        return false;
    }

    public double distance(double x1, double y1, double x2, double y2) {

        return Math.sqrt(Math.pow((x2-x1), 2.0) + Math.pow((y2-y1), 2.0));
    }

    private boolean isImmortal = false;
    public void tryDie() {

        Long tmp = (Long.MAX_VALUE-(Long.MAX_VALUE/ coefOfDie))+timeOfLive;

        if(tmp == Long.MAX_VALUE && !isImmortal) {

            System.out.println(this.index + " is immortal");
            isImmortal = true;
        }

        if(tmp < getRand().nextLong()) {

            System.out.println(this.index + " is dead");
            alive = false;
        }
    }

    public void update(FashionCenter fashionCenter1, FashionCenter fashionCenter2) {

        addPointTolive();
        move();
        changeColor(fashionCenter1, fashionCenter2);
        tryDie();
    }

    @Override
    public String toString() {
        return "SimpleDot{" +
                super.toString() + ", " +
                "index=" + index +
                ", alive=" + alive +
                ", timeOfLive=" + timeOfLive +
                ", coefOfDie=" + coefOfDie +
                '}';
    }
}
