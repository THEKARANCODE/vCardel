package dev.thekarancode.logUtilityClasses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private final LocalDateTime timestamp;
    private final LogCategory category;
    private final String message;
    private final String details;

    public Log(LogCategory category, String message, String details) {
        this.timestamp = LocalDateTime.now();
        this.category = category;
        this.message = message;
        this.details = details.isEmpty()?"N/A":details;
    }

    // Getter methods

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public LogCategory getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    // Override toString() method

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);
        return String.format("[%s] [%s] {\n\tLog Message: %s\n\tDetails: %s\n}\n", formattedTimestamp, category, message, details);
    }
}