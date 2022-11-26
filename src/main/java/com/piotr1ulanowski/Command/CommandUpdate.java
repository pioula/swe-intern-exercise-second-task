package com.piotr1ulanowski.Command;

import com.piotr1ulanowski.User.User;
import com.piotr1ulanowski.User.UserProperty;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CommandUpdate implements Runnable {
    private final String user;
    private final Integer timestamp;
    private final HashMap<String, String> values;
    private ConcurrentHashMap<String, User> users;
    public CommandUpdate(final String user, final Integer timestamp,
                         final HashMap<String, String> values) {
        this.user = user;
        this.timestamp = timestamp;
        this.values = values;
    }

    // Removing users from friend lists of each user.
    @Override
    public void run() {
        users.putIfAbsent(user, new User());

        User userObject = users.get(user);

        values.forEach((propertyName, value) ->
                userObject.addProperty(propertyName, new UserProperty(value, timestamp))
        );
    }

    public void setUsers(ConcurrentHashMap<String, User> users) {
        this.users = users;
    }
}
