package com.writeoncereadmany.targetingdsl;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public interface Targeting {

    default boolean isSatisfiedBy(String jsonImpression) {
        try {
            return isSatisfiedBy(new ObjectMapper().readValue(jsonImpression, Map.class));
        } catch (IOException ex) {
            return false;
        }
    }

    boolean isSatisfiedBy(Map impression);
}
