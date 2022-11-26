package com.piotr1ulanowski.Command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class CommandJSONParser {
    private final Gson gson;

    public CommandJSONParser() {
        this.gson = new Gson();
    }

    public CommandUpdate deserialize(String json) {
        return gson.fromJson(json, CommandUpdate.class);
    }
}
