package com.thoughtworks.routing.model.input;

import com.thoughtworks.routing.model.DirectedGraph;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * DTO class for problem one to move and parse all information to the solver.
 */
@Getter
@Log4j2
public class ProblemOneInput extends AbstractProblemInput {

    /**
     * Constructor.
     * @param input the input
     * @param directedGraph the directed graph
     * @param nodes the nodes
     */
    @Builder
    public ProblemOneInput(final String input, final DirectedGraph directedGraph, final String[] nodes) {
        super(input, directedGraph, nodes);
    }

    @Override
    public boolean isValid() {
        final boolean isValid = super.isValid();
        if (!isValid) {
            log.error("Input not valid for solving");
        }
        return isValid;
    }
}
