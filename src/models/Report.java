package models;

import java.time.LocalDateTime;

// Jeden wpis w logu sekretarza
public class Report {
    private final String message;
    private final LocalDateTime timestamp;

    public Report(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "[" + timestamp + "] " + message;
    }
}
