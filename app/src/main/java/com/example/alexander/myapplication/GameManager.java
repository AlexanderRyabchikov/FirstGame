package com.example.alexander.myapplication;

import java.util.ArrayList;

/**
 * Created by alexander on 02.06.17.
 */

public class GameManager {

    public static final int MAX_CIRCLES = 10;
    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> circles;
    private CanvasView canvasView;
    private static int width;

    private static int heigth;
    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w;
        heigth = h;
        initMainCircle();
        initEnemyCircles();
    }

    private void initEnemyCircles() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        circles = new ArrayList<EnemyCircle>();
        for (int i = 0; i < MAX_CIRCLES; i++)
        {
            EnemyCircle enemyCircle;
            do {
                enemyCircle = EnemyCircle.getRandomCircle();
            }while(enemyCircle.isIntersect(mainCircle));

            circles.add(enemyCircle);
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {
        for (EnemyCircle circle: circles) {
            circle.setEnemyOrFoodColorDependsOn(mainCircle);

        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeigth() {
        return heigth;
    }

    private void initMainCircle() {
        mainCircle = new MainCircle(width / 2, heigth / 2);
    }

    public void onDraw() {
        canvasView.drawCircle(mainCircle);
        for(EnemyCircle circle : circles)
        {
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleOnTouchAt(x,y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle circleForDel = null;
        for (EnemyCircle circle: circles) {
            if(mainCircle.isIntersect(circle))
            {
                if (circle.isSmallerThan(mainCircle))
                {
                    mainCircle.growRadius(circle);
                    circleForDel = circle;
                    calculateAndSetCirclesColor();
                    break;

                }
                else {
                    gameEnd("YOU LOSE!");
                    return;
                }
            }

        }
        if(circleForDel != null)
        {
            circles.remove(circleForDel);
        }
        if(circles.isEmpty())
        {
            gameEnd("YOU WIN!!");
        }
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle circle : circles)
        {
            circle.moveOneStep();
        }
    }
}
