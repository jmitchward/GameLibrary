package ServiceLayer;

public class GameNotFoundException extends Throwable {
    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
