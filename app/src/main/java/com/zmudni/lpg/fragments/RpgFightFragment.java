package com.zmudni.lpg.fragments;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.zmudni.lpg.Entity;
import com.zmudni.lpg.Monster;
import com.zmudni.lpg.Player;
import com.zmudni.lpg.R;
import com.zmudni.lpg.helpers.CanvasFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

public class RpgFightFragment extends BaseFragment implements SurfaceHolder.Callback {

    @Bind(R.id.rpg_fight_surface_view)
    SurfaceView surfaceView;

    @Bind(R.id.answer1)
    Button answer1;
    @Bind(R.id.answer2)
    Button answer2;
    @Bind(R.id.answer3)
    Button answer3;
    @Bind(R.id.answer4)
    Button answer4;

    private Player player;
    private Entity entity;
    private List<Monster> enemies;
    private List<String> fruitsEn;
    private List<String> fruitsPl;
    private int currentEnemy;
    Timer timer;
    protected int currentAnswerTime = 0;
    protected final int maxAnswerTime = 10;

    public void checkAnswer(Monster monster, String answer, Player player) {
        if (monster.getWord().toLowerCase().equalsIgnoreCase(answer)) {
            player.attack(monster);
        } else {
            monster.attack(player);
        }
        if (monster.getHeathPoints() <= 0){
            enemies.remove(currentEnemy);
        }
        if (player.getHeathPoints() <= 0){
            player.died();
            endFightDraw(surfaceView.getHolder(), false);
        }

        if(!enemies.isEmpty() && player.getHeathPoints() > 0) {
            currentEnemy++;
            currentEnemy = currentEnemy % enemies.size();
            FightDraw(surfaceView.getHolder());
        } else if (player.getHeathPoints() > 0){
            endFightDraw(surfaceView.getHolder(), true);
        }


    }

    public void FightDraw(SurfaceHolder holder){
        if(!enemies.isEmpty()){
            Canvas canvas = new Canvas();
            canvas = new CanvasFactory(holder.lockCanvas()).setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                    .drawCreature(player)
                    .drawCreatureCollection(enemies,currentEnemy,entity)
                    .build();
            holder.unlockCanvasAndPost(canvas);
        }

    }

    public void endFightDraw(SurfaceHolder holder,boolean won){
        if ( won == true) {
            Canvas canvas = new CanvasFactory(holder.lockCanvas())
                    .setBackgroundColor(getResources().getColor(R.color.color_button)).setText("You win", 400, 400, 80).build();
            holder.unlockCanvasAndPost(canvas);
        } else {
            Canvas canvas = new CanvasFactory(holder.lockCanvas())
                    .setBackgroundColor(getResources().getColor(R.color.color_button)).setText("You lose",400,400,80).build();
            holder.unlockCanvasAndPost(canvas);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                answer1.setVisibility(View.GONE);
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //this.draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.FightDraw(surfaceView.getHolder());
        timer.scheduleAtFixedRate(new DelayTimeTimerTask(),1000,1000);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


    }

    @OnClick(R.id.answer1)
    public void onAnswer1Click() {
        checkAnswer(enemies.get(currentEnemy), answer1.getText().toString().toLowerCase(),player);

    }

    @OnClick(R.id.answer2)
    public void onAnswer2Click() {
        checkAnswer(enemies.get(currentEnemy), answer2.getText().toString().toLowerCase(), player);
    }

    @OnClick(R.id.answer3)
    public void onAnswer3Click() {
        checkAnswer(enemies.get(currentEnemy), answer3.getText().toString().toLowerCase(), player);
    }

    @OnClick(R.id.answer4)
    public void onAnswer4Click() {
        checkAnswer(enemies.get(currentEnemy), answer4.getText().toString().toLowerCase(), player);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_rpg_fight;
    }

    @Override
    public void init() {
        fruitsEn = Arrays.asList(getResources().getStringArray(R.array.english));
        fruitsPl = Arrays.asList(getResources().getStringArray(R.array.polish));
        entity = new Entity(0,0,BitmapFactory.decodeResource(getResources(),R.mipmap.apple));
        player = new Player(1000,400, BitmapFactory.decodeResource(getResources(), R.mipmap.player1),"Shir",10);
        enemies = new ArrayList<>();
        timer = new Timer();

        currentEnemy = 0;
        enemies.add(new Monster(100, 50, BitmapFactory.decodeResource(getResources(), R.mipmap.enemysnail1), 15, 10, "Slime", 2, "apple", 50));
        enemies.add(new Monster(200, enemies.get(0).getY()+enemies.get(0).getBitmap().getHeight()+15, BitmapFactory.decodeResource(getResources(), R.mipmap.enemysnail1), 15, 10, "Wolf", 2, "peach", 50));
        enemies.add(new Monster(300, enemies.get(1).getY()+enemies.get(1).getBitmap().getHeight()+15, BitmapFactory.decodeResource(getResources(), R.mipmap.enemysnail1), 15, 10, "Scorpion", 2, "banana", 50));

        surfaceView.getHolder().addCallback(this);
    }

    class DelayTimeTimerTask extends TimerTask{

        @Override
        public void run() {
            if(currentAnswerTime >= maxAnswerTime){
                currentAnswerTime = 0;
                checkAnswer(enemies.get(currentEnemy),"",player);
            } else {
                currentAnswerTime++;
            }

        }
    }
}
