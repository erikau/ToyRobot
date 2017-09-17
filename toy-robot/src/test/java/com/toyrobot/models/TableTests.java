package com.toyrobot.models;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TableTests {

    @Test
    public void placeRobotWithinBoundariesTest(){
        Table table = new Table();
        Robot robot = table.placeRobot(0, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(true));
        assertThat(robot.reportLocation(), is("0,0,NORTH"));
    }

    @Test
    public void placeRobotWithNegativeXCoordinateTest(){
        Table table = new Table();
        table.placeRobot(-1, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(false));
    }

    @Test
    public void placeRobotWithNegativeYCoordinateTest(){
        Table table = new Table();
        table.placeRobot(0, -1, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(false));
    }

    @Test
    public void placeRobotWithXCoordinateOutOfBoundaryTest(){
        Table table = new Table();
        table.placeRobot(Table.DEFAULT_TABLE_LENGTH + 1, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(false));
    }

    @Test
    public void placeRobotWithYCoordinateOutOfBoundaryTest(){
        Table table = new Table();
        table.placeRobot(0, Table.DEFAULT_TABLE_WIDTH + 1, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void placeRobotWithNullDirectionTest(){
        Table table = new Table();
        table.placeRobot(0, 0, null);
    }

    @Test
    public void placeRobotARobotAlreadyExistingOnTheTableTest(){
        Table table = new Table();
        Robot firstRobot = table.placeRobot(0, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(true));
        assertThat(firstRobot.reportLocation(), is("0,0,NORTH"));

        Robot secondRobot = table.placeRobot(2, 2, DirectionEnum.WEST);
        assertThat(table.isToyOnTable(), is(true));
        assertThat(secondRobot.reportLocation(), is("2,2,WEST"));
    }

    @Test
    public void moveRobotNorthSingleSquareTest(){
        Table table = new Table();
        Robot robot = table.placeRobot(0, 0, DirectionEnum.NORTH);
        table.move(robot);

        assertThat(table.isToyOnTable(), is(true));
        assertThat(robot.reportLocation(), is("0,1,NORTH"));
    }

    @Test
    public void moveRobotWestOutOfBoundsSoMoveIsIgnoredTest(){
        Table table = new Table();
        Robot robot = table.placeRobot(0, 0, DirectionEnum.WEST);
        table.move(robot);

        assertThat(table.isToyOnTable(), is(true));
        assertThat(robot.reportLocation(), is("0,0,WEST"));
    }

    @Test
    public void moveRobotSouthOutOfBoundsSoMoveIsIgnoredTest(){
        Table table = new Table();
        Robot robot = table.placeRobot(0, 0, DirectionEnum.SOUTH);
        table.move(robot);

        assertThat(table.isToyOnTable(), is(true));
        assertThat(robot.reportLocation(), is("0,0,SOUTH"));
    }

    @Test
    public void moveRobotEastOutOfBoundsSoMoveIsIgnoredTest(){
        Table table = new Table();
        Robot robot = table.placeRobot(Table.DEFAULT_TABLE_LENGTH, 0, DirectionEnum.EAST);
        table.move(robot);

        assertThat(table.isToyOnTable(), is(true));
        assertThat(robot.reportLocation(), is(Table.DEFAULT_TABLE_LENGTH +",0,EAST"));
    }


    @Test
    public void moveRobotNorthOutOfBoundsSoMoveIsIgnoredTest(){
        Table table = new Table();
        Robot robot = table.placeRobot(0, Table.DEFAULT_TABLE_LENGTH, DirectionEnum.NORTH);
        table.move(robot);

        assertThat(table.isToyOnTable(), is(true));
        assertThat(robot.reportLocation(), is("0,"+ Table.DEFAULT_TABLE_LENGTH + ",NORTH"));
    }

    @Test
    public void moveRobotWhereRobotIsNotPlacedOnTableTest(){
        Table table = new Table();
        Robot robot = new Robot(0, 0, DirectionEnum.NORTH);
        table.move(robot);

        assertThat(table.isToyOnTable(), is(false));
        assertThat(robot.reportLocation(), is("0,0,NORTH"));
    }

    @Test(expected = NullPointerException.class)
    public void moveRobotWhereRobotIsNullTest(){
        Table table = new Table();
        table.move(null);
    }

    @Test
    public void isToyOnTableAfterPlaceTest(){
        Table table = new Table();
        table.placeRobot(0, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(true));
    }

    @Test
    public void isToyOnTableBeforePlaceTest(){
        Table table = new Table();
        assertThat(table.isToyOnTable(), is(false));
    }
}
