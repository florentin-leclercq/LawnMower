package org.leflo.lawnmower;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    /**
     * Tests {@link Direction#turnLeft()}.
     */
    @Test
    public void testTurnLeft() {
        Assertions.assertEquals(Direction.W, Direction.N.turnLeft());
        Assertions.assertEquals(Direction.S, Direction.W.turnLeft());
        Assertions.assertEquals(Direction.E, Direction.S.turnLeft());
        Assertions.assertEquals(Direction.N, Direction.E.turnLeft());
    }

    /**
     * Tests {@link Direction#turnRight()}.
     */
    @Test
    public void testTurnRight() {
        Assertions.assertEquals(Direction.E, Direction.N.turnRight());
        Assertions.assertEquals(Direction.S, Direction.E.turnRight());
        Assertions.assertEquals(Direction.W, Direction.S.turnRight());
        Assertions.assertEquals(Direction.N, Direction.W.turnRight());
    }

}
