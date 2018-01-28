package com.thoughtworks.routing.model.input;

import com.thoughtworks.routing.model.DirectedGraph;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * DTO class to move and parse all information to the solver.
 */
@Getter
@Setter
@NoArgsConstructor
abstract class AbstractProblemInput implements ProblemInput {

    /** The expected pattern of each node. */
    private static final Pattern VALID_PATTERN = Pattern.compile("[A-Za-z]");
    /** The expected delimiter. */
    private static final String INPUT_DELIMITER = "-";

    /** The raw input string. */
    private String input;
    /** The directed graph. */
    private DirectedGraph directedGraph;
    /** The set of nodes we will traverse through the map. */
    private String[] nodes;

    @Override
    public void parseInput() {
        parseNodes(this.input);
    }

    @Override
    public boolean isValid() {
        return getDirectedGraph() != null
            && getNodes() != null
            && getNodes().length > 1
            && Stream.of(getNodes())
                .allMatch(node -> VALID_PATTERN.matcher(node).matches());
    }

    /**
     * Set the node array from a given input.
     * @param nodeInput the given input
     */
    void parseNodes(final String nodeInput) {
        setNodes(nodeInput.split(INPUT_DELIMITER));
    }

}
