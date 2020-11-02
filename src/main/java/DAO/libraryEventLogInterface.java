package DAO;

public interface libraryEventLogInterface {
    void writeLogEntry(String entry) throws LibraryNotFound;
}
