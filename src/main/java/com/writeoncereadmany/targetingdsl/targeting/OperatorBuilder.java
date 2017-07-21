package com.writeoncereadmany.targetingdsl.targeting;

import com.writeoncereadmany.targetingdsl.Targeting;

import java.util.List;

@FunctionalInterface
public interface OperatorBuilder {

    Targeting build(List<String> path, String value);
}
