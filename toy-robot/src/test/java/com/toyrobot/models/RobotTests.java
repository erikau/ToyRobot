package com.toyrobot.models;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RobotTests {

    @Test(expected = IllegalArgumentException.class)
    public void createNewRobotWithNegativeXCoordinateTest(){
        Robot robot = new Robot(-1,0, DirectionEnum.NORTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNewRobotWithNegativeYCoordinateTest(){
        Robot robot = new Robot(0,-1, DirectionEnum.NORTH);
    }

    @Test(expected = NullPointerException.class)
    public void createNewRobotNullDirectionFacingTest(){
        Robot robot = new Robot(0,0, null);
    }

    @Test
    public void turnLeftFromNorthFacingDirectionTest() {
        Robot robot = new Robot(0,0, DirectionEnum.NORTH);
        robot.turnLeft();
        assertThat(robot.getCurrentFacingDirection(), is(DirectionEnum.WEST));
    }

    @Test
    public void turnLeftTwiceFromNorthFacingDirectionTest() {
        Robot robot = new Robot(0,0, DirectionEnum.NORTH);
        robot.turnLeft();
        robot.turnLeft();
        assertThat(robot.getCurrentFacingDirection(), is(DirectionEnum.SOUTH));
    }

    @Test
    public void turnRightFromNorthFacingDirectionTest() {
        Robot robot = new Robot(0,0, DirectionEnum.NORTH);
        robot.turnRight();
        assertThat(robot.getCurrentFacingDirection(), is(DirectionEnum.EAST));
    }

    @Test
    public void turnRightTwiceFromNorthFacingDirectionTest() {
        Robot robot = new Robot(0,0, DirectionEnum.NORTH);
        robot.turnRight();
        robot.turnRight();
        assertThat(robot.getCurrentFacingDirection(), is(DirectionEnum.SOUTH));
    }

    @Test
    public void reportLocationAfterPlacement() {
        Robot robot = new Robot(0,0, DirectionEnum.NORTH);
        assertThat(robot.reportLocation(), is("0,0,NORTH"));
    }

}
