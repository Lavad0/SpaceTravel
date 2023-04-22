package com.example.spacetravel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private Bitmap bitmap;
    private List<Rect> frames;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame;
    private double frameTime;
    private double timeForCurrentFrame;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private int padding;

    public Sprite(double x, double y, double velocityX, double velocityY, Rect initialFrame, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.bitmap = bitmap;
        this.frames = new ArrayList<Rect>();
        this.frames.add(initialFrame);
        this.currentFrame = 0;
        this.frameTime = 0.1;
        this.timeForCurrentFrame = 0.0;
        this.frameWidth = initialFrame.width();
        this.frameHeight = initialFrame.height();
        this.padding = 0;
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
    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }
    public int getFrameHeight() {
        return frameHeight;
    }
    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void update (int ms) {
        timeForCurrentFrame += ms;
        if (timeForCurrentFrame >= frameTime) {
            currentFrame = (currentFrame + 1) % frames.size();
            timeForCurrentFrame = timeForCurrentFrame - frameTime;
        }
        x = x + velocityX * ms/1000.0;
        y = y + velocityY * ms/1000.0;
    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();
        Rect destination = new Rect((int)x, (int)y, (int)(x + frameWidth), (int)(y + frameHeight));
        canvas.drawBitmap(bitmap, frames.get(currentFrame), destination,  p);
    }
    public Rect getBoundingBoxRect () {
        return new Rect((int)x+padding, (int)y+padding, (
                int)(x + frameWidth - 2 * padding), (int)(y + frameHeight - 2 * padding));
    }
    public boolean intersect (Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }
    public void onTouch(float x, float y, int screenHeight, int screenWidth) {
        this.x = x - this.frameWidth / 2;
        this.y = y - this.frameHeight / 2;
    }

}
