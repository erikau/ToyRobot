# Toy Robot
A simple toy robot simulator that reads inputs from a CSV file and issues the commands.
    See PROBLEM.md for further details


Input file Format:
    1) Each command must be on a new line in the file
    2) Commands are case sensitive and must all be in capital
    3) Place Command must be in the format - 'KEYWORD XCoordinate,YCoordinate,Direction'
        Where Keyword     = PLACE
              XCoordinate = a positive integer along the X axis of the table
              YCoordinate = a positive integer along the Y axis of the table
              Direction   = The Direction must be either NORTH, SOUTH, EAST or WEST
    E.g. PLACE 3,2,WEST

Gradle Tasks:
    This project has been built using Gradle. For more information on gradle see: https://gradle.org/

    To build the application:
        gradle build

    To run the application with the default input file input.csv in /resources:
        gradle run

    To Run the application with your own file:
        gradle run -PappArgs="['YOUR_FILE_NAME']"
    where YOUR_FILE_NAME is the csv file name including extension that you want to run.  E.g. input.csv


Existing Samples:
    There are 3 available sample files included with the project.
    To run the first:
        gradle run -PappArgs="['input.csv']"
    Expected Output:
        :toy-robot:compileJava UP-TO-DATE
        :toy-robot:processResources UP-TO-DATE
        :toy-robot:classes UP-TO-DATE
        :toy-robot:run
        2017-09-18 13:00:22 INFO  Table:145 - 3,3,NORTH

    To run the second:
        gradle run -PappArgs="['input2.csv']"
    Expected Output:
        :toy-robot:compileJava UP-TO-DATE
        :toy-robot:processResources UP-TO-DATE
        :toy-robot:classes UP-TO-DATE
        :toy-robot:run
        2017-09-18 13:01:26 INFO  Table:145 - 5,0,EAST
        2017-09-18 13:01:26 INFO  Table:145 - 5,2,NORTH
        2017-09-18 13:01:26 INFO  Table:145 - 4,2,WEST
        2017-09-18 13:01:26 INFO  Table:145 - 4,2,NORTH

    To run the third:
        gradle run -PappArgs="['input3.csv']"
    Expected Output:
        :toy-robot:compileJava UP-TO-DATE
        :toy-robot:processResources UP-TO-DATE
        :toy-robot:classes UP-TO-DATE
        :toy-robot:run
        2017-09-18 13:02:10 INFO  Table:145 - 1,2,WEST



Assumptions:
    1)  Any malformed data in the csv file will be ignored.
    2)  The Supported commands do not take affect until a robot has been placed on the table.
    3)  A new Place command will remove the existing robot from the table and create a new table.
    4)  Only 1 robot may exist on the table at a given time.