package com.zmudni.lpg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.zmudni.lpg.fragments.BaseFragment;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {
    protected abstract void init();
    protected abstract int getLayout();
    public abstract int getFragmentContainerId();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowToFullscreenMode();
        setContentView(getLayout());
        ButterKnife.bind(this);
        init();
    }

    private void setWindowToFullscreenMode() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void showFragment(BaseFragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(getFragmentContainerId(), fragment, tag);
        transaction.commitAllowingStateLoss();
    }

    public void runActivity(Class<?> className) {
        runActivity(className, null);
    }

    public void runActivity(Class<?> className, Bundle bundle) {
        Intent i = new Intent(this, className);
        if (bundle != null) i.putExtras(bundle);
        startActivity(i);
        finish();
    }
}
