package com.zmudni.lpg.helpers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.zmudni.lpg.CircleObject;
import com.zmudni.lpg.Creature;
import com.zmudni.lpg.Entity;
import com.zmudni.lpg.Monster;
import com.zmudni.lpg.Player;

import java.util.List;

public class CanvasFactory {
    public static Canvas canvas;
    private final int FONT_OFFSET_Y = 10;
    private final int FONT_OFFSET_X = - 10;
    private final int FONT_SIZE = 30;
    private Entity target;

    public CanvasFactory(Canvas canvas) {
        this.canvas = canvas;
    }


    public CanvasFactory setBackgroundColor(int colorId) {
        canvas.drawColor(colorId);
        return this;
    }

    public CanvasFactory setText(String text, float x, float y, int size) {
        Paint paint = new Paint();
        paint.setTextSize(FONT_SIZE);
        paint.setARGB(255, 255, 255, 255);
        canvas.drawText(text, x, y, paint);
        return this;
    }

    public CanvasFactory setBackgroudImage(Bitmap bitmap, int screenWidth, int screenHeight) {
        canvas.drawBitmap(bitmap, null, new RectF(0, 0, screenHeight, screenWidth), null);
        return this;
    }

    public CanvasFactory drawCircleObject(CircleObject object, Paint paint) {
        canvas.drawCircle(object.getX(), object.getY(), object.getRadius(), paint);
        return this;
    }

    public CanvasFactory drawCircleObject(CircleObject object) {
        canvas.drawCircle(object.getX(), object.getY(), object.getRadius(), new Paint());
        return this;
    }

    public CanvasFactory drawCreature(Creature creature) {
        Paint paint = new Paint();
        paint.setTextSize(FONT_SIZE);
        paint.setARGB(255, 255, 255, 255);
        canvas.drawBitmap(creature.getBitmap(), creature.getX(), creature.getY(), null);
        canvas.drawText("HP: " + creature.getHeathPoints() + "/" + creature.getMaxHealthPoints(), creature.getX(), creature.getY() + creature.getBitmap().getHeight() + FONT_OFFSET_Y + 25, paint);
        canvas.drawText("Lvl: " + ((Player) creature).getCurrentLevel(), creature.getX(), creature.getY() + creature.getBitmap().getHeight() + FONT_OFFSET_Y + 50, paint);
        canvas.drawText("Exp: " + ((Player) creature).getExperience() + "/" + ((Player) creature).getNextLevelOn(), creature.getX(), creature.getY() + creature.getBitmap().getHeight() + FONT_OFFSET_Y + 75, paint);
        return this;
    }


    public CanvasFactory drawCreatureCollection(List<Monster> creatures, int target, Entity entity) {
        int i = 0;
        Paint paint = new Paint();
        paint.setTextSize(FONT_SIZE);
        paint.setARGB(255, 255, 255, 255);
        for (Monster creature : creatures) {
            canvas.drawBitmap(creature.getBitmap(), creature.getX(), creature.getY(), null);
            if (i == target) {
                entity.setX(creature.getX() - 40);
                entity.setY(creature.getY() + ((creature.getBitmap().getHeight()) / 2) - 30);
                canvas.drawBitmap(entity.getBitmap(), entity.getX(), entity.getY(), null);
            }
            canvas.drawText(creature.getWord().toLowerCase(), creature.getX() + creature.getBitmap().getWidth() + 10, creature.getY() + ((creature.getBitmap().getHeight()) / 2), paint);
            canvas.drawText("HP: " + creature.getHeathPoints() + "/" + creature.getMaxHealthPoints(), creature.getX() + 50, creature.getY() + creature.getBitmap().getHeight() + FONT_OFFSET_Y - 10, paint);

            i++;
        }
        return this;
    }

    public Canvas build() {
        return canvas;
    }
}
