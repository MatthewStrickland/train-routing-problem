package com.thoughtworks.routing.service.impl;

import com.thoughtworks.routing.model.DirectedGraph;
import com.thoughtworks.routing.model.input.ProblemOneInput;
import com.thoughtworks.routing.service.Solver;

import java.util.Map;

/**
 * Solver implementation for problem one.
 */
public class ProblemOneSolver implements Solver<ProblemOneInput> {

    /** Return value when no route can be found. */
    private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    @Override
    public String solve(final ProblemOneInput parameters) {
        final String[] nodes = parameters.getNodes();
        final DirectedGraph directedGraph = parameters.getDirectedGraph();
        Integer runningTotal = 0;
        for (int i = 0; i < nodes.length - 1; i++) {
            final Map<String, Integer> destLocation = directedGraph.get(nodes[i]);
            if (destLocation == null) {
                return NO_SUCH_ROUTE;
            }
            final Integer length = destLocation.get(nodes[i + 1]);
            if (length == null) {
                return NO_SUCH_ROUTE;
            }
            runningTotal += length;
        }
        return runningTotal.toString();
    }
}
