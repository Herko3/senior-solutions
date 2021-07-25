package game.match;

import game.fighter.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void testWinnerIsFirst(){
        Fighter fighter = new Fighter(1L,"First",100,20);
        Fighter other = new Fighter(2L,"Second",80,20);

        Match match = new Match(1L,fighter,other);

        assertEquals("Winner is: First",match.getResult());
        assertEquals(100,fighter.getHealthPoint());
    }

    @Test
    void testWinnerIsSecond(){
        Fighter fighter = new Fighter(1L,"First",100,20);
        Fighter other = new Fighter(2L,"Second",120,20);

        Match match = new Match(1L,fighter,other);

        assertEquals("Winner is: Second",match.getResult());
        assertEquals(100,fighter.getHealthPoint());
    }

    @Test
    void testDraw(){
        Fighter fighter = new Fighter(1L,"Jack Doe",100,20);
        Fighter other = new Fighter(2L,"John",100,20);

        Match match = new Match(1L,fighter,other);

        assertEquals("Draw",match.getResult());
        assertEquals(100,fighter.getHealthPoint());
    }

}