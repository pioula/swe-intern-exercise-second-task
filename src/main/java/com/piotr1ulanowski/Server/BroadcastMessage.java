package com.piotr1ulanowski.Server;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;

public class BroadcastMessage {
    private final HashSet<String> broadcast;
    private final String user;
    private final Integer timestamp;

    private final HashMap<String, String> values;

    @Override
    public String toString() {
        if (broadcast.isEmpty() || values.isEmpty())
            return "";

        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public BroadcastMessage(final HashSet<String> userFriends, final String userID,
                            final HashMap<String, String> values, final Integer timestamp) {
        this.broadcast = userFriends;
        this.user = userID;
        this.values = values;
        this.timestamp = timestamp;
    }
}
