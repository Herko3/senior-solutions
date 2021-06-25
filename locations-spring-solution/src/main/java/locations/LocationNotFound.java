package locations;

public class LocationNotFound extends IllegalArgumentException{

    public LocationNotFound(String message) {
        super(message);
    }
}
