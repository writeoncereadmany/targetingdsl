package com.writeoncereadmany.targetingdsl.targeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writeoncereadmany.targetingdsl.Targeting;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Contains implements Targeting {

    private final List<String> path;
    private final String expectedValue;

    public Contains(List<String> path, String expectedValue) {
        this.path = path;
        this.expectedValue = expectedValue;
    }

    public boolean isSatisfiedBy(String jsonImpression) {
        try {
            Object iterator = new ObjectMapper().readValue(jsonImpression, Map.class);
            for(String element : path) {
                if(iterator instanceof Map) {
                    iterator = ((Map) iterator).get(element);
                }
            }

            if(iterator instanceof List) {
                return ((List) iterator).contains(expectedValue);
            }

            return false;

        } catch (IOException ex) {
            return false;
        }
    }

    public static BiFunction<Object, Object, Targeting> builder() {
        return (path, value) -> {
            if(value instanceof String && path instanceof List) {
                return new Contains((List) path, (String) value);
            } else {
                return new MangledTargeting();
            }
        };
    }
}
