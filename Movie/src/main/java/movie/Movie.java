package movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private long id;
    private String title;
    private int length;
    private List<Integer> ratings = new ArrayList<>();
    private double averageRate;

    public Movie(long id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void addRating(int rating){
        ratings.add(rating);

        averageRate = ratings.stream().collect(Collectors.summarizingInt(Integer::intValue)).getAverage();
    }
}
