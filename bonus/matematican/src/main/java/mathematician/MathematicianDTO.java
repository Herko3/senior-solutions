package mathematician;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MathematicianDTO {

    private String name;
    private List<String> favouriteTopics;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private int favouritePrim;
}
