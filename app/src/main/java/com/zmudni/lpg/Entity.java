package com.zmudni.lpg;

import android.graphics.Bitmap;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Entity {
    protected float x;
    protected float y;
    protected Bitmap icon;

    public Entity(float x, float y, Bitmap icon) {
        this.x = x;
        this.icon = icon;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Bitmap getBitmap() {
        return icon;
    }

    public void setBitmap(Bitmap icon) {
        this.icon = icon;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
