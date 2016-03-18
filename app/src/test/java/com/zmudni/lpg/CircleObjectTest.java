package com.zmudni.lpg;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CircleObjectTest {
    @Test
    public void testCollision() throws Exception {
        Player player = new Player(0,0,null,"Shir",10);
        Monster monster = new Monster(0,0,null,15,10,"Slime",2,"apple",50);
        Monster monster2 = new Monster(0,0,null,15,100,"Wolf",2,"peach",50);
        Monster monster3 = new Monster(0,0,null,15,100,"Wolf",20,"peach",50);

        player.attack(monster);
        assertTrue(monster.heathPoints == 5);
        player.attack(monster2);
        assertTrue(monster2.heathPoints == 5);
        monster.attack(player);
        assertTrue(player.heathPoints == 3);
        player.attack(monster);
        player.attack(monster2);
        monster3.attack(player);


    }
}