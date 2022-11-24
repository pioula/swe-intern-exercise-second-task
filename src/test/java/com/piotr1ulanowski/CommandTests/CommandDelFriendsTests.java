package com.piotr1ulanowski.CommandTests;

import com.piotr1ulanowski.Command.CommandDelFriends;
import com.piotr1ulanowski.User.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandDelFriendsTests {
    @DisplayName("Should remove friends")
    @Test
    void testRemovingFriends_IfHaveFriends_ShouldRemoveFriends() {
        final String USER_ID_1 = "user1";
        final String USER_ID_2 = "user2";

        HashMap<String, User> users = new HashMap<>();
        users.put(USER_ID_1, new User());
        users.put(USER_ID_2, new User());

        users.get(USER_ID_1).addFriend(USER_ID_2);
        users.get(USER_ID_2).addFriend(USER_ID_1);

        CommandDelFriends cm = new CommandDelFriends(USER_ID_1, USER_ID_2);

        cm.execute(users);

        assertTrue(!users.get(USER_ID_1).getFriends().contains(USER_ID_2) &&
                !users.get(USER_ID_2).getFriends().contains(USER_ID_1));
    }

    @DisplayName("Should do nothing if don't have friends")
    @Test
    void testRemovingFriends_IfDontHaveFriends_ShouldDoNothing() {
        final String USER_ID_1 = "user1";
        final String USER_ID_2 = "user2";

        HashMap<String, User> users = new HashMap<>();

        CommandDelFriends cm = new CommandDelFriends(USER_ID_1, USER_ID_2);

        cm.execute(users);

        assertTrue(!users.get(USER_ID_1).getFriends().contains(USER_ID_2) &&
                !users.get(USER_ID_2).getFriends().contains(USER_ID_1));
    }
}
