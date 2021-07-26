package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {

    @NotBlank(message = "Name cannot be blank")
    @Schema(description = "name of the locations", example = "London")
    private String name;

//    @Max(value = 90, message = "Latitude must be less than 90")
//    @Min(value = -90, message = "Latitude must be more than -90")
    @Coordinate(message = "Invalid latitude")
    @Schema(description = "Latitude of the locations between -90 and 90", example = "17.14")
    private double lat;

//    @Max(value = 180, message = "Longitude must be less than 180")
//    @Min(value = -180, message = "Longitude must be more than -180")
    @Coordinate(type = Type.LON, message = "Invalid longitude")
    @Schema(description = "Longitude of the locations between -180 and 180", example = "45.25")
    private double lon;
}
