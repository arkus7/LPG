package com.zmudni.lpg.fragments;


import android.widget.ImageView;

import com.zmudni.lpg.R;

import butterknife.Bind;
import butterknife.OnClick;

public class MapFragment extends BaseFragment {

    @Bind(R.id.island_tutorial)
    ImageView tutorialIsland;

    @OnClick(R.id.island_tutorial)
    public void onTutorialIslandClick() {
        showFragment(new TutorialScreenFragment(), "", true);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public void init() {

    }
}
