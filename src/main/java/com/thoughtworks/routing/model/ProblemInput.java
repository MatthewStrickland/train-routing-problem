package com.thoughtworks.routing.model;

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
    DirectedGraph getDirectedGraph();

    /**
     * Determine if this object is ready to be solved.
     * @return true if the object is fully constructed
     */
    boolean isValid();
}
