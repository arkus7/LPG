package com.zmudni.lpg.activities;

import com.zmudni.lpg.R;
import com.zmudni.lpg.fragments.CatchColorCirclesFragment;

public class CatchColorCirclesActivity extends BaseActivity {

    @Override
    protected void init() {
        showFragment(new CatchColorCirclesFragment(), "TAG", false);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_catch_color_circles;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.catch_color_circles_fragment_container;
    }
}
