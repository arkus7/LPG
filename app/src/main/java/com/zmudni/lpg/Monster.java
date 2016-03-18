package com.zmudni.lpg;

/**
 * Created by Shirru on 2016-03-18.
 */
public class Monster extends Creature {
    private boolean isBoss; //not sure if we need this
    private boolean useHealthPoints;
    private String word;


    public Monster(float x, float y, String imgPath, int HP, int goldHeld, String name, int damage,String word) {
        super(x, y, imgPath, HP, goldHeld, name, damage);
        isBoss = false;
        useHealthPoints = false;
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
}
