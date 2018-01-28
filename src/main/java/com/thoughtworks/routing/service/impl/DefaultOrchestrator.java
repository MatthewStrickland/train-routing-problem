package com.thoughtworks.routing.service.impl;

import com.thoughtworks.routing.enumeration.ProblemType;
import com.thoughtworks.routing.model.DirectedGraph;
import com.thoughtworks.routing.service.OrchestrationService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultOrchestrator implements OrchestrationService {

    private ProblemOneSolver problemOneSolver;
    private ProblemTwoSolver problemTwoSolver;
    private ProblemThreeSolver problemThreeSolver;
    private ProblemFourSolver problemFourSolver;

    @Override
    public void orchestrate(final DirectedGraph directedGraph,
                            final ProblemType chosenProblem,
                            final String inputStringToParse) {

    }

}
