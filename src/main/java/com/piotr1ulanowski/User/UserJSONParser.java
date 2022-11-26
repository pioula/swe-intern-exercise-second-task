package com.piotr1ulanowski.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ConcurrentHashMap;

public class UserJSONParser {
    private Gson gson;

    public UserJSONParser() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new UserSerializer())
                .registerTypeAdapter(UserProperty.class, new UserPropertySerializer())
                .create();
    }

    public String serialize(ConcurrentHashMap<String, User> users) {
        return gson.toJson(users);
    }

}
