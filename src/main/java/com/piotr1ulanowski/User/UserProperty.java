package com.piotr1ulanowski.User;

public class UserProperty {
    private final String name;
    private final Integer timestamp;

    public UserProperty(String name, Integer timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }
}
