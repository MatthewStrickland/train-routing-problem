package com.thoughtworks.routing.model.input;

import com.thoughtworks.routing.model.DirectedGraph;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO class for problem two to move and parse all information to the solver.
 */
@Getter
public class ProblemTwoInput extends AbstractProblemInput {

    /** If this should be an exact match. */
    private final boolean exactly;
    /** The limit (inclusive) of nodes we should aim for but not surpass. */
    private final int limit;
    /** The set of nodes we will traverse through the map. */
    private String[] nodes;

    @Builder
    public ProblemTwoInput(final String input, final DirectedGraph directedGraph,
                           final String[] nodes, final boolean exactly, final int limit) {
        super(input, directedGraph, nodes);
        this.exactly = exactly;
        this.limit = limit;
    }

    @Override
    public void parseInput() {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
