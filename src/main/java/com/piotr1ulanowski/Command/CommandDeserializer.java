package com.piotr1ulanowski.Command;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CommandDeserializer implements JsonDeserializer<CommandI> {
    private final String CLASS_NAME_FIELD = "type";
    @Override
    public CommandI deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASS_NAME_FIELD);
        String className = prim.getAsString();
        Class classIndicator = getObjectClass(className);
        return jsonDeserializationContext.deserialize(jsonObject, classIndicator);
    }

    private Class getObjectClass(String className) {
        switch (className) {
            case "del_friends":
                return CommandDelFriends.class;
            case "make_friends":
                return CommandMakeFriends.class;
            default:
                return CommandUpdate.class;
        }
    }
}
