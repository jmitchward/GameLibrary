package ServiceLayer;

public class InvalidRatingSelection extends Throwable {
    public InvalidRatingSelection(String message) {
        super(message);
    }

    public InvalidRatingSelection(String message, Exception e) {
        super(message, e);
    }
}
