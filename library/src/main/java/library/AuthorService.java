package library;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthorService {

    private ModelMapper mapper;
    private AuthorRepository repository;

    public List<AuthorDto> listAuthors(){
        return repository.findAll().stream()
                .map(a->mapper.map(a,AuthorDto.class))
                .toList();
    }

    public AuthorDto createAuthor(CreateAuthorCommand command){
        Author author = new Author(command.getName());
        repository.save(author);
        return mapper.map(author,AuthorDto.class);
    }

    @Transactional
    public AuthorDto addBook(long id, CreateBookCommand command){
        Author author = repository.findById(id).orElseThrow(()->new NoAuthorFoundException(id));
        author.addBook(command);
        return mapper.map(author,AuthorDto.class);
    }

    public void deleteById(long id){
        repository.deleteById(id);
    }

}
