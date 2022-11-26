package com.piotr1ulanowski.User;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserSerializer implements JsonSerializer<User> {
    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        JsonObject propertiesObj = context.serialize(user.getProperties(), ConcurrentHashMap.class).getAsJsonObject();
        for(Map.Entry<String, JsonElement> e : propertiesObj.entrySet()) {
            obj.add(e.getKey(), e.getValue());
        }
        return obj;
    }
}
