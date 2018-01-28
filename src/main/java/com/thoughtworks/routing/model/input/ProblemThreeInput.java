package com.thoughtworks.routing.model.input;

import com.thoughtworks.routing.model.DirectedGraph;
import lombok.Builder;
import lombok.Getter;

import java.util.regex.Pattern;

/**
 * DTO class for problem three to move and parse all information to the solver.
 */
@Getter
public class ProblemThreeInput extends AbstractProblemInput {

    /** The expected delimiter. */
    public static final String INPUT_DELIMITER = "-";
    /** The expected pattern of each node. */
    private static final Pattern VALID_PATTERN = Pattern.compile("[A-Za-z]");
    /** The set of nodes we will traverse through the map. */
    private String[] nodes;

    @Builder
    public ProblemThreeInput(final String input, final DirectedGraph directedGraph, final String[] nodes) {
        super(input, directedGraph, nodes);
    }

    @Override
    public void parseInput() {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
