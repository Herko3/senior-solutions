package bicyclesharing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BikeDto {

    private String bikeId;
    private String userId;
    private LocalDateTime checkIn;
    private double distance;
}
