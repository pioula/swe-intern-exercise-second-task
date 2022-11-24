package com.piotr1ulanowski.Command;

import com.piotr1ulanowski.User.User;

import java.util.HashMap;
import java.util.Optional;

public interface CommandI {
    Optional<String> execute(HashMap<String, User> users);
}
