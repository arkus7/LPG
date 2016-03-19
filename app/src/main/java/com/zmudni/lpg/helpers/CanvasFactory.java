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

    public CanvasFactory(Canvas canvas) {
        this.canvas = canvas;
    }

    public CanvasFactory setBackgroundColor(int colorId) {
        canvas.drawColor(colorId);
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
        canvas.drawBitmap(creature.getBitmap(), creature.getX(), creature.getY(), null);
        return this;
    }

    public CanvasFactory drawCreatureCollection(List<Monster> creatures) {
        for (Creature creature : creatures) {
            canvas.drawBitmap(creature.getBitmap(), creature.getX(), creature.getY(), null);
        }
        return this;
    }

    public Canvas build() {
        return canvas;
    }
}
