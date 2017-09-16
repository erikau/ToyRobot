package com.eriktveitnes.toyrobot.models;

/**
 * A Robot is a kind of toy that a direction associated with it.
 * It is able to move along a board in the direction it is facing.
 * It is also able to change its direction by turning left or right.
 */
public class Robot extends Toy {

    public Robot(int xCordinate, int yCordinate, int currentFacingDirection) {
        super(xCordinate, yCordinate);
        this.currentFacingDirection = currentFacingDirection;
    }

    /**
     * The current facing direction is represented as an integer of range 0 to 3
     * Where 0 is North
     *       1 is East
     *       2 is South
     *       3 is West
     */
    private int currentFacingDirection;

    /**
     * To change the current facing direction left is to turn 90 degrees to the left from the
     * current facing direction.
     * E.g. if the robot is facing North(0) then turning left would result in
     * the robots currentFacingDirection set at West(3).
     */
    public void turnLeft(){
        //change the direction the robot is facing to the left
        //this will be subtracting by 1 to the facing direction
        //if the current direction is 0 then it should be + 3

    }

    /**
     * To change the current facing direction right is to turn 90 degrees to the right from the
     * current facing direction.
     * E.g. if the robot is facing North(0) then turning right would result in
     * the robots currentFacingDirection set at East(1).
     */
    public void turnRight(){
        //change the direction the robot is facing to the right
        //this will be adding by 1 to the facing direction
        //If the current direction is 3 then it should be -3

    }


    /**
     * Provide a string representation of the robots current location and direction.
     * @return A string in the format XCoordinate,YCoordinate,CurrentFacingDirection
     */
    public String reportLocation(){
        //TODO use a string formatter
        return  xCoordinate + "," + yCoordinate + "," +currentFacingDirection;
    }

}
