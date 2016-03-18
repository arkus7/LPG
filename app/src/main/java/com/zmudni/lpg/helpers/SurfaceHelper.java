package com.zmudni.lpg.helpers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.zmudni.lpg.CircleObject;
import com.zmudni.lpg.Creature;

public class SurfaceHelper {
    public static void setBackgroundColor(SurfaceHolder holder, int colorId) {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.BLUE);
        holder.unlockCanvasAndPost(canvas);
    }

    public static void setBackgroudImage(SurfaceHolder holder, Bitmap bitmap) {
        Canvas canvas = holder.lockCanvas();
        canvas.drawBitmap(bitmap, 0, 0, null);
        holder.unlockCanvasAndPost(canvas);
    }

    public static void drawCircleObject(SurfaceHolder holder, CircleObject object) {
        Canvas canvas = holder.lockCanvas();
        canvas.drawCircle(object.getX(), object.getY(), object.getRadius(), new Paint());
        holder.unlockCanvasAndPost(canvas);
    }

    public static void drawCreature(SurfaceHolder holder,Creature creature){
        Canvas canvas = holder.lockCanvas();
        canvas.drawBitmap(creature.getBitmap(),creature.getX(),creature.getY(),null);
        holder.unlockCanvasAndPost(canvas);
    }
}
