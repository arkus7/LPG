package com.zmudni.lpg.helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;

public class DisplayHelper {
    public static int getScreenHeigth(Activity activity) {
        Point p = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(p);
        return p.x;
    }
    public static int getScreenWidth(Activity activity) {
        Point p = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(p);
        return p.y;
    }
}
