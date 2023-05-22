package com.example.spacetravel;




import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.ContentInfo;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.spacetravel.activity.GameSurvivalLevel;
import com.example.spacetravel.activity.MainActivity;

import java.util.ArrayList;
import java.util.Collections;


public class GameSurvivalView extends View {

    private static FragmentManager manager;
    public void setManager(FragmentManager manager) {
        GameSurvivalView.manager = manager;
    }
    MyDialog myDialog = new MyDialog();
    private static final String KEY_FOR_RECORD = "RECORD";
    private static final String KEY_FOR_SHIP = "SHIP";
    private static final String KEY_FOR_METEOR = "METEOR";
    private SharedPreferences settings;
    private static int changeship = 1;
    private static int changemeteor = 1;
    public void setChangeship(int changeship) {
        GameSurvivalView.changeship = changeship;
    }
    public void setChangemeteor(int changemeteor) {
        GameSurvivalView.changemeteor = changemeteor;
    }
    private int viewWidth;
    private int viewHeight;
    private int record;
    private boolean game = true;
    private double time = 0;
    private int oil = 100;
    private int k = 0;
    private int o = 10;
    private final int timerInterval = 30;
    int count = 0;

    Bitmap shipb1 = BitmapFactory.decodeResource(getResources(), R.drawable.ship1);
    int shipw1 = shipb1.getWidth();
    int shiph1 = shipb1.getHeight();
    Rect shipframe1 = new Rect(0, 0, shipw1, shiph1);

    Bitmap shipb2 = BitmapFactory.decodeResource(getResources(), R.drawable.ship2);
    int shipw2 = shipb2.getWidth();
    int shiph2 = shipb2.getHeight();
    Rect shipframe2 = new Rect(0, 0, shipw2, shiph2);

    Bitmap meteorb1 = BitmapFactory.decodeResource(getResources(), R.drawable.meteor1);
    int meteorw1 = meteorb1.getWidth();
    int meteorh1 = meteorb1.getHeight();
    Rect meteorframe1 = new Rect(0, 0, meteorw1, meteorh1);

    Bitmap meteorb2 = BitmapFactory.decodeResource(getResources(), R.drawable.meteor2);
    int meteorw2 = meteorb2.getWidth();
    int meteorh2 = meteorb2.getHeight();
    Rect meteorframe2= new Rect(0, 0, meteorw2, meteorh2);

    Bitmap meteorb3 = BitmapFactory.decodeResource(getResources(), R.drawable.meteor3);
    int meteorw3 = meteorb3.getWidth();
    int meteorh3 = meteorb3.getHeight();
    Rect meteorframe3 = new Rect(0, 0, meteorw3, meteorh3);

    Bitmap meteorb4 = BitmapFactory.decodeResource(getResources(), R.drawable.meteor4);
    int meteorw4 = meteorb4.getWidth();
    int meteorh4 = meteorb4.getHeight();
    Rect meteorframe4 = new Rect(0, 0, meteorw4, meteorh4);

    Bitmap oilb = BitmapFactory.decodeResource(getResources(), R.drawable.oil);
    int oilw = oilb.getWidth();
    int oilh = oilb.getHeight();
    Rect oilframe = new Rect(0, 0, oilw, oilh);

    private final Sprite ship = new Sprite(viewWidth/2f, viewHeight/2f, 0, 0, shipframe1, shipb1);
    private final Sprite meteor = new Sprite(2100, 250, -500, 0, meteorframe1, meteorb1);
    private final Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.space);

    public GameSurvivalView(Context context) {
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
        canvas.drawBitmap(bg, 0, 0, null);
        settings = getContext().getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        if (time > record){
            settings.edit().putInt(KEY_FOR_RECORD, (int)time).apply();
        }
        record = settings.getInt(KEY_FOR_RECORD, 0);

        if (changeship == 1){
            ship.setBitmap(shipb1);
            ship.setFrame(shipframe1);
        }
        if (changeship == 2){
            ship.setBitmap(shipb2);
            ship.setFrame(shipframe2);
        }
        if (changemeteor == 1){
            meteor.setBitmap(meteorb1);
            meteor.setFrame(meteorframe1);
        }
        if (changemeteor == 2){
            meteor.setBitmap(meteorb2);
            meteor.setFrame(meteorframe2);
        }
        if (changemeteor == 3){
            meteor.setBitmap(meteorb3);
            meteor.setFrame(meteorframe3);
        }
        if (changemeteor == 4){
            meteor.setBitmap(meteorb4);
            meteor.setFrame(meteorframe4);
        }

        ship.draw(canvas);
        meteor.draw(canvas);


        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(60.0f);
        p.setColor(Color.WHITE);

        canvas.drawText(getContext().getString(R.string.fuel) + " " + oil, viewWidth - 380, 70, p);
        canvas.drawText(getContext().getString(R.string.time) + " " + (int)time, viewWidth - 380, 130, p);
        canvas.drawText(getContext().getString(R.string.record) + " " + record, viewWidth - 380, 190, p);


        if (oil == 0){
            myDialog.show(manager, "myDialog");
            game = false;
            meteor.setVelocityY(0);
            meteor.setVelocityX(0);
        }
    }
    protected void update () {
        ship.update(timerInterval);
        meteor.update(timerInterval);

        if (meteor.getX() < - meteor.getFrameWidth()) {
            teleportMeteor();
        }
        if (meteor.getY() > viewHeight){
            teleportMeteor();
        }
        if (meteor.intersect(ship)) {
            oil -= o;
            teleportMeteor();
        }
        invalidate();
    }

    private void teleportMeteor () {
        meteor.setBitmap(meteorb1);
        meteor.setFrame(meteorframe1);
        o = 10;
        k += 1;
        double velocity = 600 + 100 * k;
        double a = (int)(Math.random()*10)+1;
        if (a % 2 == 0){
            if (a == 6){
                meteor.setBitmap(oilb);
                meteor.setFrame(oilframe);
                velocity *= 2;
                o = 10;
            }
            meteor.setX(viewWidth + Math.random() * 500);
            meteor.setY(Math.random() * (viewHeight - meteor.getFrameHeight()));
            meteor.setVelocityY(0);
            meteor.setVelocityX(-velocity);
        }
        else if (a % 2 == 1){
            meteor.setX(Math.random() * (viewWidth - meteor.getFrameWidth()));
            meteor.setY(-meteor.getFrameWidth());
            meteor.setVelocityX(0);
            meteor.setVelocityY(velocity / 1.5);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (game){
                    ship.onTouch(event.getX(), event.getY(), viewWidth, viewHeight);
                }
        }


        return true;
    }

    public void pausegame() {
        count += 1;
        if (oil > 0){
            if (count % 2 == 1){
                game = false;
                meteor.setVelocityY(0);
                meteor.setVelocityX(0);

            } else {
                game = true;
                teleportMeteor();
            }
        }

    }

    class Timer extends CountDownTimer {

        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            if (game){
                update();
                time += 0.03;
            }

        }
        @Override
        public void onFinish() {

        }
    }


}
