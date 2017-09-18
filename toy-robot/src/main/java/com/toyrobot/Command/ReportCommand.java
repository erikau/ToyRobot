package com.toyrobot.Command;

import com.toyrobot.models.TableInterface;

public class ReportCommand implements Command {

    private final TableInterface table;

    public ReportCommand(TableInterface table){
        this.table = table;
    }

    @Override
    public void execute() {
        table.reportRobot();
    }
}
