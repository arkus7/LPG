package com.zmudni.lpg.fragments;

import com.zmudni.lpg.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends BaseFragment {

    int TIME_TO_WAIT = 2;
    int currentTime = 0;
    Timer timer;

    @Override
    public int getLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    public void init() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new StartTimerTask(), 100, 1000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }

    private void start() {
        timer.cancel();
        showFragment(new StartScreenFragment(), "", false);
    }

    class StartTimerTask extends TimerTask {

        @Override
        public void run() {
            if(currentTime < TIME_TO_WAIT)
                currentTime++;
            else {
                start();
            }
        }
    }
}
