package com.zmudni.lpg;

import android.graphics.Point;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Entity {
    protected Point location;

    public Entity(Point location, String imgPath) {
        this.location = location;
        this.imgPath = imgPath;
    }

    public Point getLocation() {

        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    protected String imgPath;




}
