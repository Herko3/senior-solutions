package library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void addBook() {
        Author author = new Author("John Doe");
        author.addBook(new CreateBookCommand("isbn","title"));

        assertEquals("isbn",author.getBooks().get(0).getISBN());
    }
}