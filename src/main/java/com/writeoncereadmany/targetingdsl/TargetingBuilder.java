package com.writeoncereadmany.targetingdsl;

import com.writeoncereadmany.targetingdsl.generated.TargetingParser;
import com.writeoncereadmany.targetingdsl.targeting.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

import static co.unruly.control.result.Introducers.ifEquals;
import static co.unruly.control.result.Match.matchValue;
import static java.util.stream.Collectors.toList;

/**
 * Created by tomj on 21/07/2017.
 */
public class TargetingBuilder {

    public static Targeting build(TargetingParser.TargetingContext ctx) {
        return new AllOf(ctx.clause().stream().map(clause -> buildClause(clause)).collect(toList()));
    }

    private static Targeting buildClause(TargetingParser.ClauseContext ctx) {
        return buildOperator(ctx.operator()).build(buildPath(ctx.path()), buildValue(ctx.value()));
    }

    private static List<String> buildPath(TargetingParser.PathContext path) {
        return path.IDENTIFIER().stream().map(ParseTree::getText).collect(toList());
    }

    private static OperatorBuilder buildOperator(TargetingParser.OperatorContext ctx) {
        return matchValue(ctx.getText(),
            ifEquals("contains", __ -> Contains.builder()),
            ifEquals("=", __ -> Equals.builder())
        ).otherwise(__ -> MangledTargeting.builder());
    }

    private static String buildValue(TargetingParser.ValueContext ctx) {
        return ctx.getText();
    }
}
