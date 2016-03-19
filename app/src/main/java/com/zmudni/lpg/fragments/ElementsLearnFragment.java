package com.zmudni.lpg.fragments;

import android.view.View;
import android.widget.Button;

import com.zmudni.lpg.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

public class ElementsLearnFragment extends BaseFragment {

    @Bind(R.id.next_part_button)
    Button nextButton;

    @OnClick(R.id.next_part_button)
    public void onNextPartButtonClick() {
        showFragment(new ElementsGameFragment(), "", true);
        //todo: hadnle next part
    }

    private Timer timer;
    private int delayTime = 0;
    private final int MAX_WAIT_TIME = 2;

    @Override
    public int getLayout() {
        return R.layout.fragment_element_learn;
    }

    @Override
    public void init() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new NextPartTimerTask(), 100, 1000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }

    private boolean isNextPartButtonShowed() {
        return nextButton.getVisibility() == View.VISIBLE;
    }

    class NextPartTimerTask extends TimerTask {

        @Override
        public void run() {
            if (! isNextPartButtonShowed())
                delayTime++;
            if (delayTime >= MAX_WAIT_TIME) {
                delayTime = 0;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nextButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        }
    }
}
