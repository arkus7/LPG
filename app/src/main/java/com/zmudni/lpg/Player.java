package com.zmudni.lpg;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Player extends Creature {
    private int currentLevel;
    private int experience;
    private int nextLevelOn;

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

    public Player(float x, float y, String imgPath, int HP, int goldHeld, String name, int damage) {
        super(x, y, imgPath, HP, goldHeld, name, damage);
        this.currentLevel = 1;
        this.nextLevelOn = 100;

        experience = 0;
    }

    public void onLevelUp(){
        currentLevel++;
        nextLevelOn = nextLevelOn*2;
    }
}


