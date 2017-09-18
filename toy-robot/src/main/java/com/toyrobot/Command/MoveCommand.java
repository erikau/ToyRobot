package com.toyrobot.Command;

import com.toyrobot.models.TableInterface;

public class MoveCommand implements Command {

    private final TableInterface table;

    public MoveCommand(TableInterface table){
        this.table = table;
    }

    @Override
    public void execute() {
        table.moveRobot();
    }
}
