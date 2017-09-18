package com.toyrobot.Command;


import com.toyrobot.models.DirectionEnum;
import com.toyrobot.models.TableInterface;

public class PlaceCommand implements Command {

    private final TableInterface table;
    private final int xCoordinate;
    private final int yCoordinate;
    private final DirectionEnum direction;

    public PlaceCommand(TableInterface table,
                        int xCoordinate,
                        int yCoordinate,
                        DirectionEnum direction){
        this.table = table;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.direction = direction;
    }

    @Override
    public void execute() {
        table.placeRobot(xCoordinate, yCoordinate, direction);
    }
}
