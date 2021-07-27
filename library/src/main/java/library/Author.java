package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "author")
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }

    public void addBook(CreateBookCommand command) {
        if (books == null) {
            books = new ArrayList<>();
        }
        Book book = new Book(command.getISBN(), command.getTitle());
        books.add(book);
        book.setAuthor(this);
    }
}
