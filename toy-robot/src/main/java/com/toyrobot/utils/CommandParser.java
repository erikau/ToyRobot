package com.toyrobot.utils;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.toyrobot.Command.*;
import com.toyrobot.Exception.UnableToParseCommandException;
import com.toyrobot.models.CommandEnum;
import com.toyrobot.models.DirectionEnum;
import com.toyrobot.models.TableInterface;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

public class CommandParser {

	private static final Logger LOGGER = Logger.getLogger(CommandParser.class);
	protected static final String DEFAULT_FILE_NAME = "input.csv";

	/**
	 * Gets the Commands from a given file.
	 * If the file name is not provided the default file will be used.
	 *
	 * All files must exist in the srv/main/resources directory.
	 *
	 * @param args The file name to be used must be the first element in the array. ALl other values will be ignored
	 * @param table The table that the commands relate to
	 * @return A list of commands associated with the specified table
	 */
	public static List<Command> getCommandsFromFileOrDefault(String[] args, TableInterface table){

		String inputFileName = args != null && args.length > 0 ? args[0] : null;

		List<String[]> fileContents = CommandParser.parseFile(CommandParser.getFilePathOrDefault(inputFileName));
		List<Command> commands = new ArrayList<>();
		for (String[] line : fileContents){
			if(line.length ==3){
				try {
					commands.add(CommandParser.parsePlaceCommand(line, table));
				} catch (UnableToParseCommandException e){
					//ignore
				}
			} else if(line.length ==1){
				try {
					commands.add(CommandParser.parseCommand(line, table));
				} catch (UnableToParseCommandException e){
					//ignore
				}
			}
		}

		return commands;
	}

	/**
	 * Gets the File Path for a given filename.
	 * If the Filename is null or an empty string then the default file will be used.
	 * @param fileName The file name which exists in the project.
	 * @return The File path including the file name of the file.
	 */
	protected static String getFilePathOrDefault(String fileName) {

		fileName = StringUtils.isNotEmpty(fileName) ? fileName : DEFAULT_FILE_NAME;
		try {
			URL resource = CommandParser.class.getResource("/" + fileName);
			return Paths.get(resource.toURI()).toFile().getAbsolutePath();
		} catch (Exception e) {
			LOGGER.error("File:" + fileName + " could not be loaded. Please check the filename is correct and that the file exists in the resources folder of the project.");
			throw new RuntimeException("File:" + fileName + " could not be loaded.");
		}
	}

	/**
	 * This will parse the supplied CSV file and convert each line into a Command.
	 * Any line that can not be recognised will be ignored and a warning will be logged.
	 *
	 * @param filePath The path to the CSV file
	 * @return A list of Commands representing the contents of the file
	 */
	protected static List<String[]> parseFile(String filePath) {
		checkNotNull(filePath);

		List<String[]> fileContents = new ArrayList<>();
		CSVIterator iterator;
		try {
			iterator = new CSVIterator(new CSVReader(new FileReader(filePath)));
		} catch (IOException e) {
			LOGGER.error("Failed to read file:" + filePath, e);
			throw new RuntimeException(e);
		}

		for (; iterator.hasNext(); ) {

			String[] line = iterator.next();
			if(line.length == 1 || line.length == 3) {
				fileContents.add(line);
			} else {
				//ignore as unrecognised length
				LOGGER.warn("Ignored command: '" + Arrays.toString(line) + "' as it is in the wrong structure");
			}
		}

		return fileContents;
	}

	/**
	 * Will parse the string array representing the parts of the csv file line and
	 * convert the line into a PLACE command.
	 *
	 * The Line is assumed to be in the format of Line length 3
	 * 	line[0] = Place command + X Coordinate
	 * 	line[1] = Y Coordinate
	 * 	line[2] = Direction
	 *
	 * 	Any other format will be rejected
	 * @param line The line input from the input csv file
	 * @param table The table that the command relates to
	 * @return The Place Command for the table
	 * @throws UnableToParseCommandException If the line can not be parsed
	 */
	protected static PlaceCommand parsePlaceCommand(String[] line, TableInterface table) throws UnableToParseCommandException {

		int xCoordinate;
		int yCoordinate;
		DirectionEnum direction;

		try {
			checkNotNull(line);
			checkNotNull(table);
			checkArgument(line.length == 3, "The line is the wrong length",  (Object[]) line);

			//trim all elements in the line
			line = Arrays.asList(line).stream().map(String::trim).collect(Collectors.toList()).toArray(new String[3]);

			checkArgument(line[0].contains(CommandEnum.PLACE.name()), "The line does not represent a Place command", line[0]);

			//Split the Command from the X Coordinate
			String[] commandAndXCoordinate = line[0].split(" ");
			checkArgument(commandAndXCoordinate.length == 2,"The Place and XCoordinate are in an unrecognised format");
			checkArgument(commandAndXCoordinate[0].equals(CommandEnum.PLACE.name()), "The command component does not represent a Place command", line[0]);

			//Validate X and Y Coordinate
			try {
				xCoordinate = Integer.parseInt(commandAndXCoordinate[1]);
				yCoordinate = Integer.parseInt(line[1]);
			} catch (NumberFormatException nfe) {
				String message = "The Line: '" + Arrays.toString(line) + "' contained invalid X and Y coordinates";
				throw new UnableToParseCommandException(message, nfe);
			}

			//Validate Direction
			try {
				direction = DirectionEnum.valueOf(line[2]);
			} catch (IllegalArgumentException iae) {
				String message = "The Line: '" + Arrays.toString(line) + "' contained an unrecognised direction";
				throw new UnableToParseCommandException(message, iae);
			}

		} catch (Exception e){
			LOGGER.warn(e.getMessage(), e);
			throw new UnableToParseCommandException("Unable to Parse Command", e);
		}

		return new PlaceCommand(table, xCoordinate, yCoordinate, direction);
	}

	/**
	 * Will parse the string array representing the csv file line and convert into into
	 * the appropriate Command.
	 *
	 * @param line The line input from the input csv file
	 * @param table The table that the command relates to
	 * @return The command for the table
	 * @throws UnableToParseCommandException If the line can not be parsed
	 */
	protected static Command parseCommand(String[] line, TableInterface table) throws UnableToParseCommandException {

		try{
			checkNotNull(line);
			checkNotNull(table);
			checkArgument(line.length == 1, "The line is the wrong length", (Object[]) line);

			//Trim the value
			String input = line[0].trim();

			switch (CommandEnum.valueOf(input)){
				case MOVE:
					return new MoveCommand(table);
				case LEFT:
					return new TurnLeftCommand(table);
				case RIGHT:
					return new TurnRightCommand(table);
				case REPORT:
					return new ReportCommand(table);
				case PLACE:
					String message = "The place command should be parsed using the parsePlaceCommand method.";
					LOGGER.error(message);
					throw new UnableToParseCommandException(message);
				default:
					throw new IllegalArgumentException();
			}
		} catch (Exception e) {
			LOGGER.warn(e.getMessage(), e);
			throw new UnableToParseCommandException(e.getMessage(), e);
		}
	}

}
