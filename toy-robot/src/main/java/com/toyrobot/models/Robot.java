package com.toyrobot.models;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Robot is a kind of toy that a direction associated with it.
 * It is able to move along a board in the direction it is facing.
 * It is also able to change its direction by turning left or right.
 */
public class Robot extends Toy {

    /**
     * The current facing direction is represented as an integer of range 0 to 3
     * Where 0 is North
     *       1 is East
     *       2 is South
     *       3 is West
     */
    private DirectionEnum currentFacingDirection;


    public Robot(int xCoordinate, int yCoordinate, DirectionEnum currentFacingDirection) {
        super(xCoordinate, yCoordinate);

        checkNotNull(currentFacingDirection, "currentFacingDirection can not be null");
        this.currentFacingDirection = currentFacingDirection;
    }


    public DirectionEnum getCurrentFacingDirection() {
        return currentFacingDirection;
    }

    /**
     * To change the current facing direction left is to turn 90 degrees to the left from the
     * current facing direction.
     * E.g. if the robot is facing North(0) then turning left would result in
     * the robots currentFacingDirection set at West(3).
     */
    public void turnLeft(){

        int direction = currentFacingDirection.ordinal();
        if(direction == 0){
            currentFacingDirection = DirectionEnum.fromInt(3);
        } else{
            direction = direction - 1;
            currentFacingDirection = DirectionEnum.fromInt(direction);
        }
    }

    /**
     * To change the current facing direction right is to turn 90 degrees to the right from the
     * current facing direction.
     * E.g. if the robot is facing North(0) then turning right would result in
     * the robots currentFacingDirection set at East(1).
     */
    public void turnRight(){
        int direction = currentFacingDirection.ordinal();
        if(direction == 3) {
            currentFacingDirection = DirectionEnum.fromInt(0);
        } else{
            direction = direction + 1;
            currentFacingDirection = DirectionEnum.fromInt(direction);
        }
    }


    /**
     * Provide a string representation of the robots current location and direction.
     * @return A string in the format XCoordinate,YCoordinate,CurrentFacingDirection
     */
    public String reportLocation(){
        return  String.format("%d,%d,%s", xCoordinate, yCoordinate, currentFacingDirection);
    }

}
