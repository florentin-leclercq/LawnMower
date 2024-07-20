package org.leflo.lawnmower;

/**
 * Represents a lawn mower
 */
public class LawnMower {

    private Direction direction;

    private int x;
    private int y;

    public LawnMower(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * Move the lawn mower in the direction it is facing
     */
    public void move(){
        this.x += this.direction.getX();
        this.y += this.direction.getY();
    }

    /**
     * Turn the lawn mower to the left
     */
    public void turnLeft(){
        this.direction = this.direction.turnLeft();
    }

    /**
     * Turn the lawn mower to the right
     */
    public void turnRight(){
        this.direction = this.direction.turnRight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
