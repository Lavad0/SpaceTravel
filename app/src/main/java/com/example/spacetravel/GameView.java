package com.example.spacetravel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameView extends View {

    private int viewWidth;
    private int viewHeight;
    private int points = 100;
    private boolean game = true;

    private float time = 60;
    private int timerInterval = 30;
    Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
    int w1 = b1.getWidth();
    int h1 = b1.getHeight();
    Rect firstFrame1 = new Rect(0, 0, w1, h1);
    private Sprite playerShip = new Sprite(viewWidth/2f, viewHeight/2f, 0, 0, firstFrame1, b1);

    Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.meteor);
    int w2 = b2.getWidth();
    int h2 = b2.getHeight();
    Rect firstFrame2 = new Rect(0, 0, w2, h2);
    private Sprite meteor = new Sprite(2100, 250, -350, 0, firstFrame2, b2);
    public GameView(Context context) {
        super(context);

        Timer t = new Timer();
        t.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(0, 33, 55);

        playerShip.draw(canvas);
        meteor.draw(canvas);

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(60.0f);
        p.setColor(Color.WHITE);
        canvas.drawText("Топливо: " + points, viewWidth - 380, 70, p);
        canvas.drawText("Время: " + (int)time, viewWidth - 380, 130, p);

        if (time <= 0 && points > 0){
            game = false;
            meteor.setVelocityX(0f);
            p.setTextSize(150.0f);
            canvas.drawText("Вы выиграли!", viewWidth / 2f - 400, viewHeight / 2f, p);

        } else if (points <= 0){
            game = false;
            meteor.setVelocityX(0f);
            p.setTextSize(150.0f);
            p.setColor(Color.RED);
            canvas.drawText("Вы проиграли!", viewWidth / 2f - 400, viewHeight / 2f, p);
        }
    }

    protected void update () {
        playerShip.update(timerInterval);
        meteor.update(timerInterval);

        if (meteor.getX() < - meteor.getFrameWidth()) {
            teleportMeteor();
        }
        if (meteor.intersect(playerShip)) {
            points -= 10;
            teleportMeteor();
        }
        invalidate();
    }

    private void teleportMeteor () {
        meteor.setX(viewWidth + Math.random() * 500);
        meteor.setY(Math.random() * (viewHeight - meteor.getFrameHeight()));
        meteor.setVelocityX(meteor.getVelocityX() - 75);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (game){
                    playerShip.onTouch(
                            event.getX(),
                            event.getY(),
                            getHeight(),
                            getWidth()
                    );
                }

        }
        return true;
    }


    class Timer extends CountDownTimer {

        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            if (game){
                update();
                time -= 0.03;
            }

        }

        @Override
        public void onFinish() {

        }
    }


}
