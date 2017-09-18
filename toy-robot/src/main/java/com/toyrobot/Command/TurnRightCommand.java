package com.toyrobot.Command;

import com.toyrobot.models.TableInterface;

public class TurnRightCommand  implements Command{

    private final TableInterface table;

    public TurnRightCommand(TableInterface table){
        this.table = table;
    }

    @Override
    public void execute() {
        table.turnRobotRight();
    }
}
