package com.toyrobot.models;

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
public class Table {

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
     * one initialised in its place.
     * @param xCoordinate The X Coordinate the toy should be placed on
     * @param yCoordinate The Y Coordinate the toy should be placed on
     * @param facingDirection The direction the toy should be initially facing
     */
    public Robot placeRobot(int xCoordinate, int yCoordinate, DirectionEnum facingDirection){

        //remove existing toy robot from table if found.

        //initialise new toy at placed coordinate

        //add new toy to toys list as it is now on the table

        return null;
    }

    /**
     * Move the toy a single position ensuring it is a valid move.
     * @param toy The toy which should be moved along the table
     */
    public void move(Toy toy) {

        //validate if the movement will cause an invalid placement.
        if(isToyOnTable() && isValidMove(toy)){
            //move the toy in its current facing direction

        }
        //handle invalid move

    }

    /**
     * Determine if the move command of the toy would be a valid move.
     * An invalid move would be one that results in the toy being moved outside the bounds of the table.
     * @param toy The toy that should be checked if a move command is a valid command
     * @return True if the move command results in a valid move, otherwise false
     */
    private boolean isValidMove(Toy toy) {
        //implement validation

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
