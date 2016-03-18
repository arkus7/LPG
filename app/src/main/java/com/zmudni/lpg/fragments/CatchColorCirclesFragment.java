package com.zmudni.lpg.fragments;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zmudni.lpg.R;

import butterknife.Bind;

public class CatchColorCirclesFragment extends BaseFragment {

    @Bind(R.id.surfaceView)
    SurfaceView surfaceView;

    @Override
    public int getLayout() {
        return R.layout.catch_color_circles_fragment;
    }

    @Override
    public void init() {

    }
}
