package library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from authors")
class AuthorControllerIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void addBook() {
        AuthorDto author = template.postForObject("/api/authors", new CreateAuthorCommand("Tolkien"), AuthorDto.class);

        AuthorDto result = template.postForObject("/api/authors/" + author.getId() + "/books", new CreateBookCommand("123", "Hobbit"), AuthorDto.class);

        assertThat(result.getBooks())
                .hasSize(1)
                .extracting(BookDto::getTitle)
                .containsExactly("Hobbit");
    }

}