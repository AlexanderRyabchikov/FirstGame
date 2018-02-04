package com.example.alexander.myapplication;

/**
 * Created by alexander on 02.06.17.
 */

public interface ICanvasView {
    void drawCircle(SimpleCircle mainCircle);

    void redraw();

    void showMessage(String text);
}
