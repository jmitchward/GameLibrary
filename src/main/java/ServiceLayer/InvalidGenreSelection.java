package ServiceLayer;

public class InvalidGenreSelection extends Throwable {
    public InvalidGenreSelection(String message) {
        super(message);
    }

    public InvalidGenreSelection(String message, Exception e) {
        super(message, e);
    }
}
