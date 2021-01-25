package it.sergio.arnese.pair;

import java.util.Objects;

public class PlayerInterface {

    private final GooseGame gooseGame = new GooseGame();

    public String sendCommand(String command) {
        Objects.requireNonNull(command);

        String[] splittedCommand = command.split(" ");

        String commandName = splittedCommand[0];

        String response;

        if( commandName.equals("add") ) {
            response = addCommand(splittedCommand[2]);

        } else {
            response = moveCommand(splittedCommand[1], Integer.parseInt(splittedCommand[2].substring(0,1)), Integer.parseInt(splittedCommand[3]));
        }

        return response;
    }

    private String moveCommand(String playerName, int diceRoll1, int diceRoll2) {
        gooseGame.movePlayer(playerName,diceRoll1 + diceRoll2);

        return playerName + " rolls " + diceRoll1 + ", " + diceRoll2 + ". " + playerName + " moves from Start to " + (diceRoll1 + diceRoll2);
    }

    private String addCommand(String playerName) {
        String response;

        try {
            gooseGame.addPlayer(playerName);

            response = "players: " + String.join(", ", gooseGame.getAllPlayer());

        } catch (Exception exc) {
            response = playerName + ": already existing player";
        }

        return response;
    }
}
