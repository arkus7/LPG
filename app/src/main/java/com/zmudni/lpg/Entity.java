package com.zmudni.lpg;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Entity {
    protected float x;
    protected float y;
    protected String imgPath;

    public Entity(float x, float y, String imgPath) {
        this.x = x;
        this.imgPath = imgPath;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
