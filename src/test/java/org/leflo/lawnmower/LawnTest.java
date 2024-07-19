package org.leflo.lawnmower;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LawnTest {

    @Test
    public void testCanLawnMowerMoveIsTrue(){
        Lawn lawn = new Lawn(5, 5);
        LawnMower lawnMower = new LawnMower(1, 1, Direction.N);
        Assertions.assertTrue(lawn.canLawnMowerMove(lawnMower));
    }

    @Test
    public void testCanLawnMowerMoveIsFalseNorth(){
        Lawn lawn = new Lawn(5, 5);
        LawnMower lawnMower = new LawnMower(4, 4, Direction.N);
        Assertions.assertFalse(lawn.canLawnMowerMove(lawnMower));
    }

    @Test
    public void testCanLawnMowerMoveIsFalseSouth(){
        Lawn lawn = new Lawn(5, 5);
        LawnMower lawnMower = new LawnMower(5, 0, Direction.S);
        Assertions.assertFalse(lawn.canLawnMowerMove(lawnMower));
    }

    @Test
    public void testCanLawnMowerMoveIsFalseEast(){
        Lawn lawn = new Lawn(5, 5);
        LawnMower lawnMower = new LawnMower(5, 5, Direction.E);
        Assertions.assertFalse(lawn.canLawnMowerMove(lawnMower));
    }

    @Test
    public void testCanLawnMowerMoveIsFalseWest(){
        Lawn lawn = new Lawn(5, 5);
        LawnMower lawnMower = new LawnMower(0, 5, Direction.W);
        Assertions.assertFalse(lawn.canLawnMowerMove(lawnMower));
    }

}
