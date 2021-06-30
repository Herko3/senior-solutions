package movie;

public class MovieNotFound extends IllegalArgumentException {

    public MovieNotFound(String message) {
        super(message);
    }
}
