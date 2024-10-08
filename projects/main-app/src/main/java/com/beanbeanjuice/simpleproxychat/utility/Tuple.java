package com.beanbeanjuice.simpleproxychat.utility;

import lombok.Getter;

@Getter
public class Tuple<KeyType, ValueType> {

    private final KeyType key;
    private final ValueType value;

    private Tuple(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public static Tuple<String, String> of(String key, String value) {
        return new Tuple<>(key, value);
    }

    public static Tuple<String, Integer> of(String key, int value) {
        return new Tuple<>(key, value);
    }

}
