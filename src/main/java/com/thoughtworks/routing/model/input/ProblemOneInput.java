package com.thoughtworks.routing.model.input;

import com.thoughtworks.routing.model.DirectedGraph;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * DTO class for problem one to move and parse all information to the solver.
 */
@Getter
@Log4j2
public class ProblemOneInput extends AbstractProblemInput {

    /** The expected delimiter. */
    public static final String INPUT_DELIMITER = "-";
    /** The expected pattern of each node. */
    private static final Pattern VALID_PATTERN = Pattern.compile("[A-Za-z]");
    /** The set of nodes we will traverse through the map. */
    private String[] nodes;

    @Builder
    public ProblemOneInput(final String input, final DirectedGraph directedGraph, final String[] nodes) {
        super(input, directedGraph, nodes);
    }

    @Override
    public void parseInput() {
        this.nodes = getInput().split(INPUT_DELIMITER);
    }

    @Override
    public boolean isValid() {
        final boolean valid = getDirectedGraph() != null &&
            getNodes() != null &&
            getNodes().length != 0 &&
            Stream.of(getNodes()).allMatch(node -> VALID_PATTERN.matcher(node).matches());
        if (!valid) {
            log.error("Input not valid for solving");
        }
        return valid;
    }
}
