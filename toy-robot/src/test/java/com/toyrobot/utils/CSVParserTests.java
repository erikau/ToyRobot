package com.toyrobot.utils;

import com.opencsv.CSVParser;
import com.toyrobot.models.Command;
import com.toyrobot.models.DirectionEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CSVParserTests {

    @Test
    public void parseFileUsingTestInputFile(){
        String fileName = CsvParser.getFilePathOrDefault("test-input.csv");
        List<Command> commands = CsvParser.parseFile(fileName);
        assertThat(commands.size(), is(6));
        assertThat(commands, contains(
                allOf(hasProperty("command", is("PLACE")),
                        hasProperty("xCoordinatePlacement", is(1)),
                        hasProperty("yCoordinatePlacement", is(2)),
                        hasProperty("directionPlacement", is(DirectionEnum.EAST))),
                allOf(hasProperty("command", is("MOVE")),
                        hasProperty("xCoordinatePlacement", is(nullValue())),
                        hasProperty("yCoordinatePlacement", is(nullValue())),
                        hasProperty("directionPlacement", is(nullValue()))),
                allOf(hasProperty("command", is("RIGHT")),
                        hasProperty("xCoordinatePlacement", is(nullValue())),
                        hasProperty("yCoordinatePlacement", is(nullValue())),
                        hasProperty("directionPlacement", is(nullValue()))),
                allOf(hasProperty("command", is("LEFT")),
                        hasProperty("xCoordinatePlacement", is(nullValue())),
                        hasProperty("yCoordinatePlacement", is(nullValue())),
                        hasProperty("directionPlacement", is(nullValue()))),
                allOf(hasProperty("command", is("REPORT")),
                        hasProperty("xCoordinatePlacement", is(nullValue())),
                        hasProperty("yCoordinatePlacement", is(nullValue())),
                        hasProperty("directionPlacement", is(nullValue()))),
                allOf(hasProperty("command", is("PLACE")),
                        hasProperty("xCoordinatePlacement", is(-1)),
                        hasProperty("yCoordinatePlacement", is(10)),
                        hasProperty("directionPlacement", is(DirectionEnum.NORTH))),
                allOf(hasProperty("command", is("PLACE")),
                        hasProperty("xCoordinatePlacement", is(2)),
                        hasProperty("yCoordinatePlacement", is(2)),
                        hasProperty("directionPlacement", is(DirectionEnum.SOUTH)))
        ));
    }

    @Test(expected = NullPointerException.class)
    public void parseFileWithNullFilePathFile() {
        CsvParser.parseFile(null);
    }

    @Test(expected = RuntimeException.class)
    public void parseFileWithEmptyFilePathFile() {
        CsvParser.parseFile(null);
    }

    @Test
    public void getFilePathOrDefaultWithNullFileNameUsesDefaultValueTest() {
        assertThat(CsvParser.getFilePathOrDefault(null),  containsString(CsvParser.DEFAULT_FILE_NAME));
    }

    @Test
    public void getFilePathOrDefaultWithEmptyFileNameUsesDefaultValueTest() {
        assertThat(CsvParser.getFilePathOrDefault(""),  containsString(CsvParser.DEFAULT_FILE_NAME));
    }



    @Test(expected = RuntimeException.class)
    public void getFilePathOrDefaultWithMissingFileNameTest() {
        CsvParser.getFilePathOrDefault("fakeFileName.fake");
    }

    @Test
    public void getFilePathOrDefaultWithRealFileTest() {
        assertThat(CsvParser.getFilePathOrDefault("input.csv"),  containsString("input.csv"));
    }

    @Test
    public void parsePlaceCommandWithValidInputTest() {
        String[] input = new String[]{"PLACE 0", "2", "NORTH"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList.size(), is(1));
        assertThat(commandsList, contains(allOf(
                hasProperty("command", is("PLACE")),
                hasProperty("xCoordinatePlacement", is(0)),
                hasProperty("yCoordinatePlacement", is(2)),
                hasProperty("directionPlacement", is(DirectionEnum.NORTH)))
        ));
    }

    @Test
    public void parsePlaceCommandWithValidInputAsNegativeCoordinatesTest() {
        String[] input = new String[]{"PLACE -1", "-3", "SOUTH"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList.size(), is(1));
        assertThat(commandsList, contains(allOf(
                hasProperty("command", is("PLACE")),
                hasProperty("xCoordinatePlacement", is(-1)),
                hasProperty("yCoordinatePlacement", is(-3)),
                hasProperty("directionPlacement", is(DirectionEnum.SOUTH)))
        ));
    }

    @Test
    public void parsePlaceCommandWithLowerCasePlaceCommandTest() {
        String[] input = new String[]{"place 1", "2", "NORTH"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }

    @Test
    public void parsePlaceCommandWithMissingXCoordinateTest() {
        String[] input = new String[]{"PLACE", "2", "NORTH"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }

    @Test
    public void parsePlaceCommandWithYCoordinateAsCharTest() {
        String[] input = new String[]{"PLACE 0", "A", "NORTH"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }

    @Test
    public void parsePlaceCommandWithDirectionAsInvalidWordTest() {
        String[] input = new String[]{"PLACE 0", "2", "aWord"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }

    @Test
    public void parsePlaceCommandWithDirectionAsLowerCaseTest() {
        String[] input = new String[]{"PLACE 0", "2", "north"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }

    @Test
    public void parsePlaceCommandWithNullInputTest() {
        String[] input = new String[]{null, null, null};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }

    @Test
    public void parseCommandWithValidMoveInputTest() {
        String[] input = new String[]{"MOVE"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList.size(), is(1));
        assertThat(commandsList, contains(allOf(
                hasProperty("command", is("MOVE")),
                hasProperty("xCoordinatePlacement", is(nullValue())),
                hasProperty("yCoordinatePlacement", is(nullValue())),
                hasProperty("directionPlacement", is(nullValue())))
        ));
    }

    @Test
    public void parseCommandWithValidLeftInputTest() {
        String[] input = new String[]{"LEFT"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList.size(), is(1));
        assertThat(commandsList, contains(allOf(
                hasProperty("command", is("LEFT")),
                hasProperty("xCoordinatePlacement", is(nullValue())),
                hasProperty("yCoordinatePlacement", is(nullValue())),
                hasProperty("directionPlacement", is(nullValue())))
        ));
    }

    @Test
    public void parseCommandWithValidRightInputTest() {
        String[] input = new String[]{"LEFT"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList.size(), is(1));
        assertThat(commandsList, contains(allOf(
                hasProperty("command", is("RIGHT")),
                hasProperty("xCoordinatePlacement", is(nullValue())),
                hasProperty("yCoordinatePlacement", is(nullValue())),
                hasProperty("directionPlacement", is(nullValue())))
        ));
    }

    @Test
    public void parseCommandWithValidReportInputTest() {
        String[] input = new String[]{"REPORT"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList.size(), is(1));
        assertThat(commandsList, contains(allOf(
                hasProperty("command", is("REPORT")),
                hasProperty("xCoordinatePlacement", is(nullValue())),
                hasProperty("yCoordinatePlacement", is(nullValue())),
                hasProperty("directionPlacement", is(nullValue())))
        ));
    }

    @Test
    public void parseCommandWithValidInputIncludingSpaceBeforeAndAfterTest() {
        String[] input = new String[]{" REPORT "};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList.size(), is(1));
        assertThat(commandsList, contains(allOf(
                hasProperty("command", is("REPORT")),
                hasProperty("xCoordinatePlacement", is(nullValue())),
                hasProperty("yCoordinatePlacement", is(nullValue())),
                hasProperty("directionPlacement", is(nullValue())))
        ));
    }

    @Test
    public void parseCommandWithNullInputTest() {
        String[] input = new String[]{null};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }

    @Test
    public void parseCommandWithInValidInputUnRecognisedCommandTest() {
        String[] input = new String[]{"BLAH"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }

    @Test
    public void parseCommandWithInValidInputLowercaseCommandTest() {
        String[] input = new String[]{"report"};
        List<Command> commandsList = new ArrayList<>();
        CsvParser.parsePlaceCommand(input, commandsList);

        assertThat(commandsList, is(empty()));
    }
}
