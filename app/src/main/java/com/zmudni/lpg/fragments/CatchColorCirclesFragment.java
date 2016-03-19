package com.zmudni.lpg.fragments;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zmudni.lpg.CircleObject;
import com.zmudni.lpg.R;
import com.zmudni.lpg.helpers.CanvasFactory;
import com.zmudni.lpg.helpers.ToastHelper;

import butterknife.Bind;

public class CatchColorCirclesFragment extends BaseFragment implements SurfaceHolder.Callback {

    @Bind(R.id.surfaceView)
    SurfaceView surfaceView;

    public static String TAG = "CatchColorCirclesFragment";

    @Override
    public int getLayout() {
        return R.layout.fragment_catch_color_circles;
    }

    @Override
    public void init() {
        surfaceView.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ToastHelper.show(getContext(), "surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        ToastHelper.show(getContext(), "surfaceChanged");
        Canvas canvas = new CanvasFactory(holder.lockCanvas())
                .setBackgroundColor(getResources().getColor(R.color.colorAccent))
                .drawCircleObject(new CircleObject(300, 300, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 200))
                .build();
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        ToastHelper.show(getContext(), "surfaceDestroyed");
    }

    private void tryDraw(SurfaceHolder holder, int colorId) {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(getResources().getColor(colorId));
        holder.unlockCanvasAndPost(canvas);
    }
}
