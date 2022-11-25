package com.piotr1ulanowski.Command;

import com.piotr1ulanowski.User.User;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface CommandI {
    void execute(ConcurrentHashMap<String, User> users);
}
