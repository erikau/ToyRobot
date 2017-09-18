package com.toyrobot.models;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class TableTests {

    @Test
    public void placeRobotWithinBoundariesTest(){
        Table table = new Table();
        table.placeRobot(0, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(true));
        assertThat(table.reportRobot(), is("0,0,NORTH"));
    }

    @Test
    public void placeRobotWithNegativeXCoordinateTest(){
        Table table = new Table();
        table.placeRobot(-1, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(false));
        assertThat(table.reportRobot(), is(nullValue()));
    }

    @Test
    public void placeRobotWithNegativeYCoordinateTest(){
        Table table = new Table();
        table.placeRobot(0, -1, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(false));
        assertThat(table.reportRobot(), is(nullValue()));
    }

    @Test
    public void placeRobotWithXCoordinateOutOfBoundaryTest(){
        Table table = new Table();
        table.placeRobot(Table.DEFAULT_TABLE_LENGTH + 1, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(false));
        assertThat(table.reportRobot(), is(nullValue()));
    }

    @Test
    public void placeRobotWithYCoordinateOutOfBoundaryTest(){
        Table table = new Table();
        table.placeRobot(0, Table.DEFAULT_TABLE_WIDTH + 1, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(false));
        assertThat(table.reportRobot(), is(nullValue()));
    }

    @Test()
    public void placeRobotWithNullDirectionTest(){
        Table table = new Table();
        table.placeRobot(0, 0, null);
        assertThat(table.isToyOnTable(), is(false));
        assertThat(table.reportRobot(), is(nullValue()));
    }

    @Test
    public void placeRobotARobotAlreadyExistingOnTheTableTest(){
        Table table = new Table();
        table.placeRobot(0, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(true));
        assertThat(table.reportRobot(), is("0,0,NORTH"));

        table.placeRobot(2, 2, DirectionEnum.WEST);
        assertThat(table.isToyOnTable(), is(true));
        assertThat(table.reportRobot(), is("2,2,WEST"));
    }

    @Test
    public void moveRobotNorthSingleSquareTest(){
        Table table = new Table();
        table.placeRobot(0, 0, DirectionEnum.NORTH);
        table.moveRobot();

        assertThat(table.isToyOnTable(), is(true));
        assertThat(table.reportRobot(), is("0,1,NORTH"));
    }

    @Test
    public void moveRobotWestOutOfBoundsSoMoveIsIgnoredTest(){
        Table table = new Table();
        table.placeRobot(0, 0, DirectionEnum.WEST);
        table.moveRobot();

        assertThat(table.isToyOnTable(), is(true));
        assertThat(table.reportRobot(), is("0,0,WEST"));
    }

    @Test
    public void moveRobotSouthOutOfBoundsSoMoveIsIgnoredTest(){
        Table table = new Table();
        table.placeRobot(0, 0, DirectionEnum.SOUTH);
        table.moveRobot();

        assertThat(table.isToyOnTable(), is(true));
        assertThat(table.reportRobot(), is("0,0,SOUTH"));
    }

    @Test
    public void moveRobotEastOutOfBoundsSoMoveIsIgnoredTest(){
        Table table = new Table();
        table.placeRobot(Table.DEFAULT_TABLE_LENGTH, 0, DirectionEnum.EAST);
        table.moveRobot();

        assertThat(table.isToyOnTable(), is(true));
        assertThat(table.reportRobot(), is(Table.DEFAULT_TABLE_LENGTH + ",0,EAST"));
    }


    @Test
    public void moveRobotNorthOutOfBoundsSoMoveIsIgnoredTest(){
        Table table = new Table();
        table.placeRobot(0, Table.DEFAULT_TABLE_LENGTH, DirectionEnum.NORTH);
        table.moveRobot();

        assertThat(table.isToyOnTable(), is(true));
        assertThat(table.reportRobot(), is("0," + Table.DEFAULT_TABLE_LENGTH + ",NORTH"));
    }

    @Test
    public void moveRobotWhereRobotIsNotPlacedOnTableTest(){
        Table table = new Table();
        table.moveRobot();

        assertThat(table.isToyOnTable(), is(false));
        assertThat(table.reportRobot(), is(nullValue()));
    }

    @Test
    public void isToyOnTableAfterPlaceTest() {
        Table table = new Table();
        table.placeRobot(0, 0, DirectionEnum.NORTH);
        assertThat(table.isToyOnTable(), is(true));
    }

    @Test
    public void isToyOnTableBeforePlaceTest() {
        Table table = new Table();
        assertThat(table.isToyOnTable(), is(false));
    }
}
