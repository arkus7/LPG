package com.zmudni.lpg;

import android.graphics.Bitmap;

/**
 * Created by Shirru on 2016-03-18.
 */
public abstract class Creature extends Entity {

    protected int heathPoints;
    protected int goldHeld;
    protected String name;
    protected int damage;

    public Creature(float x, float y, Bitmap icon,int HP,int goldHeld,String name,int damage) {
        super(x, y, icon);
        this.heathPoints = HP;
        this.goldHeld = goldHeld;
        this.name = name;
        this.damage = damage;
    }
}
