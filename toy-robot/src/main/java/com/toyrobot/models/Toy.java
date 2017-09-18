package com.toyrobot.models;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
        checkArgument(xCoordinate >= 0, "must be positive: %s", xCoordinate);
        checkArgument(yCoordinate >= 0, "must be positive: %s", yCoordinate);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setCoordinates(int xCoordinate, int yCoordinate){
        checkArgument(xCoordinate >= 0, "must be positive: %s", xCoordinate);
        checkArgument(yCoordinate >= 0, "must be positive: %s", yCoordinate);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

}
