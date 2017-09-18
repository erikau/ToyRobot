package com.toyrobot;

import com.toyrobot.Command.Command;
import com.toyrobot.models.Table;
import com.toyrobot.utils.CommandParser;

public class ToyRobot {

    /**
     * Main method to initialise the application.
     *
     * File and command parsing is performed by the CommandParser.
     *
     * The application follows a command pattern where each record in the input file is transformed into a
     * command to be executed against the table object.  All outputs are logged via log4j to command line.
     *
     * @param args To use a custom file, include the file name in args[0] including extension. E.g. input.csv
     */
    public static void main(String[] args) {
        Table table = new Table();
        CommandParser.getCommandsFromFileOrDefault(args, table).forEach(Command::execute);
    }

}
