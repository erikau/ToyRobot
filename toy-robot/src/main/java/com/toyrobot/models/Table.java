package com.toyrobot.models;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *   A table is defined as a rectangle with a calculated area of tableLength * tableWidth.
 *
 *   A position within the table is represented as an X and Y coordinate where (0,0) is the
 *   first position on the grid.
 *
 *   It has the notion of a direction similar to that of a compass where the (0,0)
 *   of the table can be considered the SW corner.
 */
public class Table implements TableInterface {

    private static final Logger LOGGER = Logger.getLogger(Table.class);

    /**
     * The length of the table.
     */
    private int tableLength;
    /**
     * The width of the table
     */
    private int tableWidth;

    /**
     * A list containing all current toys placed on the table
     */
    private List<Toy> currentToysOnTheTable;

    /**
     * The robot that is present on the table.
     */
    private Robot robot;

    /**
     * Default values
     */
    protected static final int DEFAULT_TABLE_LENGTH = 5;
    protected static final int DEFAULT_TABLE_WIDTH = 5;

    /**
     * Default constructor
     */
    public Table() {
        tableLength = DEFAULT_TABLE_LENGTH;
        tableWidth = DEFAULT_TABLE_WIDTH;
        currentToysOnTheTable = new ArrayList<>();
    }

    /**
     * The place command to initialise a new Toy and place it on the table.
     *
     * If an existing robot is already on the table, it should be removed and a new
     * one initialised in the new location.
     * @param xCoordinate The X Coordinate the toy should be placed on
     * @param yCoordinate The Y Coordinate the toy should be placed on
     * @param facingDirection The direction the toy should be initially facing
     */
    @Override
    public void placeRobot(int xCoordinate, int yCoordinate, DirectionEnum facingDirection){

        //validate
        if (!isValidToyPlacement(xCoordinate, yCoordinate) || facingDirection == null) {
            return;
        }

        //remove existing toy robot from table if found.
        if (isToyOnTable()) {
            currentToysOnTheTable.clear();
        }

        //initialise new toy at placed coordinate
        robot = new Robot(xCoordinate, yCoordinate, facingDirection);

        //add new toy to toys list as it is now on the table
        currentToysOnTheTable.add(robot);

    }



    /**
     * Move the robot a single position ensuring it is a valid move.
     *
     * If the move action results in an invalid table placement,
     * then it will be ignored`
     */
    @Override
    public void moveRobot() {

        if(!isToyOnTable()){
            return;
        }

        int xCoordinate = robot.getxCoordinate();
        int yCoordinate = robot.getyCoordinate();
        //calculate new coordinates
        switch(robot.getCurrentFacingDirection()){
            case NORTH:
                yCoordinate++;
                break;
            case EAST:
                xCoordinate++;
                break;
            case SOUTH:
                yCoordinate--;
                break;
            case WEST:
                xCoordinate--;
                break;
        }

        //validate if the movement will cause an invalid placement.
        if(!isValidToyPlacement(xCoordinate, yCoordinate)){
            return;
        }

        //move the toy in its current facing direction
        robot.setCoordinates(xCoordinate, yCoordinate);
    }

    @Override
    public void turnRobotRight() {
        if(isToyOnTable()) {
            robot.turnRight();
        }
    }

    @Override
    public void turnRobotLeft() {
        if(isToyOnTable()) {
            robot.turnLeft();
        }
    }

    @Override
    public String reportRobot() {
        if(isToyOnTable()) {
            String report = robot.reportLocation();
            LOGGER.info(report);
            return report;
        }
        return null;
    }


    /**
     * Determine if the coordinates provided result in a valid toy placement
     * An invalid move would be one that results in the toy being moved outside the bounds of the table.
     * @param xCoordinate The X Coordinate the toy should be placed on
     * @param yCoordinate The Y Coordinate the toy should be placed on
     * @return True if the move command results in a valid move, otherwise false
     */
    private boolean isValidToyPlacement(int xCoordinate, int yCoordinate){

        if(xCoordinate < 0 || xCoordinate > tableWidth){
            LOGGER.debug("Invalid X Coordinate placement of toy:" + xCoordinate);
            return false;
        }
        if(yCoordinate < 0 || yCoordinate > tableLength){
            LOGGER.debug("Invalid Y Coordinate placement of toy:" + yCoordinate);
            return false;
        }

        return true;
    }

    /**
     * Determine if the toy is already only the table.
     * @return True if the toy is already on the table, otherwise false
     */
    public boolean isToyOnTable() {
        return !currentToysOnTheTable.isEmpty();
    }
}
