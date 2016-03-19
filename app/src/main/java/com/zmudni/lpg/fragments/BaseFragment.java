package com.zmudni.lpg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmudni.lpg.activities.BaseActivity;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    public abstract int getLayout();
    public abstract void init();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void showFragment(BaseFragment fragment, String tag, boolean shouldAddToBackStack) {
        ((BaseActivity)getActivity()).showFragment(fragment, tag, shouldAddToBackStack);
    }

    public void showFragmentWithAnimation(BaseFragment fragment, String tag, int enterAnim, int exitAnim, boolean shouldAddToBackStack) {
        ((BaseActivity)getActivity()).showFragmentWithAnimation(fragment, tag, enterAnim, exitAnim, shouldAddToBackStack);
    }

    public void showFragmentWithEnterAndPopAnimation(BaseFragment fragment, String tag, int enterAnim, int exitAnim, int popEnter, int popExit, boolean shouldAddToBackStack) {
        ((BaseActivity)getActivity()).showFragmentWithAnimation(fragment, tag, enterAnim, exitAnim, popEnter, popExit, shouldAddToBackStack);
    }
}
