package com.piotr1ulanowski.Command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.piotr1ulanowski.JSONParser.JSONParserI;

public class CommandJSONParser implements JSONParserI {
    private final Gson gson;

    public CommandJSONParser() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CommandI.class, new CommandDeserializer());
        this.gson = gsonBuilder.create();
    }

    @Override
    public CommandI deserialize(String json) {
        return gson.fromJson(json, CommandI.class);
    }
}
