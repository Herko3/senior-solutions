package locations;

public class LocationParser {

    public Location parse(String text) {
        String[] parts = text.split(",");

        if(parts.length != 3){
            throw new IllegalArgumentException("Text to parse is invalid");
        }
        try {
            double lat = Double.parseDouble(parts[1]);
            double lon = Double.parseDouble(parts[2]);
            return new Location(parts[0], lat, lon);

        } catch (NumberFormatException nfe) {
            throw new IllegalStateException("Cannot parse latitude or longitude", nfe);
        }
    }
}
