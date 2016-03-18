package com.zmudni.lpg.fragments;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zmudni.lpg.R;

import butterknife.Bind;

public class CatchColorCirclesFragment extends BaseFragment implements SurfaceHolder.Callback {

    @Bind(R.id.surfaceView)
    SurfaceView surfaceView;

    @Override
    public int getLayout() {
        return R.layout.fragment_catch_color_circles;
    }

    @Override
    public void init() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
