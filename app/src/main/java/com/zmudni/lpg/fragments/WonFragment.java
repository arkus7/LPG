package com.zmudni.lpg.fragments;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zmudni.lpg.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

public class WonFragment extends BaseFragment {

    @Bind(R.id.next_part_button)
    Button nextPartButton;

    @OnClick(R.id.next_part_button)
    public void onNextPartClick() {
        showFragment(new MapFragment(), "", true);

    }

    @Bind(R.id.history_bubble)
    TextView historyBubble;

    private Timer timer;
    private int delayTime = 0;
    private final int MAX_WAIT_TIME = 2;

    @Override
    public int getLayout() {
        return R.layout.fragment_failed;
    }

    @Override
    public void init() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new NextPartTimerTask(), 100, 1000);
    }


    private boolean isNextPartButtonShowed() {
        return nextPartButton.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }

    class NextPartTimerTask extends TimerTask {

        @Override
        public void run() {
            if(! isNextPartButtonShowed())
                delayTime++;
            if(delayTime >= MAX_WAIT_TIME) {
                delayTime = 0;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nextPartButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        }
    }
}
