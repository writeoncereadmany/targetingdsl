package com.writeoncereadmany.targetingdsl.targeting;

import com.writeoncereadmany.targetingdsl.Targeting;

import java.util.Map;

/**
 * This is a fallback for when we get something unexpected out of our parser
 */
public class MangledTargeting implements Targeting {

    @Override
    public boolean isSatisfiedBy(Map impression) {
        throw new RuntimeException();
    }

    public static OperatorBuilder builder() {
        return (x, y) -> new MangledTargeting();
    }
}
