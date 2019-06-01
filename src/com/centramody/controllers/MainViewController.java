package com.centramody.controllers;

import com.centramody.models.Dot;
import com.centramody.models.FashionCenter;
import com.centramody.models.SimpleDot;
import com.centramody.views.DotView;
import com.centramody.views.MainView;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainViewController {

    private MainView mainView;

    private Scene scene;

    private Long index;
    private HashMap<Long, SimpleDot> mapOfDots;

    private FashionCenter fashionCenter1;
    private FashionCenter fashionCenter2;

    private Random random;

    /**
     *
     * random.nextLong() > (Long.MAX_VALUE-(Long.MAX_VALUE/ coefOfRespown))
     * Generated value must be greater than the specified condition to create a new ball.
     *
     * */
    private Long coefOfRespown = 40L;

    /**
     *
     * this.coefOfLive = ((Long.MAX_VALUE/ coefOfDie)/coefOfLive); <- in SimpleDot constructor.
     *
     * in addPointToLive() method
     * if((timeOfLive + coefOfLive) <= (Long.MAX_VALUE/ coefOfDie)) { timeOfLive += coefOfLive; }
     *
     * else if(timeOfLive < (Long.MAX_VALUE/coefOfDie)) { timeOfLive++; }
     *
     * */
    private Long coefOfLive = 5000L;

    /**
     *
     * Long tmp = (Long.MAX_VALUE-(Long.MAX_VALUE/ coefOfDie))+timeOfLive;
     *
     * if(tmp < getRand().nextLong()) { alive = false; }
     *
     * */
    private Long coefOfDie = 1000L;

    /**
     *
     * if(getRand().nextLong() > (Long.MAX_VALUE-(Long.MAX_VALUE/coefOfColorFC)))
     * this coefficient is used only in FashionCenter
     *
     * */
    private Long coefOfColorFC = 1000L;

    public Pane getMainView() {
        return mainView.getMainPane();
    }

    public Scene getScene() {
        return scene;
    }

    public Long getIndex() {
        return index;
    }

    public HashMap<Long, SimpleDot> getMapOfDots() {
        return mapOfDots;
    }

    public FashionCenter getFashionCenter1() {
        return fashionCenter1;
    }

    public FashionCenter getFashionCenter2() {
        return fashionCenter2;
    }

    public MainViewController() {

        mainView = new MainView();
        scene = new Scene(getMainView());

        index = 0L;
        mapOfDots = new HashMap<>();

        fashionCenter1 = new FashionCenter(new DotView(10,10,10,Color.RED),
                new Point2D(240, 240), 2.0, coefOfColorFC);
        fashionCenter2 = new FashionCenter(new DotView(10,10,10,Color.BLUE),
                new Point2D(260, 260), 2.0, coefOfColorFC);

        addSimpleDot(fashionCenter1, 250, 250);
        addSimpleDot(fashionCenter2, 200, 200);

        random = new Random();

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                update();
            }
        };
        timer.start();
    }

    public void update() {

        List<Long> list = new ArrayList<>();

        for(SimpleDot d : mapOfDots.values()) {

            d.update(fashionCenter1, fashionCenter2);
            if(!d.isAlive()) {
                getMainView().getChildren().remove(d.getView());
                list.add(d.getIndex());
            }
        }
        list.forEach(mapOfDots::remove);

        fashionCenter1.update();
        fashionCenter2.update();

        randomRespown();
    }

    public void addSimpleDot(Dot dot, double x, double y) {

        dot.getView().setTranslateX(x);
        dot.getView().setTranslateY(y);
        getMainView().getChildren().add(dot.getView());
    }

    public void randomRespown() {

        if(random.nextLong() > (Long.MAX_VALUE-(Long.MAX_VALUE/ coefOfRespown))) {

            if(mapOfDots.size() < 26) {

                SimpleDot dot = new SimpleDot(index, coefOfDie, coefOfLive);
                mapOfDots.put(index++, dot);

                addSimpleDot(dot, random.nextInt(450), random.nextInt(450));
            }
        }
    }
}
