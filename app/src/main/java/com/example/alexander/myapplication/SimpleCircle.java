package com.example.alexander.myapplication;

/**
 * Created by alexander on 02.06.17.
 */

public class SimpleCircle {
    public static final int SIZE_FREE_AREA = 100;
    protected int x;
    protected int y;
    protected int radius;

    public void setColor(int color) {
        this.color = color;
    }

    private int color;

    public SimpleCircle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getColor() {
        return color;
    }

    public SimpleCircle getCircleArea() {
        return new SimpleCircle(x, y, radius * SIZE_FREE_AREA);
    }

    public boolean isIntersect(SimpleCircle circle) {
        return radius + circle.getRadius() >=
                Math.sqrt(Math.pow(x - circle.getX(), 2) +
                        Math.pow(y - circle.getY(), 2));
    }
}
