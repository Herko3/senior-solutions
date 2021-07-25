package game.match;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class ContestController {

    private ContestService service;

    public ContestController(ContestService service) {
        this.service = service;
    }

    @GetMapping
    public List<MatchDto> getMatches(){
        return service.getMatches();
    }

    @GetMapping("/{id}")
    public MatchDto findById(@PathVariable ("id") long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatchDto startMatch(@RequestBody CreateMatchCommand command){
        return service.startMatch(command);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        service.deleteAll();
    }
}
