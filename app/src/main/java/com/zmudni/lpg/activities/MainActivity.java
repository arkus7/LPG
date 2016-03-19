package com.zmudni.lpg.activities;

import com.zmudni.lpg.R;
import com.zmudni.lpg.fragments.CatchColorCirclesFragment;
import com.zmudni.lpg.fragments.RpgFightFragment;
import com.zmudni.lpg.fragments.StartScreenFragment;

public class MainActivity extends BaseActivity {

    @Override

    //        showFragment(new CatchColorCirclesFragment(), CatchColorCirclesFragment.TAG);

    protected void init() {
        showFragment(new StartScreenFragment(), CatchColorCirclesFragment.TAG, false);
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
