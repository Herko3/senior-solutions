package mathematician;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/mathematicians")
public class MathematicianController {

    private MathematicianService service;

    public MathematicianController(MathematicianService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MathematicianDTO saveMathematician(@Valid @RequestBody CreateMathematicianCommand command){
        return service.saveMathematician(command);
    }

    @GetMapping
    public List<MathematicianDTO> listMathematicians(){
        return service.listMathematicians();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        service.deleteAll();
    }
}
