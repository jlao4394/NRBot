package com.someazndude.nrbot.api.methods;

import com.someazndude.nrbot.api.interfaces.Filter;
import com.someazndude.nrbot.asm.accessors.Player;

import java.util.ArrayList;
import java.util.List;

public class Players {

    public static Player getLocal() {
        return Client.getClient().getLocalPlayer();
    }

    public static Player get(Filter<Player> filter) {
        Player[] players = Client.getClient().getPlayerArray();
        int[] playerIndices = Client.getClient().getPlayerIndices();
        for (int i = 0; i < Client.getClient().getPlayerCount(); i++) {
            Player player = players[playerIndices[i]];
            if (filter.accept(player)) {
                return player;
            }
        }
        return null;
    }

    public static Player[] getAll(Filter<Player> filter) {
        List<Player> players = new ArrayList<>();
        for (Player player : Client.getClient().getPlayerArray()) {
            if (filter.accept(player)) {
                players.add(player);
            }
        }
        return players.toArray(new Player[players.size()]);
    }
}
