package org.leflo.lawnmower;

import java.util.ArrayList;
import java.util.List;

public class Lawn {

    private int width;
    private int height;

    private List<LawnMower> lawnMowers = new ArrayList<>();

    public Lawn(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void addLawnMower(LawnMower lawnMower){
        lawnMowers.add(lawnMower);
    }

    public boolean canLawnMowerMove(LawnMower lawnMower){
        return isInside(lawnMower.getX() + lawnMower.getDirection().getX(), lawnMower.getY() + lawnMower.getDirection().getY());
    }

    public List<LawnMower> getLawnMowers() {
        return lawnMowers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private boolean isInside(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
