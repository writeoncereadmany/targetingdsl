package com.writeoncereadmany.targetingdsl.targeting;

import com.writeoncereadmany.targetingdsl.Targeting;

import java.util.List;
import java.util.Map;

public class AllOf implements Targeting {

    private final List<Targeting> targetingList;

    public AllOf(List<Targeting> targetingList) {
        this.targetingList = targetingList;
    }


    @Override
    public boolean isSatisfiedBy(Map impression) {
        return targetingList.stream().allMatch(targeting -> targeting.isSatisfiedBy(impression));
    }
}
