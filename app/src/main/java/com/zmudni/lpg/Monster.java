package com.zmudni.lpg;

import android.graphics.Bitmap;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Monster extends Creature {
    private boolean isBoss; //not sure if we need this
    private boolean useHealthPoints;
    private String word;
    private int experience;


    public Monster(float x, float y, Bitmap icon, int HP, int goldHeld, String name, int damage,String word, int experience) {
        super(x, y, icon, HP, goldHeld, name, damage);
        isBoss = false;
        useHealthPoints = false;
        this.experience = experience;
    }




    public boolean isBoss() {
        return isBoss;
    }

    public void setIsBoss(boolean isBoss) {
        this.isBoss = isBoss;
    }

    public boolean isUseHealthPoints() {
        return useHealthPoints;
    }

    public void setUseHealthPoints(boolean useHealthPoints) {
        this.useHealthPoints = useHealthPoints;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void attack(Creature target) {
        target.heathPoints-=this.damage;
        this.setUseHealthPoints(true);
    }

    public void died(Player player){
        player.setExperience(player.getExperience()+this.experience);
        if (player.checkIfLevelUp()) player.LevelUp();
    }
}
