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
}
