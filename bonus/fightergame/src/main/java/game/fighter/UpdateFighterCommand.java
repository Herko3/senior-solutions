package game.fighter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFighterCommand {

    private String name;
    private int healthPoint;
    private int damage;
}
