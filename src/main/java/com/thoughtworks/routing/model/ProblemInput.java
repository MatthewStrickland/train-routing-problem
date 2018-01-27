package com.thoughtworks.routing.model;

import java.util.Map;

/**
 * A visitor responsible for holding information corresponding to a specific problem type.
 */
public interface ProblemInput {

    /**
     * Parse the raw input string.
     */
    void parseInput();

    /**
     * Gets the raw input string.
     * @return the raw input
     */
    String getInput();

    /**
     * Get the directed graph to be solved against.
     * @return the directed graph
     */
    Map<String, Map<String, Integer>> getDirectedGraph();

    /**
     * Determine if this object is ready to be solved.
     * @return true if the object is fully constructed
     */
    boolean isValid();
}
