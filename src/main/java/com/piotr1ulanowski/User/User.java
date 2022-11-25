package com.piotr1ulanowski.User;

import java.util.concurrent.ConcurrentHashMap;

public class User {
    private final ConcurrentHashMap<String, UserProperty> properties;

    // Updates property if given value has a newer timestamp.
    public void addProperty(String name, UserProperty value) {
        UserProperty oldProperty = properties.putIfAbsent(name, value);
        if (oldProperty != null) {
            oldProperty.setIfNewer(value);
        }
    }

    public User() {
        properties = new ConcurrentHashMap<>();
    }
}
