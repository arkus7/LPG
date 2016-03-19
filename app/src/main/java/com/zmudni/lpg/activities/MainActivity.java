package com.zmudni.lpg.activities;

import com.zmudni.lpg.R;
import com.zmudni.lpg.fragments.CatchColorCirclesFragment;
import com.zmudni.lpg.fragments.RpgFightFragment;

public class MainActivity extends BaseActivity {

    @Override

    //        showFragment(new CatchColorCirclesFragment(), CatchColorCirclesFragment.TAG);

    protected void init() {
        showFragment(new RpgFightFragment(), CatchColorCirclesFragment.TAG, false);
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
