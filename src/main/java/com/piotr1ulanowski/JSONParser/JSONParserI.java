package com.piotr1ulanowski.JSONParser;

public interface JSONParserI<T> {
    T deserialize(String json);
}
