package com.example.alexander.myapplication;

import android.graphics.Color;

/**
 * Created by alexander on 02.06.17.
 */

public class MainCircle extends SimpleCircle {
    public static final int INIT_RADIUS = 50;
    public static final int CIRCLE_SPEED = 100;
    public static final int OUR_COLOR = Color.BLUE;


    public MainCircle(int x, int y) {
        super(x, y, INIT_RADIUS);
        setColor(OUR_COLOR);
    }


    public void moveMainCircleOnTouchAt(int x1, int y1) {

        int dx = (x1 - x) * CIRCLE_SPEED / GameManager.getWidth();
        int dy = (y1 - y) * CIRCLE_SPEED / GameManager.getHeigth();
        x += dx;
        y += dy;
    }

    public void initRadius() {
        radius = INIT_RADIUS;
    }

    public void growRadius(SimpleCircle circle) {
        radius = (int)Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2));
    }
}
