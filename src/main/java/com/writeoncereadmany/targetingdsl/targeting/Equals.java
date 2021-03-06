package com.writeoncereadmany.targetingdsl.targeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writeoncereadmany.targetingdsl.Targeting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.writeoncereadmany.targetingdsl.targeting.PathExtractor.extractPath;

public class Equals implements Targeting {

    private final List<String> path;
    private final String expectedValue;

    public Equals(List<String> path, String expectedValue) {
        this.path = path;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean isSatisfiedBy(Map impression) {
        Object object = extractPath(path, impression);

        if(object instanceof String) {
            return object.equals(expectedValue);
        }
        return false;
    }

    public static OperatorBuilder builder() {
        return Equals::new;
    }
}
