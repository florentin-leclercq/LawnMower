package org.leflo.lawnmower;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a lawn where lawn mowers can move
 */
public class Lawn {

    private final int width;
    private final int height;

    private final List<LawnMower> lawnMowers = new ArrayList<>();

    public Lawn(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * Add a lawn mower to the lawn
     * @param lawnMower the lawn mower to add
     */
    public void addLawnMower(LawnMower lawnMower){
        lawnMowers.add(lawnMower);
    }

    /**
     * Check if a lawn mower can move in its current direction
     * @param lawnMower the lawn mower to check
     * @return true if the lawn mower can move, false otherwise
     */
    public boolean canLawnMowerMove(LawnMower lawnMower){
        return isInside(lawnMower.getX() + lawnMower.getDirection().getX(), lawnMower.getY() + lawnMower.getDirection().getY());
    }

    /**
     * Get the list of lawn mowers on the lawn
     * @return a list of lawn mowers
     */
    public List<LawnMower> getLawnMowers() {
        return lawnMowers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Check if a position is inside the lawn
     * @param x the x position
     * @param y the y position
     * @return true if the position is inside the lawn, false otherwise
     */
    private boolean isInside(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
