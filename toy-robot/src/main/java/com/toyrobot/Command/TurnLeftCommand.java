package com.toyrobot.Command;

import com.toyrobot.models.TableInterface;

public class TurnLeftCommand implements Command{

    private final TableInterface table;

    public TurnLeftCommand(TableInterface table){
        this.table = table;
    }

    @Override
    public void execute() {
        table.turnRobotLeft();
    }
}