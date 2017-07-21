package com.writeoncereadmany.targetingdsl.targeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writeoncereadmany.targetingdsl.Targeting;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static com.writeoncereadmany.targetingdsl.targeting.PathExtractor.extractPath;

public class Contains implements Targeting {

    private final List<String> path;
    private final String expectedValue;

    public Contains(List<String> path, String expectedValue) {
        this.path = path;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean isSatisfiedBy(Map impression) {
        Object object = extractPath(path, impression);

        if(object instanceof List) {
            return ((List) object).contains(expectedValue);
        }

        return false;
    }

    public static OperatorBuilder builder() {
        return Contains::new;
    }
}
