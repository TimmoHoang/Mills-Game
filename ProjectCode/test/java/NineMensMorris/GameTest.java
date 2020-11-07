package NineMensMorris;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GameTest extends Game {

    Game.GamePlay theGame;

    @BeforeEach
    void setUp() {
        theGame = new Game.GamePlay();
    }

    @AfterEach
    void tearDown() {
        theGame = null;
    }

    @Test
    public void FirstTest() throws Exception {
        assertTrue(Move.changeLocation(theGame.pl1, new Point(-1, -1), new Point(0,0)));
        assertTrue(Move.changeLocation(theGame.pl1, new Point(-1, -1), new Point(1,0)));
        assertFalse(Move.isOpen(new Point(0,0)));
        assertTrue(theGame.isPlacing());
        assertFalse(theGame.lostByPieceCount());

    }

    @Test
    public void ConstructorTest() throws Exception { //Tests the Constructor and InitPlayers

        assertEquals (theGame.getPlayers().size(),2); //Tests for 2 players

        for (Player player : theGame.getPlayers()) {
            assertEquals(player.getPieces().size(), 9); //Tests for 9 pieces each

            for (Piece piece : player.getPieces()) {
                assertEquals(piece.getPair(), IN_BAG); //Test that all pieces are initialized
            }
        }
    }

    @Test
    public void lostByPieceCountTest() throws Exception {  //Tests the lostByPieceCount method
        //Set-up
        Vector<Piece> myVec = new Vector<Piece>();
        myVec.add(new Piece(IN_BAG, theGame.pl1));
        myVec.add(new Piece(IN_BAG, theGame.pl1 ));
        theGame.getPlayers().firstElement().setPieces(myVec);
        //Test
        assertTrue(theGame.lostByPieceCount());

    }
    @Test
    public void inMillTest() throws Exception {
        //Setup
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,0));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,0));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,1));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,1));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,2));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,3));
        //Test
        assertTrue(theGame.inMill(theGame.pl1, new Point(0, 0)));
    }

    @Test
    void getPlayers() {
    }

    @Test
    void initPlayers() {
    }

    @Test
    void drawQuickTable() {
    }

    @Test
    void drawCurrentMoveTable() {
    }

    @Test
    void inMill() {
    }

    @Test
    void checkMill() {
    }

    @Test
    void lostByPieceCount() {
    }

    @Test
    void noMove() {
        //Setup
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (2,0));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,7));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (2,1));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(1,1));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (2,2));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(1,3));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (2,3));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,4));
        //Test
        assertTrue(theGame.noMove(theGame.pl1));
    }

    @Test
    void isPlacing() {
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,0));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,0));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,1));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,1));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,2));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,2));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,3));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,3));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,4));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,4));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,5));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,5));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,6));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,6));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (0,7));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(2,7));
        Move.changeLocation(theGame.pl1, IN_BAG, new Point (1,0));
        Move.changeLocation(theGame.pl2, IN_BAG, new Point(1,4));

        assertFalse(theGame.isPlacing());

    }
}
