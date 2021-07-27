package library;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService service;

    @GetMapping
    public List<AuthorDto> listAuthors(){
        return service.listAuthors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthor(@RequestBody CreateAuthorCommand command){
        return service.createAuthor(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable ("id") long id){
        service.deleteById(id);
    }

    @PostMapping("/{id}/books")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto addBook(@PathVariable ("id") long id,@RequestBody CreateBookCommand command){
        return service.addBook(id,command);
    }
}
