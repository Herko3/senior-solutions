package locations;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateLocationCommand {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    //    @Max(value = 90, message = "Latitude must be less than 90")
//    @Min(value = -90, message = "Latitude must be more than -90")
    @Coordinate(message = "Invalid latitude")
    private double lat;

    //    @Max(value = 180, message = "Longitude must be less than 180")
//    @Min(value = -180, message = "Longitude must be more than -180")
    @Coordinate(type = Type.LON, message = "Invalid longitude")
    private double lon;
}
