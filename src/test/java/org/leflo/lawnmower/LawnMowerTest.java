package org.leflo.lawnmower;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LawnMowerTest {

    /**
     * Tests {@link LawnMower#move()} with all directions
     */
    @Test
    public void testMoveGeneric(){
        for(Direction direction : Direction.values()){
            LawnMower lawnMower = new LawnMower(5, 5, direction);
            lawnMower.move();
            Assertions.assertEquals(5 + direction.getX(), lawnMower.getX());
            Assertions.assertEquals(5 + direction.getY(), lawnMower.getY());
        }
    }

    /**
     * Tests {@link LawnMower#move()} with north direction
     */
    @Test
    public void testMoveActualNorth(){
        LawnMower lawnMower = new LawnMower(5, 5, Direction.N);
        lawnMower.move();
        Assertions.assertEquals(5, lawnMower.getX());
        Assertions.assertEquals(6, lawnMower.getY());
    }

    /**
     * Tests {@link LawnMower#move()} with east direction
     */
    @Test
    public void testMoveActualEast(){
        LawnMower lawnMower = new LawnMower(5, 5, Direction.E);
        lawnMower.move();
        Assertions.assertEquals(6, lawnMower.getX());
        Assertions.assertEquals(5, lawnMower.getY());
    }

    /**
     * Tests {@link LawnMower#move()} with south direction
     */
    @Test
    public void testMoveActualSouth(){
        LawnMower lawnMower = new LawnMower(5, 5, Direction.S);
        lawnMower.move();
        Assertions.assertEquals(5, lawnMower.getX());
        Assertions.assertEquals(4, lawnMower.getY());
    }

    /**
     * Tests {@link LawnMower#move()} with west direction
     */
    @Test
    public void testMoveActualWest(){
        LawnMower lawnMower = new LawnMower(5, 5, Direction.W);
        lawnMower.move();
        Assertions.assertEquals(4, lawnMower.getX());
        Assertions.assertEquals(5, lawnMower.getY());
    }

    /**
     * Tests {@link LawnMower#turnLeft()} with all directions
     */
    @Test
    public void testTurnLeftGeneric(){
        for(Direction direction : Direction.values()){
            LawnMower lawnMower = new LawnMower(5, 5, direction);
            lawnMower.turnLeft();
            Assertions.assertEquals(direction.turnLeft(), lawnMower.getDirection());
        }
    }

    /**
     * Tests {@link LawnMower#turnRight()} with all directions
     */
    @Test
    public void testTurnRightGeneric(){
        for(Direction direction : Direction.values()){
            LawnMower lawnMower = new LawnMower(5, 5, direction);
            lawnMower.turnRight();
            Assertions.assertEquals(direction.turnRight(), lawnMower.getDirection());
        }
    }

}
