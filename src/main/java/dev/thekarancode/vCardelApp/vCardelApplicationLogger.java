package dev.thekarancode.vCardelApp;

import dev.thekarancode.logUtilityClasses.Log;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * vCardelApplicationLogger is a class responsible for logging application activities
 * and displaying logs in a JavaFX TextField.
 */
public class vCardelApplicationLogger {
    /**
     * Formatter for date and time.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy@hh:mm:ss");
    /**
     * List of logs.
     */
    private final List<Log> logList;
    /**
     * TextField for displaying logs.
     */
    private final TextField logField;
    /**
     * Unique session identifier.
     */
    private final String sessionId;
    /**
     * Path to the log file.
     */
    private final Path logFilePath;
    /**
     * Index of the currently displayed log.
     */
    private int displayedLogIndex;

    /**
     * Constructs a vCardelApplicationLogger with a specified TextField for displaying logs.
     *
     * @param appLogField The TextField used to display logs.
     */
    public vCardelApplicationLogger(TextField appLogField) {
        this.logList = new ArrayList<>();
        this.logField = appLogField;
        this.displayedLogIndex = -1;
        this.sessionId = UUID.randomUUID() + "-" + LocalDateTime.now().format(formatter);
        this.logFilePath = Paths.get("vCardelAppLog.txt");

        if (!Files.exists(logFilePath)) {
            try {
                Files.createFile(logFilePath);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Logs the start of a session by appending a session start message to the log file.
     */
    public void logSessionStart() {
        try {
            String sessionStartLog = "#############################################################################################################\n" + " ######  ########  ######   ######  ####  #######  ##    ##     ######  ########    ###    ########  ########\n" + "##    ## ##       ##    ## ##    ##  ##  ##     ## ###   ##    ##    ##    ##      ## ##   ##     ##    ##   \n" + "##       ##       ##       ##        ##  ##     ## ####  ##    ##          ##     ##   ##  ##     ##    ##   \n" + " ######  ######    ######   ######   ##  ##     ## ## ## ##     ######     ##    ##     ## ########     ##   \n" + "      ## ##             ##       ##  ##  ##     ## ##  ####          ##    ##    ######### ##   ##      ##   \n" + "##    ## ##       ##    ## ##    ##  ##  ##     ## ##   ###    ##    ##    ##    ##     ## ##    ##     ##   \n" + " ######  ########  ######   ######  ####  #######  ##    ##     ######     ##    ##     ## ##     ##    ##   \n" + "\nSession started: [vcrdl-" + sessionId + "]\n";

            Files.write(logFilePath, sessionStartLog.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Logs the End of a session by appending a session end message to the log file.
     */
    public void logSessionEnd() {
        try {
            String sessionStartLog = "\n" + " ######  ########  ######   ######  ####  #######  ##    ##    ######## ##    ## ######## \n" + "##    ## ##       ##    ## ##    ##  ##  ##     ## ###   ##    ##       ###   ## ##     ##\n" + "##       ##       ##       ##        ##  ##     ## ####  ##    ##       ####  ## ##     ##\n" + " ######  ######    ######   ######   ##  ##     ## ## ## ##    ######   ## ## ## ##     ##\n" + "      ## ##             ##       ##  ##  ##     ## ##  ####    ##       ##  #### ##     ##\n" + "##    ## ##       ##    ## ##    ##  ##  ##     ## ##   ###    ##       ##   ### ##     ##\n" + " ######  ########  ######   ######  ####  #######  ##    ##    ######## ##    ## ######## \n" + "#############################################################################################################\n";
            Files.write(logFilePath, sessionStartLog.getBytes(), StandardOpenOption.APPEND);
            System.out.println("end called");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Logs a new log entry and updates the log file and display accordingly.
     *
     * @param newlog The Log object to be logged.
     */
    public void log(Log newlog) {
        logList.add(newlog);
        updateLogFile(newlog);
        displayedLogIndex = logList.size() - 1;
        updateLogField(displayedLogIndex);
    }

    /**
     * Displays the previous log in the log display field.
     */
    public void displayPrevLog() {
        if (displayedLogIndex > 0) {
            displayedLogIndex--;
            updateLogField(displayedLogIndex);
        }
    }

    /**
     * Displays the next log in the log display field.
     */
    public void displayNextLog() {
        if (displayedLogIndex < logList.size() - 1) {
            displayedLogIndex++;
            updateLogField(displayedLogIndex);
        }
    }

    /**
     * Updates the log file with a new log entry.
     *
     * @param log The Log object to be written to the log file.
     */
    private void updateLogFile(Log log) {
        try {
            Files.write(logFilePath, log.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Updates the log display field with the log at the specified index.
     *
     * @param logIndex The index of the log to be displayed.
     */
    private void updateLogField(int logIndex) {
        Log log = logList.get(logIndex);
        logField.setStyle("-fx-text-fill:" + log.getCategory().getColorCode());
        logField.setText(log.getCategory().getEmoticon2() + " -> " + log.getMessage());
    }
}
