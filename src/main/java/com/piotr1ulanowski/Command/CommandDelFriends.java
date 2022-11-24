package com.piotr1ulanowski.Command;

import com.piotr1ulanowski.User.User;

import java.util.HashMap;
import java.util.Optional;

public class CommandDelFriends implements CommandI {
    private final String user1;
    private final String user2;

    public CommandDelFriends(final String user1, final String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    // Removing users from friend lists of each user.
    @Override
    public Optional<String> execute(HashMap<String, User> users) {
        if (!users.containsKey(user1))
            users.put(user1, new User());
        if (!users.containsKey(user2))
            users.put(user2, new User());

        users.get(user1).removeFriend(user2);
        users.get(user2).removeFriend(user1);

        return Optional.empty();
    }
}
