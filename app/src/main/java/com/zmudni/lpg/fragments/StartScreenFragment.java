package com.zmudni.lpg.fragments;

import android.widget.Button;

import com.zmudni.lpg.R;

import butterknife.Bind;
import butterknife.OnClick;

public class StartScreenFragment extends BaseFragment {

    @Bind(R.id.start_screen_button)
    Button startButton;

    @OnClick(R.id.start_screen_button)
    public void onStartButtonClick() {
        showFragment(new MapFragment(), "MapFragment", true);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_start_screen;
    }

    @Override
    public void init() {

    }
}
