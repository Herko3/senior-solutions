package locations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/locations")
@Tag(name = "Operations on locations")
public class LocationsController {

    private LocationsService service;

    public LocationsController(LocationsService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "list all locations")
    public List<LocationDto> getLocations(@RequestParam Optional<String> prefix,
                                          @RequestParam Optional<Double> minLat,
                                          @RequestParam Optional<Double> minLon,
                                          @RequestParam Optional<Double> maxLat,
                                          @RequestParam Optional<Double> maxLon
    ) {
        return service.getLocations(prefix, minLat, minLon, maxLat, maxLon);
    }

    @GetMapping("/{id}")
    @Operation(summary = "gives back an exact location")
    @ApiResponse(responseCode = "404", description = "Location not found")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return service.findLocationById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new location")
    @ApiResponse(responseCode = "201", description = "Location is created")
    public LocationDto createLocation(@Valid @RequestBody CreateLocationCommand command) {
        return service.createLocation(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "changes the value of an existing location")
    @ApiResponse(responseCode = "404", description = "Location not found")
    public LocationDto updateLocation(@PathVariable("id") long id,@Valid @RequestBody UpdateLocationCommand command) {
        return service.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deletes an exact location")
    @ApiResponse(responseCode = "404", description = "Location not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id) {
        service.deleteLocation(id);
    }

//    @ExceptionHandler(LocationNotFound.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<Problem> handleNotFound(LocationNotFound lnf) {
//        Problem problem = Problem.builder()
//                .withType(URI.create("locations/not-found"))
//                .withTitle("Not Found")
//                .withStatus(Status.NOT_FOUND)
//                .withDetail(lnf.getMessage())
//                .build();
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
//                .body(problem);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Problem> handleValidException(MethodArgumentNotValidException e){
//        List<Violation> violations =
//                e.getBindingResult().getFieldErrors().stream()
//                .map(fe -> new Violation(fe.getField(),fe.getDefaultMessage(),fe.getRejectedValue()))
//                .toList();
//
//        Problem problem = Problem.builder()
//                .withType(URI.create("locations/not-valid"))
//                .withTitle("Validation Error")
//                .withStatus(Status.BAD_REQUEST)
//                .withDetail(e.getMessage())
//                .with("violations", violations)
//                .build();
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
//                .body(problem);
//    }
}
