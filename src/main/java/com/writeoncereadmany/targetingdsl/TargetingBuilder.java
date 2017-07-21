package com.writeoncereadmany.targetingdsl;

import com.writeoncereadmany.targetingdsl.generated.TargetingBaseListener;
import com.writeoncereadmany.targetingdsl.generated.TargetingParser;
import com.writeoncereadmany.targetingdsl.targeting.Contains;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

public class TargetingBuilder extends TargetingBaseListener {

    Stack<Object> arguments = new Stack<>();
    Targeting targeting;


    @Override
    public void exitPath(TargetingParser.PathContext ctx) {
        arguments.push(ctx.IDENTIFIER().stream().map(ParseTree::getText).collect(toList()));
    }

    @Override
    public void exitValue(TargetingParser.ValueContext ctx) {
        arguments.push(ctx.IDENTIFIER().getText());
    }

    @Override
    public void exitOperator(TargetingParser.OperatorContext ctx) {
        arguments.push(Contains.builder());
    }

    @Override
    public void exitClause(TargetingParser.ClauseContext ctx) {
        Object value = arguments.pop();
        Object operator = arguments.pop();
        Object path = arguments.pop();

        if(operator instanceof BiFunction) {
            targeting = (Targeting) ((BiFunction) operator).apply(path, value);
        }
    }

    public Targeting getTargeting() {
        return targeting;
    }
}
