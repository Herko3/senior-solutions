package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ISBN;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
    }
}
