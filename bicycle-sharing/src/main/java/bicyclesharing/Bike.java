package bicyclesharing;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Bike {

    private String bikeId;
    private String userId;
    private LocalDateTime checkIn;
    private double distance;
}
