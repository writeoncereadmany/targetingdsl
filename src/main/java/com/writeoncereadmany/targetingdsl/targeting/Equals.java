package com.writeoncereadmany.targetingdsl.targeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writeoncereadmany.targetingdsl.Targeting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Equals implements Targeting {

    private final List<String> path;
    private final String expectedValue;

    public Equals(List<String> path, String expectedValue) {
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

            if(iterator instanceof String) {
                return iterator.equals(expectedValue);
            }
            return false;
        } catch (IOException ex) {
            return false;
        }
    }

    public static OperatorBuilder builder() {
        return Equals::new;
    }
}
