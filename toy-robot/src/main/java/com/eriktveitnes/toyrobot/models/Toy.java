package com.eriktveitnes.toyrobot.models;

/**
 * The toy is an object that has been placed on a table.
 * It is represented by an X and Y coordinate.
 */
public class Toy {

    /**
     * The current location of the toy represented by an X coordinate
     */
    protected int xCoordinate;
    /**
     * The current location of the toy represented by a Y coordinate
     */
    protected int yCoordinate;

    public Toy(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
