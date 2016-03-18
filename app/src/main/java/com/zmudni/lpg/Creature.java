package com.zmudni.lpg;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Creature extends Entity {

    protected int heathPoints;
    protected int goldHeld;
    protected String name;
    protected int damage;




    public Creature(float x, float y, String imgPath,int HP,int goldHeld,String name,int damage) {
        super(x, y, imgPath);
        this.heathPoints = HP;
        this.goldHeld = goldHeld;
        this.name = name;
        this.damage = damage;
    }
}
