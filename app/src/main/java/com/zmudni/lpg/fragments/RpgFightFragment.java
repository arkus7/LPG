package com.zmudni.lpg.fragments;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.zmudni.lpg.CircleObject;
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
    private List<String> fruitsDe;
    private List<String> fruitsJp;
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
        }
        if (monster.getHeathPoints() <= 0){
            enemies.remove(currentEnemy);
            currentAnswerTime = 0;
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
            canvas = new CanvasFactory(holder.lockCanvas()).setBackgroudImage(BitmapFactory.decodeResource(getResources(),R.mipmap.meadow))
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
        timer.cancel();
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

    protected void drawDamage(SurfaceHolder holder){
        Paint paint = new Paint();
        CircleObject c1 = new CircleObject(enemies.get(currentEnemy).getX(),enemies.get(currentEnemy).getX(),null,5);
        switch(animationPart){
            case 0:
                animationPart++;
                paint.setColor(Color.BLUE);

                Canvas canvas = new CanvasFactory(holder.lockCanvas()).drawCircleObject(c1,paint).build();
                holder.unlockCanvasAndPost(canvas);
                break;
            case 1:
                animationPart++;
                paint.setColor(Color.CYAN);
                canvas = new CanvasFactory(holder.lockCanvas()).drawCircleObject(c1,paint).build();
                holder.unlockCanvasAndPost(canvas);
                break;
            case 2:
                animationPart++;
                paint.setColor(Color.RED);
                canvas = new CanvasFactory(holder.lockCanvas()).drawCircleObject(c1,paint).build();
                holder.unlockCanvasAndPost(canvas);
                break;
            case 3:
                animationPart++;
                paint.setColor(Color.GREEN);
                canvas = new CanvasFactory(holder.lockCanvas()).drawCircleObject(c1,paint).build();
                holder.unlockCanvasAndPost(canvas);
                break;
        }
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
        fruitsEn = Arrays.asList(getResources().getStringArray(R.array.english_fruits_and_vegetable));
        fruitsPl = Arrays.asList(getResources().getStringArray(R.array.polish_fruits_and_vegetable));
        fruitsDe = Arrays.asList(getResources().getStringArray(R.array.german_fruits_and_vegetable));
        fruitsJp = Arrays.asList(getResources().getStringArray(R.array.japanese_fruits_and_vegetable));
        enemies = new ArrayList<>();
        timer = new Timer();
        createEntities();

        surfaceView.getHolder().addCallback(this);
    }

    private void createEntities() {
        entity = new Entity(0,0,BitmapFactory.decodeResource(getResources(),R.mipmap.pointer));
        player = new Player(950,350, BitmapFactory.decodeResource(getResources(), R.mipmap.player1),"Shir",10);
        int index = randomIndex(0,fruitsDe.size()-1);
        currentEnemy = 0;
        enemies.add(new Monster(250, 50, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy_snail1), 15, 10, fruitsPl.get(index).toLowerCase(), 2, fruitsEn.get(index).toLowerCase(), 50));
        answer1.setText(enemies.get(0).getName());
        index = randomIndex(0,fruitsDe.size()-1);
        enemies.add(new Monster(100, enemies.get(0).getY() + enemies.get(0).getBitmap().getHeight() + 15, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy_snail1), 15, 10, fruitsPl.get(index).toLowerCase(), 2, fruitsEn.get(index).toLowerCase(), 50));
        answer2.setText(enemies.get(1).getName());
        index = randomIndex(0,fruitsDe.size()-1);
        enemies.add(new Monster(300, enemies.get(1).getY() + enemies.get(1).getBitmap().getHeight() + 15, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy_snail1), 15, 10, fruitsPl.get(index).toLowerCase(), 2, fruitsEn.get(index).toLowerCase(), 50));
        answer3.setText(enemies.get(2).getName());
        index = randomIndex(0,fruitsDe.size()-1);
        enemies.add(new Monster(500, enemies.get(0).getY() + enemies.get(0).getBitmap().getHeight() + 15, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy_snail1), 15, 10, fruitsPl.get(index).toLowerCase(), 2, fruitsEn.get(index).toLowerCase(), 50));
        answer4.setText(enemies.get(3).getName());
    }

    private int randomIndex(int min,int max){
        return min + (int)(Math.random() * ((max - min) + 1));
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
