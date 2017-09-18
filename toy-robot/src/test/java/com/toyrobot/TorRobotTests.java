package com.toyrobot;

import com.toyrobot.Command.Command;
import com.toyrobot.Command.MoveCommand;
import com.toyrobot.Command.PlaceCommand;
import com.toyrobot.Command.ReportCommand;
import com.toyrobot.models.DirectionEnum;
import com.toyrobot.models.Table;
import com.toyrobot.utils.CommandParser;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest( CommandParser.class )
public class TorRobotTests {

    /** Unit under test. */
    private Table table;
    private List<Command> commands;

    @Mock
    AppenderSkeleton appender;
    @Captor
    ArgumentCaptor<LoggingEvent> logCaptor;

    @Before
    public void setUp() {
        table = new Table();
        commands = new ArrayList<>();

        //Mock the CommandParser
        PowerMockito.mockStatic(CommandParser.class);
        PowerMockito.when(CommandParser.getCommandsFromFileOrDefault(any(), any())).thenReturn(commands);
    }

    @Test
    public void ToyRobotMainPlaceRobotAndReportTest(){
        Logger.getRootLogger().addAppender(appender);

        //Test Code
        commands.add(new PlaceCommand(table,0, 0, DirectionEnum.NORTH));
        commands.add(new ReportCommand(table));
        ToyRobot.main(new String[]{"file"});

        verify(appender).doAppend(logCaptor.capture());
        assertThat(logCaptor.getValue().getRenderedMessage(), is("0,0,NORTH"));
    }

    @Test
    public void ToyRobotMainPlaceRobotThenMoveToInvalidPositionAndReportTest(){
        Logger.getRootLogger().addAppender(appender);

        //Test Code
        commands.add(new PlaceCommand(table,0, 0, DirectionEnum.WEST));
        commands.add(new MoveCommand(table));
        commands.add(new ReportCommand(table));
        ToyRobot.main(new String[]{"file"});

        verify(appender).doAppend(logCaptor.capture());
        assertThat(logCaptor.getValue().getRenderedMessage(), is("0,0,WEST"));
    }
}
