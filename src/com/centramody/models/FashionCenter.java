package com.centramody.models;

import com.centramody.views.DotView;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class FashionCenter extends Dot {

    private Long coefOfColorFC;

    public Long getCoefOfColorFC() {
        return coefOfColorFC;
    }

    public void setCoefOfColorFC(Long coefOfColorFC) {
        this.coefOfColorFC = coefOfColorFC;
    }

    public FashionCenter(Long coefOfColor) {

        super();
        this.coefOfColorFC = coefOfColor;
    }

    public FashionCenter(DotView view, Point2D direction, double speed, Long coefOfColorFC) {

        super(view, direction, speed);
        this.coefOfColorFC = coefOfColorFC;
    }

    public void changeColor() {

        if(getRand().nextLong() > (Long.MAX_VALUE-(Long.MAX_VALUE/coefOfColorFC))) {
            Color color = Color.rgb(
                    getRand().nextInt(256),
                    getRand().nextInt(256),
                    getRand().nextInt(256)
            );

            getView().setFill(color);
        }
    }

    public void update() {

        move();
        changeColor();
    }

    @Override
    public String toString() {
        return "FashionCenter{" +
                super.toString() + ", " +
                "coefOfColorFC=" + coefOfColorFC +
                '}';
    }
}
