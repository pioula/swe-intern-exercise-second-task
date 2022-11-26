package com.piotr1ulanowski.User;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class UserPropertySerializer implements JsonSerializer<UserProperty> {
    @Override
    public JsonElement serialize(UserProperty userProperty, Type type, JsonSerializationContext context) {
        return context.serialize(userProperty.getName(), String.class);
    }
}
