package it.sergio.arnese.pair;

import java.util.*;

public class GooseGame {
    private Map<String, Integer> playersPosition = new HashMap<>();

    public void addPlayer(String player) {
        Objects.requireNonNull(player);

        if( this.playersPosition.keySet().contains(player) ) {
            throw new IllegalArgumentException("there is already a player: " + player);
        }

        this.playersPosition.put(player, 0);
    }

    public List<String> getAllPlayer() {
        return new ArrayList<>(this.playersPosition.keySet());
    }

    public void movePlayer(String playerName, int steps) {
        this.playersPosition.put(playerName, steps);
    }
}
