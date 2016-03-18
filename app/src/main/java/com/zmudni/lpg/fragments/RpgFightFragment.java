package com.zmudni.lpg.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zmudni.lpg.Monster;
import com.zmudni.lpg.Player;
import com.zmudni.lpg.R;
import com.zmudni.lpg.helpers.SurfaceHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    private List<Monster> enemy;
    private int currentEnemy;

    public void checkAnswer(Monster monster, String answer, Player player){
        if (monster.getWord().toLowerCase().equalsIgnoreCase(answer)){
            player.attack(monster);
        } else {
            monster.attack(player);
        }
        if (monster.getHeathPoints() < 0) enemy.remove(currentEnemy);
        currentEnemy++;
        currentEnemy=currentEnemy%enemy.size();
    }

    public void draw(){
        SurfaceHelper.drawCreature(surfaceView.getHolder(),player);
        SurfaceHelper.drawCreature(surfaceView.getHolder(),enemy.get(0));
        SurfaceHelper.drawCreature(surfaceView.getHolder(),enemy.get(1));
         //   SurfaceHelper.drawCreature(surfaceView.getHolder(),enemy.get(i));

    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //this.draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.draw();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @OnClick(R.id.answer1)
    public void onAnswer1Click() {
        checkAnswer(enemy.get(currentEnemy), answer1.getText().toString(),player);
    }

    @OnClick(R.id.answer2)
    public void onAnswer2Click() {
        checkAnswer(enemy.get(currentEnemy), answer2.getText().toString(), player);
    }

    @OnClick(R.id.answer3)
    public void onAnswer3Click() {
        checkAnswer(enemy.get(currentEnemy), answer3.getText().toString(), player);
    }

    @OnClick(R.id.answer4)
    public void onAnswer4Click() {
        checkAnswer(enemy.get(currentEnemy), answer4.getText().toString(), player);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_rpg_fight;
    }

    @Override
    public void init() {
        player = new Player(500,1000, BitmapFactory.decodeResource(getResources(), R.mipmap.enemy),"Shir",10);
        enemy = new ArrayList<>();
        currentEnemy = 1;
        enemy.add(new Monster(100,1000,BitmapFactory.decodeResource(getResources(),R.mipmap.apple),15,10,"Slime",2,"apple",50));
        enemy.add(new Monster(150,1000,BitmapFactory.decodeResource(getResources(),R.mipmap.cherry),15,10,"Wolf",2,"peach",50));
        surfaceView.getHolder().addCallback(this);
    }

}
