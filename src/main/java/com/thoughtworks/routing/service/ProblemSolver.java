package com.thoughtworks.routing.service;

import com.thoughtworks.routing.model.input.ProblemInput;

/**
 * ProblemSolver interface which accepts a visitor. Responsible for solving an input for a given problem type.
 * @param <P> the type of problem this solver can solve
 */
public interface ProblemSolver<P extends ProblemInput> {

    /**
     * Solve the problem based on the parameters supplied.
     * @param parameters the paramaters ready for solving
     * @return the solution to display back to the user
     */
    String solve(P parameters);

    /**
     * Determine if a set of parameters can be solved at all.
     * @param parameters the paramaters to check
     * @return true if the input was deemed valid
     */
    default boolean canSolve(final P parameters) {
        parameters.parseInput();
        return parameters.isValid();
    }
}
