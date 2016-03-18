package com.zmudni.lpg.activities;

import com.zmudni.lpg.R;

public class MainActivity extends BaseActivity {


    @Override
    protected void init() {

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
