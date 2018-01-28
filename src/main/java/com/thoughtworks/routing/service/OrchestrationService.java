package com.thoughtworks.routing.service;

import com.thoughtworks.routing.enumeration.ProblemType;
import com.thoughtworks.routing.model.DirectedGraph;

/**
 * Service for orchestrating.
 */
public interface OrchestrationService {

    /**
     * Orchestrate inputs to a specific service for solving.
     * @param directedGraph the directed graph
     * @param chosenProblem the problem which has been chosen
     * @param inputStringToParse the input string
     */
    void orchestrate(final DirectedGraph directedGraph,
                     final ProblemType chosenProblem,
                     final String inputStringToParse);
}
