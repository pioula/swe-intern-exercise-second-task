package com.piotr1ulanowski.CommandTests;

import com.piotr1ulanowski.Command.CommandUpdate;
import com.piotr1ulanowski.User.User;
import com.piotr1ulanowski.User.UserProperty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandUpdateTests {
    @DisplayName("Should return Optional.empty() when has no friends")
    @Test
    void testUpdateWithNoFriends_IfNoFriends_NoUpdateShouldBeGenerated() {
        final String USER_ID = "user1";
        final Integer TIMESTAMP = 1;
        final HashMap<String, String> VALUES = new HashMap<>();
        HashMap<String, User> users = new HashMap<>();

        CommandUpdate cm = new CommandUpdate(USER_ID, TIMESTAMP, VALUES);

        assertFalse(cm.execute(users).isPresent());
    }

    @DisplayName("Should return Optional.empty() when no update has happened")
    @Test
    void testUpdateWithNoValuesUpdated_IfNoValuesUpdated_NoUpdateShouldBeGenerated() {
        final String USER_ID = "user1";
        final String FRIEND_ID = "friend_id";
        final Integer TIMESTAMP1 = 1; // Timestamp of the property already added.
        final Integer TIMESTAMP2 = 0; // Timestamp of the property that is going to be added.
        final String PROPERTY_NAME = "test_property";
        final String PROPERTY_VALUE = "test";
        HashMap<String, String> values = new HashMap<>();
        HashMap<String, User> users = new HashMap<>();

        User user1 = new User();
        user1.addFriend(FRIEND_ID);
        user1.addProperty(PROPERTY_NAME, new UserProperty(PROPERTY_VALUE, TIMESTAMP1));
        values.put(PROPERTY_NAME, PROPERTY_VALUE);
        users.put(USER_ID, user1);

        CommandUpdate cm = new CommandUpdate(USER_ID, TIMESTAMP2, values);

        assertFalse(cm.execute(users).isPresent());
    }

    @DisplayName("Should return an Optional String when there is an update")
    @Test
    void testUpdateWithValuesUpdated_IfValuesUpdated_UpdateShouldBeGenerated() {
        final String USER_ID = "user1";
        final String FRIEND_ID = "friend_id";
        final Integer TIMESTAMP1 = 0; // Timestamp of the property already added.
        final Integer TIMESTAMP2 = 1; // Timestamp of the property that is going to be added.
        final String PROPERTY_NAME = "test_property";
        final String PROPERTY_VALUE = "test";
        HashMap<String, String> values = new HashMap<>();
        HashMap<String, User> users = new HashMap<>();

        User user1 = new User();
        user1.addFriend(FRIEND_ID);
        user1.addProperty(PROPERTY_NAME, new UserProperty(PROPERTY_VALUE, TIMESTAMP1));
        values.put(PROPERTY_NAME, PROPERTY_VALUE);
        users.put(USER_ID, user1);

        CommandUpdate cm = new CommandUpdate(USER_ID, TIMESTAMP2, values);

        assertTrue(cm.execute(users).isPresent());
    }
}