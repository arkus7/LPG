package com.zmudni.lpg.helpers;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.zmudni.lpg.CircleObject;
import com.zmudni.lpg.Creature;
import com.zmudni.lpg.Entity;
import com.zmudni.lpg.Monster;
import com.zmudni.lpg.R;

import java.util.List;

public class CanvasFactory {
    public static Canvas canvas;
    private final int FONT_OFFSET_Y = 10;
    private final int FONT_OFFSET_X = -10;
    private final int FONT_SIZE = 30;
    private Entity  target;
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
        canvas.drawText("HP: " + creature.getHeathPoints() + "/" + creature.getMaxHealthPoints(), creature.getX()+(((creature.getBitmap().getWidth())/2)-30), creature.getY()+creature.getBitmap().getHeight() + FONT_OFFSET_Y+20, paint);

        return this;
    }


    public CanvasFactory drawCreatureCollection(List<Monster> creatures, int target, Entity entity) {
        int i = 0;
        Paint paint = new Paint();
        paint.setTextSize(FONT_SIZE);
        paint.setARGB(255,255,255,255);
        for (Monster creature : creatures) {
            canvas.drawBitmap(creature.getBitmap(), creature.getX(), creature.getY(), null);
            if (i == target){
                entity.setX(creature.getX()-40);
                entity.setY(creature.getY()+((creature.getBitmap().getHeight())/2)-30);
                canvas.drawBitmap(entity.getBitmap(), entity.getX(), entity.getY(), null);
            }
            canvas.drawText(creature.getWord().toLowerCase(), creature.getX()+ creature.getBitmap().getWidth()+10, creature.getY()+((creature.getBitmap().getHeight())/2), paint);
            canvas.drawText("HP: " + creature.getHeathPoints() + "/" + creature.getMaxHealthPoints(), creature.getX() + (((creature.getBitmap().getWidth()) / 2) - 15), creature.getY() + creature.getBitmap().getHeight() + FONT_OFFSET_Y-10, paint);

            i++;
        }
        return this;
    }

    public Canvas build() {
        return canvas;
    }
}
