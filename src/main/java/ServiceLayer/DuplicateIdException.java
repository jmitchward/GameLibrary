package ServiceLayer;

public class DuplicateIdException extends Throwable {
    public DuplicateIdException(String message) {
        super(message);
    }

    public DuplicateIdException(String message, Exception e) {
        super(message, e);
    }
}
