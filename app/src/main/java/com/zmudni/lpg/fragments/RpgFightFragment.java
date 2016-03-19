package com.zmudni.lpg.fragments;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zmudni.lpg.Entity;
import com.zmudni.lpg.Monster;
import com.zmudni.lpg.Player;
import com.zmudni.lpg.R;
import com.zmudni.lpg.helpers.CanvasFactory;
import com.zmudni.lpg.helpers.DisplayHelper;
import com.zmudni.lpg.helpers.ToastHelper;

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
    private List<String> elementsEn;
    private List<String> elemntsPl;
    private List<String> fruitsDe;
    private List<String> fruitsJp;
    //private List<Integer> usedIndexes;
    //private List<Integer> deletedEnemies;
    private int currentEnemy;
    Timer timer;
    protected int currentAnswerTime = 0;
    protected final int maxAnswerTime = 10;
    protected int animationPart = 4;



    public void checkAnswer(Monster monster, String answer, Player player) {
        if (monster.getName().toLowerCase().equalsIgnoreCase(answer)) {
            player.attack(monster);
            currentAnswerTime = 0;

        } else {
            monster.attack(player);
            currentAnswerTime = 0;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final Toast toast = Toast.makeText(getContext(),"Prawidłowa odpowiedź to: " + enemies.get(currentEnemy).getName(), Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 500);
                }});
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
            fightDraw(surfaceView.getHolder());
        } else if (player.getHeathPoints() > 0){

            endFightDraw(surfaceView.getHolder(), true);
        }


    }

    public void fightDraw(SurfaceHolder holder) {
        if (! enemies.isEmpty()) {
            Canvas canvas = new CanvasFactory(holder.lockCanvas()).setBackgroudImage(
                    BitmapFactory.decodeResource(getResources(), R.mipmap.background),
                    DisplayHelper.getScreenWidth(getActivity()), DisplayHelper.getScreenHeigth(getActivity()))
                    .drawCreature(player)
                    .drawCreatureCollection(enemies, currentEnemy, entity)
                    .build();
            holder.unlockCanvasAndPost(canvas);
        }

    }

    public void endFightDraw(SurfaceHolder holder,boolean won){

        if (won) {
            timer.cancel();
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            showFragment(new WonFragment(), "", false);
        } else {
            timer.cancel();
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            showFragment(new FailedFragment(), "", false);
        }

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        init();
        fightDraw(surfaceView.getHolder());
        timer.scheduleAtFixedRate(new DelayTimeTimerTask(), 0, 1000);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.fightDraw(surfaceView.getHolder());
        timer.scheduleAtFixedRate(new DelayTimeTimerTask(),0,1000);
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
        elementsEn = Arrays.asList(getResources().getStringArray(R.array.english_elements));
        elemntsPl = Arrays.asList(getResources().getStringArray(R.array.polish_elements));
        fruitsDe = Arrays.asList(getResources().getStringArray(R.array.german_fruits_and_vegetable));
        fruitsJp = Arrays.asList(getResources().getStringArray(R.array.japanese_fruits_and_vegetable));
        enemies = new ArrayList<>();
        //usedIndexes = new ArrayList<>();
        //deletedEnemies = new ArrayList<>();
        timer = new Timer();
        createEntities();

        surfaceView.getHolder().addCallback(this);
    }

    private void createEntities() {
        entity = new Entity(0,0,BitmapFactory.decodeResource(getResources(),R.mipmap.pointer));
        player = new Player(950,350, BitmapFactory.decodeResource(getResources(), R.mipmap.player1),"TestPlayer");

        int index = randomIndex(0, elemntsPl.size()-1);
        currentEnemy = 0;
        enemies.add(new Monster(250, 50, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy_snail1), 11, 10, elemntsPl.get(0).toLowerCase(), 2, elementsEn.get(0).toLowerCase(), 25));
        answer1.setText(enemies.get(0).getName());

        index = randomIndex(0, elemntsPl.size()-1);
        enemies.add(new Monster(100, enemies.get(0).getY() + enemies.get(0).getBitmap().getHeight() + 15, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy_slime1), 22, 20, elemntsPl.get(1).toLowerCase(), 3, elementsEn.get(1).toLowerCase(), 50));
        answer2.setText(enemies.get(1).getName());

        index = randomIndex(0, elemntsPl.size()-1);
        enemies.add(new Monster(300, enemies.get(1).getY() + enemies.get(0).getBitmap().getHeight() + 15, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy_slime2), 33, 25, elemntsPl.get(2).toLowerCase(), 4, elementsEn.get(2).toLowerCase(), 75));
        answer3.setText(enemies.get(2).getName());

        index = randomIndex(0, elemntsPl.size()-1);
        enemies.add(new Monster(500, enemies.get(0).getY() + enemies.get(0).getBitmap().getHeight() + 15, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy_snail1), 50, 10, elemntsPl.get(3).toLowerCase(), 2, elementsEn.get(3).toLowerCase(), 25));
        answer4.setText(enemies.get(3).getName());
    }

    private int randomIndex(int min,int max){
        //int i = min + (int)(Math.random() * ((max - min) + 1));
        //while(list.contains(i)){
            return min + (int)(Math.random() * ((max - min) + 1)); //i =
        //}
        //list.add(i);
        //return i;
    }

    class DelayTimeTimerTask extends TimerTask{

        @Override
        public void run() {
            if(currentAnswerTime >= maxAnswerTime){
                currentAnswerTime = 0;
                checkAnswer(enemies.get(currentEnemy),"wrong",player);
            } else {
                currentAnswerTime++;
            }

        }
    }

}
