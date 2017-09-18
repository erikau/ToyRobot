package com.toyrobot.models;

public interface TableInterface {

    public void placeRobot(int xCoordinate, int yCoordinate, DirectionEnum facingDirection);

    public void moveRobot();

    public void turnRobotRight();

    public void turnRobotLeft();

    public String reportRobot();
}
