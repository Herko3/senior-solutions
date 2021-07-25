package game.fighter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FighterDto {

    private String name;
    private int healthPoint;
    private int damage;
    private int score;
}
