package com.thoughtworks.routing.model.input;

import com.thoughtworks.routing.model.DirectedGraph;

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
     * Gets the array of nodes that were given in the input.
     * @return the array of nodes
     */
    String[] getNodes();

    /**
     * Sets the input.
     * @param input the input
     */
    void setInput(String input);

    /**
     * Sets the directed graph.
     * @param directedGraph the directed graph
     */
    void setDirectedGraph(DirectedGraph directedGraph);

    /**
     * Determine if this object is ready to be solved.
     * @return true if the object is fully constructed
     */
    boolean isValid();
}
