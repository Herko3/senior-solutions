package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private long id;
    private String name;
    private double lat;
    private double lon;

//    public Location(long id, String name, double lat, double lon) {
//        this.id = id;
//        this.name = name;
//        this.lat = lat;
//        this.lon = lon;
//    }

//    public long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getLat() {
//        return lat;
//    }
//
//    public double getLon() {
//        return lon;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%d - %s %f,%f",id,name,lat,lon);
//    }
}
