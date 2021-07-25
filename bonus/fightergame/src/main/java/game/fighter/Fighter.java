package game.fighter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Fighter {

    private final int HP;

    private Long id;
    private String name;
    private int healthPoint;
    private int damage;
    private int score;

    public Fighter(Long id, String name, int healthPoint, int damage) {
        HP = healthPoint;
        this.id = id;
        this.name = name;
        this.healthPoint = healthPoint;
        this.damage = damage;
    }

    public int doDamage() {
        return damage;
    }

    public void sufferDamage(int damage){
        healthPoint -= damage;
    }

    public void addWin() {
        score++;
    }

    public boolean isAlive() {
        return healthPoint >= 0;
    }

    public void resetHP(){
        healthPoint = HP;
    }

}
