package com.piotr1ulanowski.UserTests;

import com.piotr1ulanowski.User.User;
import com.piotr1ulanowski.User.UserProperty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {
    @DisplayName("Should add a property with a bigger timestamp.")
    @Test
    void testAddingProperty_WhenBiggerTimestamp_ShouldUpdateTheProperty() {
        final String PROPERTY_NAME = "test_property";
        final UserProperty FIRST_UPDATE = new UserProperty("test1", 1);
        final UserProperty SECOND_UPDATE = new UserProperty("test2", 2);

        User testUser = new User();

        testUser.addProperty("test_property", FIRST_UPDATE);
        testUser.addProperty("test_property", SECOND_UPDATE);

        assertTrue(testUser.getProperties().containsKey(PROPERTY_NAME) &&
                Objects.equals(testUser.getProperties().get(PROPERTY_NAME).getTimestamp(),
                        SECOND_UPDATE.getTimestamp()) &&
                testUser.getProperties().get(PROPERTY_NAME).getName().equals(SECOND_UPDATE.getName()));
    }

    @DisplayName("Should not add a property with a smaller timestamp.")
    @Test
    void testAddingProperty_WhenSmallerTimestamp_ShouldNotUpdateTheProperty() {
        final String PROPERTY_NAME = "test_property";
        final UserProperty FIRST_UPDATE = new UserProperty("test1", 2);
        final UserProperty SECOND_UPDATE = new UserProperty("test2", 1);

        User testUser = new User();

        testUser.addProperty("test_property", FIRST_UPDATE);
        testUser.addProperty("test_property", SECOND_UPDATE);

        assertTrue(testUser.getProperties().containsKey(PROPERTY_NAME) &&
                Objects.equals(testUser.getProperties().get(PROPERTY_NAME).getTimestamp(),
                        FIRST_UPDATE.getTimestamp()) &&
                testUser.getProperties().get(PROPERTY_NAME).getName().equals(FIRST_UPDATE.getName()));
    }

    @DisplayName("Should have a friend after adding one.")
    @Test
    void testAddingAFriend_WhenAddedFriend_ShouldContainAFriend() {
        final String USER2_ID = "test2_id";

        User test1User = new User();
        test1User.addFriend(USER2_ID);

        assertTrue(test1User.getFriends().contains(USER2_ID));
    }

    @DisplayName("Should not have a friend after deleting one.")
    @Test
    void testDeletingAFriend_WhenDeletesFriend_ShouldNotContainAFriend() {
        final String USER2_ID = "test2_id";

        User test1User = new User();
        test1User.addFriend(USER2_ID);
        test1User.removeFriend(USER2_ID);

        assertFalse(test1User.getFriends().contains(USER2_ID));
    }
}

