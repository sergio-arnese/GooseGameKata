package it.sergio.arnese.test.unit;

import it.sergio.arnese.pair.GooseGame;
import it.sergio.arnese.pair.PlayerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GooseGameTest {

    private GooseGame gooseGame;

    @BeforeEach
    void setUp() {
        gooseGame = new GooseGame();
    }

    @Test
    public void testAddPlayer() {
        String player = "Player1";
        int numExpectedPlayers = 1;

        gooseGame.addPlayer(player);

        List<String> allPlayer = gooseGame.getAllPlayer();

        assertEquals(numExpectedPlayers, allPlayer.size());
        assertEquals(player, allPlayer.get(0));
    }

    @Test
    public void testTwoPlayer() {
        String player1 = "Player1";
        String player2 = "Player2";

        int numExpectedPlayers = 2;

        gooseGame.addPlayer(player1);
        gooseGame.addPlayer(player2);

        List<String> allPlayer = gooseGame.getAllPlayer();

        assertEquals(numExpectedPlayers, allPlayer.size());
        assertTrue(allPlayer.contains(player1));
        assertTrue(allPlayer.contains(player2));
    }

    @Test
    public void testDuplicatedPlayer() {
        String player1 = "Player1";

        gooseGame.addPlayer(player1);

        assertThrows(IllegalArgumentException.class, ()-> gooseGame.addPlayer(player1));
    }

    @Test
    public void testSendCommandOnePlayer() {
        PlayerInterface playerInterface = new PlayerInterface();

        String commandResponse = playerInterface.sendCommand("add player Pippo");

        assertEquals("players: Pippo", commandResponse);
    }

    @Test
    public void testSendCommandTwoPlayers() {
        PlayerInterface playerInterface = new PlayerInterface();

        playerInterface.sendCommand("add player Pippo");
        String commandResponse2 = playerInterface.sendCommand("add player Pluto");

        assertEquals("players: Pippo, Pluto", commandResponse2);
    }

    @Test
    public void testSendCommandAlreadyExistingPlayer() {
        PlayerInterface playerInterface = new PlayerInterface();

        playerInterface.sendCommand("add player Pippo");
        String commandResponse2 = playerInterface.sendCommand("add player Pippo");

        assertEquals("Pippo: already existing player", commandResponse2);
    }

    @Test
    public void testSendCommandMoveFromStart() {
        PlayerInterface playerInterface = new PlayerInterface();
        playerInterface.sendCommand("add player Pippo");
        playerInterface.sendCommand("add player Pluto");

        String commandResponse = playerInterface.sendCommand("move Pippo 4, 2");

        assertEquals("Pippo rolls 4, 2. Pippo moves from Start to 6", commandResponse);
    }
}
