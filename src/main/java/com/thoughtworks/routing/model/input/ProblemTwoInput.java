package com.thoughtworks.routing.model.input;

import com.thoughtworks.routing.enumeration.LimitType;
import com.thoughtworks.routing.model.DirectedGraph;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * DTO class for problem two to move and parse all information to the solver.
 */
@Getter
@Log4j2
public class ProblemTwoInput extends AbstractProblemInput {

    /** The delimiter between the route specified and the operation. */
    private static final String ROUTE_OPERATION_DELIMITER = ", ";
    /** The delimiter between the limit type and the limit. */
    private static final String OPERATION_DELIMITER = " ";

    /** If this should be an exact match. */
    private LimitType limitType;
    /** The limit (inclusive) of nodes we should aim for but not surpass. */
    private int limit;

    /**
     * Constructor.
     * @param input the input
     * @param directedGraph the directed graph
     */
    @Builder
    public ProblemTwoInput(final String input, final DirectedGraph directedGraph) {
        setInput(input);
        setDirectedGraph(directedGraph);
    }

    @Override
    public void parseInput() {
        final String[] inputSplit = getInput().split(ROUTE_OPERATION_DELIMITER);
        super.parseNodes(inputSplit[0]);
        final String[] operationSplit = inputSplit[1].split(OPERATION_DELIMITER);
        this.limitType = LimitType.valueOf(operationSplit[0].toUpperCase());
        this.limit = Integer.parseInt(operationSplit[1]);
    }

    @Override
    public boolean isValid() {
        final boolean isValid = super.isValid() && this.limit > 0;
        if (!isValid) {
            log.error("Input not valid for solving");
        }
        return isValid;
    }
}
