package com.thoughtworks.routing.model;

import lombok.Builder;
import lombok.Getter;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * DTO class to move and parse all information to the solver.
 */
@Builder
@Getter
public class ProblemOneInput implements ProblemInput {

    /** The expected delimiter. */
    public static final String INPUT_DELIMITER = "-";
    /** The expected pattern of each node. */
    private static final Pattern VALID_PATTERN = Pattern.compile("[A-Za-z]");

    /** The raw input string. */
    private String input;
    /** The directed graph. */
    private DirectedGraph directedGraph;
    /** The set of nodes we will traverse through the map. */
    private String[] nodes;

    @Override
    public void parseInput() {
        this.nodes = getInput().split(INPUT_DELIMITER);
    }

    @Override
    public boolean isValid() {
        return getDirectedGraph() != null &&
            getNodes() != null &&
            getNodes().length != 0 &&
            Stream.of(getNodes()).allMatch(node -> VALID_PATTERN.matcher(node).matches());
    }
}
