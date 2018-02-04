package com.example.alexander.myapplication;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by alexander on 02.06.17.
 */

public class EnemyCircle extends SimpleCircle {

    public static final int TO_RADIUS = 110;
    public static final int RANDOM_SPEED = 5;
    public static final int FROM_RADIUS = 10;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int FOOR_COLOR = Color.GREEN;
    private int dx;
    private int dy;

    public EnemyCircle(int x, int y, int radius,int dx,int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeigth());
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);
        int dx = -RANDOM_SPEED + random.nextInt(RANDOM_SPEED*2);
        int dy = -RANDOM_SPEED + random.nextInt(RANDOM_SPEED*2);
        EnemyCircle enemyCircle = new EnemyCircle(x, y, radius, dx, dy);
        enemyCircle.setColor(ENEMY_COLOR);
        return enemyCircle;
    }

    public void setEnemyOrFoodColorDependsOn(MainCircle mainCircle) {
        if(isSmallerThan(mainCircle))
        {
            setColor(FOOR_COLOR);
        }
        else
        {
            setColor(ENEMY_COLOR);
        }
    }

    protected boolean isSmallerThan(SimpleCircle circle) {
        if (radius < circle.getRadius())
        {
            return true;
        }
        return false;
    }

    public void moveOneStep() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if (x > GameManager.getWidth() || x < 0)
        {
            dx = -dx;
        }
        if (y > GameManager.getHeigth() || y < 0)
        {
            dy = -dy;
        }
    }
}
