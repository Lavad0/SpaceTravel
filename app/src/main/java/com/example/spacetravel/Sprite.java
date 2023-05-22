package com.example.spacetravel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private Rect frame;
    private Bitmap bitmap;
    private int frameWidth;
    private int frameHeight;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    public Sprite(double x, double y, double velocityX, double velocityY, Rect frame, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.frame = frame;
        this.bitmap = bitmap;
        this.frameWidth = frame.width();
        this.frameHeight = frame.height();
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public int getFrameWidth() {
        return frameWidth;
    }
    public int getFrameHeight() {
        return frameHeight;
    }
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setFrame(Rect frame) {
        this.frame = frame;
    }

    public void update (int ms) {
        x = x + velocityX * ms/1000.0;
        y = y + velocityY * ms/1000.0;
    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();
        Rect rect = new Rect((int)x, (int)y, (int)(x + frameWidth), (int)(y + frameHeight));
        canvas.drawBitmap(bitmap, this.frame, rect,  p);
    }
    public Rect getBoundingBoxRect () {
        return new Rect((int)x, (int)y,
                (int)(x + frameWidth), (int)(y + frameHeight));
    }
    public boolean intersect (Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }
    public void onTouch(float x, float y, int w, int h) {
        this.x = x - this.frameWidth / 2f;
        this.y = y - this.frameHeight / 2f;
        if (this.x < 0){
            this.x = 0;
        }
        if (this.y < 0){
            this.y = 0;
        }
        if (this.x + frameWidth > w){
            this.x = w - frameWidth;
        }
        if (this.y + frameHeight > h){
            this.y = h - frameHeight;
        }
    }

}
