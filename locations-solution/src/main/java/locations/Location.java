package locations;

public class Location {

    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {
        if (Math.abs(lat) > 90 || Math.abs(lon) > 180){
            throw new IllegalArgumentException("Invalid longitude or latitude");
        }

            this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public boolean isOnEquator() {
        return lat == 0;
    }

    public boolean isOnPrimeMeridian() {
        return lon == 0;
    }

    public double distanceFrom(Location other) {
        final int R = 6371;

        double latDistance = Math.toRadians(other.getLat() - lat);
        double lonDistance = Math.toRadians(other.getLon() - lon);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(other.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
