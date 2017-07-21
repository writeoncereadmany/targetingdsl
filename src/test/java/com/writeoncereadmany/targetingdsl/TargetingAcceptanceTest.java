package com.writeoncereadmany.targetingdsl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by tomj on 21/07/2017.
 */
public class TargetingAcceptanceTest {

    private final DslCompiler compiler = new DslCompiler();

    @Test
    public void acceptsImpressionWithAudience() {
        final String targetingScript = "imp.audience contains angry";
        final Targeting targeting = compiler.compile(targetingScript);

        assertTrue(targeting.isSatisfiedBy("{ \"audience\" : [\"angry\", \"disappointed\"] }"));
        assertFalse(targeting.isSatisfiedBy("{ \"audience\" : [\"hungry\", \"tired\"] }"));
        assertFalse(targeting.isSatisfiedBy("{ \"distractions\" : [\"balloons\", \"butterflies\"] }"));
    }

    @Test
    public void canCheckMultipleClauses() {
        final String targetingScript =
                "imp.audience contains angry\n" +
                "imp.audience contains disappointed";
        final Targeting targeting = compiler.compile(targetingScript);

        assertTrue(targeting.isSatisfiedBy("{ \"audience\" : [\"angry\", \"disappointed\"] }"));
        assertFalse(targeting.isSatisfiedBy("{ \"audience\" : [\"hungry\", \"tired\"] }"));
        assertFalse(targeting.isSatisfiedBy("{ \"distractions\" : [\"balloons\", \"butterflies\"] }"));
    }

    @Test
    public void checksIfValueExactlyEqual() {
        final String targetingScript = "imp.trousers = enormous";
        final Targeting targeting = compiler.compile(targetingScript);

        assertTrue(targeting.isSatisfiedBy("{ \"trousers\" : \"enormous\" }"));
        assertFalse(targeting.isSatisfiedBy("{ \"trousers\" : \"skinny\" }"));
    }

    @Test
    public void handlesAlternatives() {
        final String targetingScript = "either imp.trousers = enormous or imp.hat = jaunty end";
        final Targeting targeting = compiler.compile(targetingScript);

        assertTrue(targeting.isSatisfiedBy("{ \"trousers\" : \"enormous\" }"));
        assertTrue(targeting.isSatisfiedBy("{ \"hat\" : \"jaunty\" }"));
        assertFalse(targeting.isSatisfiedBy("{ \"shoes\" : \"pointy\" }"));
    }

    @Test
    public void handlesMultupleAlternatives() {
        final String targetingScript = "either imp.trousers = enormous or imp.hat = jaunty or imp.shoes = pointy end";
        final Targeting targeting = compiler.compile(targetingScript);

        assertTrue(targeting.isSatisfiedBy("{ \"trousers\" : \"enormous\" }"));
        assertTrue(targeting.isSatisfiedBy("{ \"hat\" : \"jaunty\" }"));
        assertTrue(targeting.isSatisfiedBy("{ \"shoes\" : \"pointy\" }"));
        assertFalse(targeting.isSatisfiedBy("{ \"shirt\" : \"stripey\" }"));
    }

    @Test
    public void checksIfValueInList() {
        final String targetingScript = "imp.starred in [buffy, angel, charmed]";
        final Targeting targeting = compiler.compile(targetingScript);

        assertTrue(targeting.isSatisfiedBy("{ \"starred\" : \"angel\" }"));
        assertFalse(targeting.isSatisfiedBy("{ \"starred\" : \"psych\" }"));
    }
}
