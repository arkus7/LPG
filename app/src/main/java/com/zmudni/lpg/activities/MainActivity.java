package com.zmudni.lpg.activities;

import com.zmudni.lpg.R;
import com.zmudni.lpg.fragments.CatchColorCirclesFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void init() {
        runActivity(CatchColorCirclesActivity.class);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.fragment_container;
    }
}
