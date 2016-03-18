package com.zmudni.lpg;

import android.graphics.Bitmap;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Player extends Creature {
    private int currentLevel;
    private int experience;
    private int nextLevelOn;


    public void attack(Monster target){
        if (target.isUseHealthPoints()){
            target.heathPoints = 0;
        } else {
            target.heathPoints-=this.damage;
        }

        if(target.heathPoints == 0) target.died(this);

}

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getNextLevelOn() {
        return nextLevelOn;
    }

    public void setNextLevelOn(int nextLevelOn) {
        this.nextLevelOn = nextLevelOn;
    }

    public Player(float x, float y, Bitmap icon, int HP, int goldHeld, String name, int damage) {
        super(x, y, icon, HP, goldHeld, name, damage);
        this.currentLevel = 1;
        this.nextLevelOn = 100;

        experience = 0;
    }

    public void LevelUp(){
        currentLevel++;
        nextLevelOn = nextLevelOn*2;
    }

    public boolean checkIfLevelUp(){
        return currentLevel > nextLevelOn;
    }
}


