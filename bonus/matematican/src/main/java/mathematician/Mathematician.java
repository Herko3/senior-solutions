package mathematician;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mathematician {

    private Long id;

    private String name;
    private List<String> favouriteTopics;
    private LocalDate birthDate;
    private int favouritePrim;

}
