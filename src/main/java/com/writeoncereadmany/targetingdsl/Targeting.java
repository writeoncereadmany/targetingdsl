package com.writeoncereadmany.targetingdsl;

import java.util.Map;

/**
 * Created by tomj on 21/07/2017.
 */
public interface Targeting {

    boolean isSatisfiedBy(String jsonImpression);

    boolean isSatisfiedBy(Map impression);
}
