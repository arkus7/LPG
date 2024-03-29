package com.zmudni.lpg;

import android.graphics.Bitmap;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Creature extends Entity {

    protected int maxHealthPoints;
    protected int heathPoints;
    protected int goldHeld;
    protected String name;
    protected int damage;

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public Creature(float x, float y, Bitmap icon,int HP,int goldHeld,String name,int damage) {
        super(x, y, icon);
        this.heathPoints = HP;
        this.goldHeld = goldHeld;
        this.name = name;
        this.damage = damage;
        this.maxHealthPoints = HP;
    }

    public int getHeathPoints() {
        return heathPoints;
    }

    public void setHeathPoints(int heathPoints) {
        this.heathPoints = heathPoints;
    }

    public int getGoldHeld() {
        return goldHeld;
    }

    public void setGoldHeld(int goldHeld) {
        this.goldHeld = goldHeld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
