package com.zmudni.lpg;

/**
 * Created by Shirru on 2016-03-18.
 */
public class CircleObject extends Entity{
    protected float centerX;
    protected float centerY;
    protected float radius;

    private void offset(float dx,float dy){
        this.centerX = this.x + dx;
        this.centerY = this.y + dy;
    }

    public CircleObject(float x, float y, String imgPath,float radius) {
        super(x, y, imgPath);
        this.radius = radius;
        this.offset(this.radius,this.radius);
    }

    public boolean isCollided(CircleObject secondObject){
       return Math.sqrt(Math.pow(secondObject.centerX - this.centerX,2)+ Math.pow(secondObject.centerY-this.centerY,2)) <= this.radius+secondObject.radius;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}