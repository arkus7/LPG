package com.zmudni.lpg;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        CircleObject c1 = new CircleObject(5,5,null,5);
        CircleObject c2 = new CircleObject(15,5,null,5);
        CircleObject c3 = new CircleObject(16,5,null,5);
        CircleObject c4 = new CircleObject(5,5,null,5);

        assertTrue(c1.isCollided(c2));
        assertFalse(c1.isCollided(c3));
        assertTrue(c1.isCollided(c4));
    }
}