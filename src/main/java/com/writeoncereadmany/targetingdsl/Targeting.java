package com.writeoncereadmany.targetingdsl;

/**
 * Created by tomj on 21/07/2017.
 */
public interface Targeting {

    boolean isSatisfiedBy(String jsonImpression);
}
