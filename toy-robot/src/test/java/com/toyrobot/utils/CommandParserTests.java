package com.toyrobot.utils;

import com.toyrobot.Command.*;
import com.toyrobot.Exception.UnableToParseCommandException;
import com.toyrobot.models.Table;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommandParserTests {

    @Test
    public void getCommandsFromFileOrDefaultWithTestFileInputTest(){
        Table table = new Table();
        List<Command> commands = CommandParser.getCommandsFromFileOrDefault(new String[]{"test-input.csv"}, table);
        assertThat(commands.size(), is(7));
        assertThat(commands, contains(instanceOf(PlaceCommand.class),
                                      instanceOf(MoveCommand.class),
                                      instanceOf(TurnRightCommand.class),
                                      instanceOf(TurnLeftCommand.class),
                                      instanceOf(ReportCommand.class),
                                      instanceOf(PlaceCommand.class),
                                      instanceOf(PlaceCommand.class)
        ));
    }

    @Test(expected = NullPointerException.class)
    public void parseFileWithNullFilePathFile() {
        CommandParser.parseFile(null);
    }

    @Test(expected = RuntimeException.class)
    public void parseFileWithEmptyFilePathFile() {
        CommandParser.parseFile(null);
    }

    @Test
    public void getFilePathOrDefaultWithNullFileNameUsesDefaultValueTest() {
        assertThat(CommandParser.getFilePathOrDefault(null),  containsString(CommandParser.DEFAULT_FILE_NAME));
    }

    @Test
    public void getFilePathOrDefaultWithEmptyFileNameUsesDefaultValueTest() {
        assertThat(CommandParser.getFilePathOrDefault(""),  containsString(CommandParser.DEFAULT_FILE_NAME));
    }



    @Test(expected = RuntimeException.class)
    public void getFilePathOrDefaultWithMissingFileNameTest() {
        CommandParser.getFilePathOrDefault("fakeFileName.fake");
    }

    @Test
    public void getFilePathOrDefaultWithRealFileTest() {
        assertThat(CommandParser.getFilePathOrDefault("input.csv"),  containsString("input.csv"));
    }

    @Test
    public void parsePlaceCommandWithValidInputTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"PLACE 0", "2", "NORTH"};
        PlaceCommand command = CommandParser.parsePlaceCommand(input, table);

        assertThat(command, is(not(nullValue())));
    }

    @Test
    public void parsePlaceCommandWithValidInputAsNegativeCoordinatesTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"PLACE -1", "-3", "SOUTH"};
        PlaceCommand command = CommandParser.parsePlaceCommand(input, table);

        assertThat(command, is(not(nullValue())));
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parsePlaceCommandWithLowerCasePlaceCommandTest()  throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"place 1", "2", "NORTH"};
        CommandParser.parsePlaceCommand(input, table);
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parsePlaceCommandWithMissingXCoordinateTest()  throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"PLACE", "2", "NORTH"};
        CommandParser.parsePlaceCommand(input, table);
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parsePlaceCommandWithYCoordinateAsCharTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"PLACE 0", "A", "NORTH"};
        CommandParser.parsePlaceCommand(input, table);
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parsePlaceCommandWithDirectionAsInvalidWordTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"PLACE 0", "2", "aWord"};
        CommandParser.parsePlaceCommand(input, table);
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parsePlaceCommandWithDirectionAsLowerCaseTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"PLACE 0", "2", "north"};
        CommandParser.parsePlaceCommand(input, table);
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parsePlaceCommandWithNullInputTest()  throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{null, null, null};
        CommandParser.parsePlaceCommand(input, table);
    }

    @Test
    public void parseCommandWithValidMoveInputTest()  throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"MOVE"};
        Command command = CommandParser.parseCommand(input, table);

        assertThat(command, is(not(nullValue())));
        assertThat(command, instanceOf(MoveCommand.class));
    }

    @Test
    public void parseCommandWithValidLeftInputTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"LEFT"};
        Command command = CommandParser.parseCommand(input, table);

        assertThat(command, is(not(nullValue())));
        assertThat(command, instanceOf(TurnLeftCommand.class));
    }

    @Test
    public void parseCommandWithValidRightInputTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"RIGHT"};
        Command command = CommandParser.parseCommand(input, table);

        assertThat(command, is(not(nullValue())));
        assertThat(command, instanceOf(TurnRightCommand.class));
    }

    @Test
    public void parseCommandWithValidReportInputTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{"REPORT"};
        Command command = CommandParser.parseCommand(input, table);

        assertThat(command, is(not(nullValue())));
        assertThat(command, instanceOf(ReportCommand.class));
    }

    @Test
    public void parseCommandWithValidInputIncludingSpaceBeforeAndAfterTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{" REPORT "};
        Command command = CommandParser.parseCommand(input, table);

        assertThat(command, is(not(nullValue())));
        assertThat(command, instanceOf(ReportCommand.class));
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parseCommandWithNullInputTest() throws UnableToParseCommandException {
        Table table = new Table();
        String[] input = new String[]{null};
        CommandParser.parseCommand(input, table);
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parseCommandWithInValidInputUnRecognisedCommandTest() throws UnableToParseCommandException  {
        Table table = new Table();
        String[] input = new String[]{"BLAH"};
        CommandParser.parseCommand(input, table);
    }

    @Test (expected = UnableToParseCommandException.class)
    public void parseCommandWithInValidInputLowercaseCommandTest() throws UnableToParseCommandException  {
        Table table = new Table();
        String[] input = new String[]{"report"};
        CommandParser.parseCommand(input, table);
    }
}
