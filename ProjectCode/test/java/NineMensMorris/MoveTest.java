package NineMensMorris;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    Game.GamePlay theGame;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        theGame = new Game.GamePlay();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        theGame = null;
    }


    @Test
    public void isOpenTest() throws Exception {
        for (int i = 0; i < 3; i++ ) {
            for (int j = 0; j < 8; j++) {
                assertTrue(Move.isOpen(new Point(i, j)));
            }
        }
    }

    @Test
    public void isFlyingTest() throws Exception {
        assertFalse(Move.isFlying(theGame.pl1));
        assertFalse(Move.isFlying(theGame.pl2));
    }
    @Test
    public void linkUpTest() {
        assertEquals(theGame, Move.getMyGame());
    }

    @Test
    public void isLegalTest() {
        assertTrue(Move.isLegal(new Player(),new Point(0,0), new Point(0,1)));
        assertFalse(Move.isLegal(new Player(), new Point(0,0), new Point(1, 0)));
    }

    @Test
    void moveTestOne() throws Exception {
        assertTrue(Move.changeLocation(theGame.pl2, Game.IN_BAG, new Point(0,0)));
        assertTrue(Move.changeLocation(theGame.pl2, new Point(0,0), new Point(0,7)));
        assertFalse(Move.changeLocation(theGame.pl2, new Point(0,7), new Point(2,7)));
    }
}