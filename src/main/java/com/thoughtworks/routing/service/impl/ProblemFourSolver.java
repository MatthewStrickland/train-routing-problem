package com.thoughtworks.routing.service.impl;

import com.thoughtworks.routing.enumeration.LimitType;
import com.thoughtworks.routing.model.Connection;
import com.thoughtworks.routing.model.Journey;
import com.thoughtworks.routing.model.input.ProblemFourInput;
import com.thoughtworks.routing.service.ProblemSolver;
import com.thoughtworks.routing.service.util.SolvingUtil;

import java.util.List;
import java.util.function.Function;

/**
 * ProblemSolver implementation for problem four.
 */
public class ProblemFourSolver implements ProblemSolver<ProblemFourInput> {

    /** The function to determine what the cumulative distance is. */
    private static final Function<Journey, Integer> LIMIT_FUNCTION =
        journey -> journey.getConnections().stream().mapToInt(Connection::getDistance).sum();

    @Override
    public String solve(final ProblemFourInput parameters) {
        final int limit = parameters.getLimit();
        final LimitType limitType = parameters.getLimitType();
        final List<Journey> foundRoutes = SolvingUtil.findPossibleJourneys(parameters, limit, limitType, LIMIT_FUNCTION);
        return String.format("%s %s", foundRoutes.size(), foundRoutes.toString());
    }
}
