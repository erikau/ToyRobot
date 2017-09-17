package com.toyrobot.utils;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.toyrobot.models.Command;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class CsvParser {

	private static final Logger LOGGER = Logger.getLogger(CsvParser.class);
	protected static final String DEFAULT_FILE_NAME = "input.csv";

	/**
	 * Gets the File Path for a given filename.
	 * If the Filename is null or an empty string then the default file will be used.
	 * @param fileName The file name which exists in the project.
	 * @return The File path including the file name of the file.
	 */
	public static String getFilePathOrDefault(String fileName) {

		fileName = StringUtils.isNotEmpty(fileName) ? fileName : DEFAULT_FILE_NAME;
		try {
			URL resource = CsvParser.class.getResource("/" + fileName);
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
	public static List<Command> parseFile(String filePath) {
		checkNotNull(filePath);

		List<Command> commandsList = new ArrayList<>();
		CSVIterator iterator;
		try {
			iterator = new CSVIterator(new CSVReader(new FileReader(filePath)));
		} catch (IOException e) {
			LOGGER.error("Failed to read file:" + filePath, e);
			throw new RuntimeException(e);
		}

		for (; iterator.hasNext(); ) {

			String[] line = iterator.next();
			if(line.length == 3) {
				//Handle place command
				parsePlaceCommand(line, commandsList);
			} else if(line.length == 1 ){
				//Handle other command
				parseCommand(line, commandsList);
			} else {
				//ignore as unrecognised
				LOGGER.warn("Ignored command: '" + Arrays.toString(line) + "' as it is in the wrong structure");
			}
		}

		return commandsList;
	}

	/**
	 * Will parse the string array representing the parts of the csv file line and
	 * convert the line into a PLACE command. The command is then attached to the commandList.
	 *
	 * The Line is assumed to be in the format of Line length 3
	 * 	line[0] = Place command + X Coordinate
	 * 	line[1] = Y Coordinate
	 * 	line[2] = Direction
	 *
	 * 	Any other format will be rejected and the commandsList will remain un changed.
	 * @param line The line input from the input csv file
	 * @param commandsList The list of commands that the command should be added to
	 */
	protected static void parsePlaceCommand(String[] line, List<Command> commandsList) {
		//TODO Implement

	}

	/**
	 * Will parse the string array representing the csv file line and convert into into
	 * the appropriate Command. The command is then attached to the commandList.
	 *
	 * If the command can not be recognised the commandsList will remain un changed.
	 * @param line The line input from the input csv file
	 * @param commandsList The list of commands that the command should be added to
	 */
	protected static void parseCommand(String[] line, List<Command> commandsList) {
		//TODO Implement

	}

}
