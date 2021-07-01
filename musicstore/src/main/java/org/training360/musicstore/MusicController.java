package org.training360.musicstore;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
public class MusicController {

    private MusicStoreService service;

    public MusicController(MusicStoreService service) {
        this.service = service;
    }

    @GetMapping
    public List<InstrumentDTO> getInstruments(@RequestParam Optional<String> brand,
                                              @RequestParam Optional<Long> price){
        return service.getInstruments(brand, price);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDTO createInstrument(@Valid @RequestBody CreateInstrumentCommand command){
        return service.createInstrument(command);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstruments(){
        service.deleteInstruments();
    }

    @GetMapping("/{id}")
    public InstrumentDTO getInstrumentById(@PathVariable ("id") long id){
        return service.getInstrumentById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public InstrumentDTO updatePrice(@Valid @PathVariable ("id") long id, @RequestBody UpdatePriceCommand command){
        return service.updatePrice(id,command);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstrumentById(@PathVariable ("id") long id){
        service.deleteInstrumentById(id);
    }

    @ExceptionHandler(InstrumentNotFound.class)
    public ResponseEntity<Problem> exceptionHandler(InstrumentNotFound inf){
        Problem problem = Problem.builder()
                .withType(URI.create("instruments/not-found"))
                .withTitle("Instrument not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(inf.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidException(MethodArgumentNotValidException e){
        List<Violation> violations =
                e.getBindingResult().getFieldErrors().stream()
                        .map(fe -> new Violation(fe.getField(),fe.getDefaultMessage(),fe.getRejectedValue()))
                        .toList();

        Problem problem = Problem.builder()
                .withType(URI.create("instrument/not-valid"))
                .withTitle("Validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(e.getMessage())
                .with("violations", violations)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
