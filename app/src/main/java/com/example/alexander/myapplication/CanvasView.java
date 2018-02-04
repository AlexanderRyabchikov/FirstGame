package com.example.alexander.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


/**
 * Created by alexander on 02.06.17.
 */


class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private Context context;
    public GestureListener(Context context) {

        this.context = context;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Change window")
                .setMessage("You double tap")
                .setCancelable(false)
                .setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return true;
    }
}


public class CanvasView extends View implements ICanvasView
{
    private static int width;
    private static int heigth;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;
    GestureDetector gestureDetector;

    private GameManager gameManager;

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        gameManager = new GameManager(this, width, heigth);
        gestureDetector = new GestureDetector(context, new GestureListener(context));
        initPaint();

    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        heigth = displaymetrics.heightPixels;
    }


    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null)
        {
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            gameManager.onTouchEvent(x,y);
        }
        invalidate();
        return gestureDetector.onTouchEvent(event);
    }

}