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
        setContentView(getLayout());
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() == 1)
            finish();
        else
            super.onBackPressed();
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

    public void showFragment(BaseFragment fragment, String tag, boolean shouldAddToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(getFragmentContainerId(), fragment, tag);
        if (shouldAddToBackStack) transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public void showFragmentWithAnimation(BaseFragment fragment, String tag, int enterAnim, int exitAnim, boolean shouldAddToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(enterAnim, exitAnim);
        transaction.add(getFragmentContainerId(), fragment, tag);
        if (shouldAddToBackStack) transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public void showFragmentWithAnimation(BaseFragment fragment, String tag, int enterAnim, int exitAnim, int popEnter, int popExit, boolean shouldAddToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(enterAnim, exitAnim, popEnter, popExit);
        transaction.add(getFragmentContainerId(), fragment, tag);
        if (shouldAddToBackStack) transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }
}
