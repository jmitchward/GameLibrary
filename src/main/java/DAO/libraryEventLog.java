package DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class libraryEventLog implements libraryEventLogInterface {

    public static final String LOG_FILE = "library_log.txt";

    @Override
    public void writeLogEntry(String entry) throws LibraryNotFound {
        PrintWriter recorder;

        try {
            recorder = new PrintWriter((new FileWriter(LOG_FILE, true)));
        } catch (IOException e) {
            throw new LibraryNotFound("Could not get audit log.");
        }
        LocalDateTime timeStamp = LocalDateTime.now();

        recorder.println(timeStamp.toString() + " : " + entry);
        recorder.flush();
    }
}
