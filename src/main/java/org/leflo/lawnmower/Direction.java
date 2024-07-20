package org.leflo.lawnmower;

/**
 * Represents the cardinal directions.
 */
public enum Direction {

    N(0, 1),
    E(1, 0),
    S(0, -1),
    W(-1, 0);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Turn left from the current direction.
     * @return the new direction after turning left.
     */
    public Direction turnLeft() {
        // We take advantage of the cyclical nature of cardinal directions
        // and the fact that the values are ordered in the same order as the directions.
        return Direction.values()[(this.ordinal() + 3) % 4];
    }

    /**
     * Turn right from the current direction.
     * @return the new direction after turning right.
     */
    public Direction turnRight() {
        // We take advantage of the cyclical nature of cardinal directions
        // and the fact that the values are ordered in the same order as the directions.
        return Direction.values()[(this.ordinal() + 1) % 4];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
