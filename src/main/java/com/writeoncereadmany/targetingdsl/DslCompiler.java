package com.writeoncereadmany.targetingdsl;

import com.writeoncereadmany.targetingdsl.generated.TargetingLexer;
import com.writeoncereadmany.targetingdsl.generated.TargetingParser;
import org.antlr.v4.runtime.CommonTokenStream;

import static org.antlr.v4.runtime.CharStreams.fromString;

/**
 * Created by tomj on 21/07/2017.
 */
public class DslCompiler {

    public Targeting compile(String targeting) {
        TargetingLexer lexer = new TargetingLexer(fromString(targeting));
        TargetingParser parser = new TargetingParser(new CommonTokenStream(lexer));

        return TargetingBuilder2.build(parser.targeting());
    }
}
