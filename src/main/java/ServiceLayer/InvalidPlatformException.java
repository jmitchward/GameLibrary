package ServiceLayer;

public class InvalidPlatformException extends Throwable {
    public InvalidPlatformException(String message) {
        super(message);
    }

    public InvalidPlatformException(String message, Exception e) {
        super(message, e);
    }
}
