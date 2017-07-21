package com.writeoncereadmany.targetingdsl.targeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writeoncereadmany.targetingdsl.Targeting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Member implements Targeting {

    private final List<String> path;
    private final List<String> expectedValues;

    public Member(List<String> path, List<String> expectedValue) {
        this.path = path;
        this.expectedValues = expectedValue;
    }

    public boolean isSatisfiedBy(String jsonImpression) {
        try {
            Object iterator = new ObjectMapper().readValue(jsonImpression, Map.class);
            for(String element : path) {
                if(iterator instanceof Map) {
                    iterator = ((Map) iterator).get(element);
                }
            }

            return expectedValues.contains(iterator);

        } catch (IOException ex) {
            return false;
        }
    }
}
