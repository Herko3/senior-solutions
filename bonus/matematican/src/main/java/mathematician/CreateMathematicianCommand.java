package mathematician;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateMathematicianCommand {

    @NotBlank(message = "name most not be blank or null")
    private String name;

    @NotEmpty(message = "topics must have records")
    @NotNull
    private List<String> favouriteTopics;

    @Past(message = "birthdate must be in the past")
    private LocalDate birthDate;

    @Prime
    private int favouritePrim;
}
