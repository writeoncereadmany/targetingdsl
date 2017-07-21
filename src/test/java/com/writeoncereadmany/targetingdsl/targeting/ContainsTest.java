package com.writeoncereadmany.targetingdsl.targeting;

import com.writeoncereadmany.targetingdsl.Targeting;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by tomj on 21/07/2017.
 */
public class ContainsTest {

    @Test
    public void passesWhenPropertyContainsRequiredValue() {
        Targeting containsCheck = new Contains(asList("cloakroom", "hatrack"), "fedora");

        assertTrue(containsCheck.isSatisfiedBy("{ \"cloakroom\" : { \"hatrack\" : [\"fedora\", \"trilby\", \"bowler\"] } }"));
    }

    @Test
    public void failsWhenPropertyDoesNotContainRequiredValue() {
        Targeting containsCheck = new Contains(asList("cloakroom", "hatrack"), "boomerang");

        assertFalse(containsCheck.isSatisfiedBy("{ \"cloakroom\" : { \"hatrack\" : [\"fedora\", \"trilby\", \"bowler\"] } }"));
    }

    @Test
    public void failsInsteadOfThrowingWhenPropertyDoesNotExist() {
        Targeting containsCheck = new Contains(asList("narnia"), "boomerang");

        assertFalse(containsCheck.isSatisfiedBy("{ \"cloakroom\" : { \"hatrack\" : [\"fedora\", \"trilby\", \"bowler\"] } }"));
    }

}