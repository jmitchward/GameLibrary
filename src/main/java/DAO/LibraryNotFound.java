package DAO;

public class LibraryNotFound extends Throwable {
    public LibraryNotFound(String message) {
        super(message);
    }

    public LibraryNotFound(String message, Exception e) {
        super(message, e);
    }
}
