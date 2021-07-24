package minastirith;

import minastirith.army.Attacker;
import minastirith.army.Defender;

public class Main {
    public static void main(String[] args) {
        Attacker mordor = new Attacker();
        Defender gondor = new Defender();
        mordor.addBasicMeleeUnit(100);
        mordor.addEliteMeleeUnit(20);
        mordor.addRangedUnit(50);
        mordor.addCatapult(10);
        mordor.addBallista(5);
        gondor.addBasicMeleeUnit(80);
        gondor.addEliteMeleeUnit(10);
        gondor.addRangedUnit(150);
        gondor.addTower(10);
        gondor.addWall(5);

        System.out.println(mordor.getStatistics());
        System.out.println(gondor.getStatistics());

    }
}
