package com.piotr1ulanowski.Command;

import com.piotr1ulanowski.Server.BroadcastMessage;
import com.piotr1ulanowski.User.User;
import com.piotr1ulanowski.User.UserProperty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class CommandUpdate implements CommandI {
    private final String user;
    private final Integer timestamp;
    private final HashMap<String, String> values;
    public CommandUpdate(final String user, final Integer timestamp,
                         final HashMap<String, String> values) {
        this.user = user;
        this.timestamp = timestamp;
        this.values = values;
    }

    // Removing users from friend lists of each user.
    @Override
    public Optional<String> execute(HashMap<String, User> users) {
        if (!users.containsKey(user))
            users.put(user, new User());

        User userObject = users.get(user);
        HashMap<String, String> addedValues = new HashMap<>();
        values.forEach((propertyName, value) -> {
            if (!userObject.addProperty(propertyName, new UserProperty(value, timestamp)))
                addedValues.put(propertyName, value);
        });

        users.put(user, userObject);
        BroadcastMessage broadcastMessage
                = new BroadcastMessage(userObject.getFriends(), user, addedValues, timestamp);

        String result = broadcastMessage.toString();
        return result.equals("") ? Optional.empty() : Optional.of(result);
    }
}
