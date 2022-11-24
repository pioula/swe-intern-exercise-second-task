package com.piotr1ulanowski.CommandTests;

import com.piotr1ulanowski.Command.CommandDelFriends;
import com.piotr1ulanowski.Command.CommandMakeFriends;
import com.piotr1ulanowski.User.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandMakeFriendsTests {
    @DisplayName("Should add friends")
    @Test
    void testMakingFriends_WhenRun_ShouldAddFriends() {
        final String USER_ID_1 = "user1";
        final String USER_ID_2 = "user2";

        HashMap<String, User> users = new HashMap<>();

        CommandMakeFriends cm = new CommandMakeFriends(USER_ID_1, USER_ID_2);

        cm.execute(users);

        assertTrue(users.get(USER_ID_1).getFriends().contains(USER_ID_2) &&
                users.get(USER_ID_2).getFriends().contains(USER_ID_1));
    }
}
