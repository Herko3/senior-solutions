package game.match;

import game.fighter.Fighter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Long id;

    private Fighter contestant1;
    private Fighter contestant2;

    private String firstContestant;
    private String secondContestant;
    private String result;

    public Match(Long id, Fighter contestant1, Fighter contestant2) {
        this.id = id;
        this.contestant1 = contestant1;
        this.contestant2 = contestant2;
        firstContestant = contestant1.getName();
        secondContestant = contestant2.getName();

        fight();
    }

    public void fight() {
        while (contestant1.isAlive() && contestant2.isAlive()) {
            contestant2.sufferDamage(contestant1.doDamage());
            contestant1.sufferDamage(contestant2.doDamage());
        }

        if (contestant1.isAlive()) {
            result = "Winner is: " + firstContestant;
        } else if (contestant2.isAlive()) {
            result = "Winner is: " + secondContestant;
        } else {
            result = "Draw";
        }

        contestant1.resetHP();
        contestant2.resetHP();
    }
}
