package com.zmudni.lpg.helpers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.zmudni.lpg.CircleObject;
import com.zmudni.lpg.Creature;
import com.zmudni.lpg.Monster;

import java.util.List;

public class CanvasFactory {
    public static Canvas canvas;
    private final int FONT_OFFSET_Y = 120;
    private final int FONT_OFFSET_X = -20;
    private final int FONT_SIZE = 30;
    public CanvasFactory(Canvas canvas) {
        this.canvas = canvas;
    }


    public CanvasFactory setBackgroundColor(int colorId) {
        canvas.drawColor(colorId);
        return this;
    }

    public CanvasFactory setText(String text, float x, float y,int size) {
        Paint paint = new Paint();
        paint.setTextSize(FONT_SIZE);
        paint.setARGB(255,255,255,255);
        canvas.drawText(text,x,y,paint);
        return this;
    }

    public CanvasFactory setBackgroudImage(Bitmap bitmap) {
        canvas.drawBitmap(bitmap, 0, 0, null);
        return this;
    }

    public CanvasFactory drawCircleObject(CircleObject object) {
        canvas.drawCircle(object.getX(), object.getY(), object.getRadius(), new Paint());
        return this;
    }

    public CanvasFactory drawCreature(Creature creature) {
        Paint paint = new Paint();
        paint.setTextSize(FONT_SIZE);
        paint.setARGB(255,255,255, 255);
        canvas.drawBitmap(creature.getBitmap(), creature.getX(), creature.getY(), null);
        canvas.drawText("HP: " + creature.getHeathPoints() + "/" + creature.getMaxHealthPoints(), creature.getX()+FONT_OFFSET_X, creature.getY() + FONT_OFFSET_Y, paint);

        return this;
    }

    public CanvasFactory drawCreatureCollection(List<Monster> creatures) {
        Paint paint = new Paint();
        paint.setTextSize(FONT_SIZE);
        paint.setARGB(255,255,255,255);
        for (Monster creature : creatures) {
            canvas.drawBitmap(creature.getBitmap(), creature.getX(), creature.getY(), null);
            canvas.drawText("HP: " + creature.getHeathPoints()+"/"+creature.getMaxHealthPoints(), creature.getX()+FONT_OFFSET_X, creature.getY() + FONT_OFFSET_Y, paint);
        }
        return this;
    }

    public Canvas build() {
        return canvas;
    }
}
