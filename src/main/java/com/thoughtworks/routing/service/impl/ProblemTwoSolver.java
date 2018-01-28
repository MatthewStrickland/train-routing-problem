package com.thoughtworks.routing.service.impl;

import com.thoughtworks.routing.enumeration.LimitType;
import com.thoughtworks.routing.model.Journey;
import com.thoughtworks.routing.model.input.ProblemTwoInput;
import com.thoughtworks.routing.service.Solver;
import com.thoughtworks.routing.service.util.SolvingUtil;

import java.util.List;
import java.util.function.Function;

/**
 * Solver implementation for problem two.
 */
public class ProblemTwoSolver implements Solver<ProblemTwoInput> {

    /** The function to determine what the cumulative trips cardinality is. */
    private static final Function<Journey, Integer> LIMIT_FUNCTION = journey -> journey.getConnections().size();

    @Override
    public String solve(final ProblemTwoInput parameters) {
        final int limit = parameters.getLimit();
        final LimitType limitType = parameters.getLimitType();
        final List<Journey> foundRoutes = SolvingUtil.findPossibleJourneys(parameters, limit, limitType, LIMIT_FUNCTION);
        return String.format("%s %s", foundRoutes.size(), foundRoutes.toString());
    }

}
