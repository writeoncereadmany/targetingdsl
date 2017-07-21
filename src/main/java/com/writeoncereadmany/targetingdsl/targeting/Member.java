package com.writeoncereadmany.targetingdsl.targeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writeoncereadmany.targetingdsl.Targeting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.writeoncereadmany.targetingdsl.targeting.PathExtractor.extractPath;

public class Member implements Targeting {

    private final List<String> path;
    private final List<String> expectedValues;

    public Member(List<String> path, List<String> expectedValue) {
        this.path = path;
        this.expectedValues = expectedValue;
    }

    @Override
    public boolean isSatisfiedBy(Map impression) {
        return expectedValues.contains(extractPath(path, impression));
    }
}
