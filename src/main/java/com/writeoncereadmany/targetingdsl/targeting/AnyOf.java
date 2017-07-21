package com.writeoncereadmany.targetingdsl.targeting;

import com.writeoncereadmany.targetingdsl.Targeting;

import java.util.List;
import java.util.Map;

public class AnyOf implements Targeting {

    private final List<Targeting> targetingList;

    public AnyOf(List<Targeting> targetingList) {
        this.targetingList = targetingList;
    }

    @Override
    public boolean isSatisfiedBy(String jsonImpression) {
        return targetingList.stream().anyMatch(targeting -> targeting.isSatisfiedBy(jsonImpression));
    }

    @Override
    public boolean isSatisfiedBy(Map impression) {
        return targetingList.stream().anyMatch(targeting -> targeting.isSatisfiedBy(impression));
    }
}
