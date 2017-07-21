package com.writeoncereadmany.targetingdsl.targeting;

import com.writeoncereadmany.targetingdsl.Targeting;

import java.util.List;

public class AllOf implements Targeting {

    private final List<Targeting> targetingList;

    public AllOf(List<Targeting> targetingList) {
        this.targetingList = targetingList;
    }

    @Override
    public boolean isSatisfiedBy(String jsonImpression) {
        return targetingList.stream().allMatch(targeting -> targeting.isSatisfiedBy(jsonImpression));
    }
}
