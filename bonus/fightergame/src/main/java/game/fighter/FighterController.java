package game.fighter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fighters")
public class FighterController {

    private FighterService service;

    public FighterController(FighterService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public FighterDto findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<FighterDto> getFighters() {
        return service.listFighters();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FighterDto saveFighter(@RequestBody CreateFighterCommand command) {
        return service.saveFighter(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FighterDto updateFighter(@PathVariable("id") long id, @RequestBody UpdateFighterCommand command) {
        return service.updateFighter(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) {
        service.deleteByID(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        service.deleteAll();
    }
}
