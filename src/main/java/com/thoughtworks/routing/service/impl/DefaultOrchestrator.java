package com.thoughtworks.routing.service.impl;

import com.thoughtworks.routing.enumeration.ProblemType;
import com.thoughtworks.routing.model.DirectedGraph;
import com.thoughtworks.routing.model.input.ProblemFourInput;
import com.thoughtworks.routing.model.input.ProblemInput;
import com.thoughtworks.routing.model.input.ProblemOneInput;
import com.thoughtworks.routing.model.input.ProblemThreeInput;
import com.thoughtworks.routing.model.input.ProblemTwoInput;
import com.thoughtworks.routing.service.OrchestrationService;
import com.thoughtworks.routing.service.ProblemSolver;
import lombok.AllArgsConstructor;

/**
 * Default orchestration implementation.
 */
@AllArgsConstructor
public class DefaultOrchestrator implements OrchestrationService {

    /** ProblemSolver for problem one. */
    private ProblemOneSolver problemOneSolver;
    /** ProblemSolver for problem two. */
    private ProblemTwoSolver problemTwoSolver;
    /** ProblemSolver for problem three. */
    private ProblemThreeSolver problemThreeSolver;
    /** ProblemSolver for problem four. */
    private ProblemFourSolver problemFourSolver;

    @Override
    public void orchestrate(final DirectedGraph directedGraph,
                            final ProblemType chosenProblem,
                            final String inputStringToParse) {

        if (ProblemType.ROUTE_DISTANCE == chosenProblem) {
            solveProblem(this.problemOneSolver,
                ProblemOneInput.builder().directedGraph(directedGraph).input(inputStringToParse).build(),
                chosenProblem, inputStringToParse);
        } else if (ProblemType.ROUTE_COUNT_STOPS == chosenProblem) {
            solveProblem(this.problemTwoSolver,
                ProblemTwoInput.builder().directedGraph(directedGraph).input(inputStringToParse).build(),
                chosenProblem, inputStringToParse);
        } else if (ProblemType.ROUTE_SHORTEST == chosenProblem) {
            solveProblem(this.problemThreeSolver,
                ProblemThreeInput.builder().directedGraph(directedGraph).input(inputStringToParse).build(),
                chosenProblem, inputStringToParse);
        } else if (ProblemType.ROUTE_COUNT_DISTANCE == chosenProblem) {
            solveProblem(this.problemFourSolver,
                ProblemFourInput.builder().directedGraph(directedGraph).input(inputStringToParse).build(),
                chosenProblem, inputStringToParse);
        }
    }

    /**
     * Construct and pass the problemInput to the problemSolver, first checking if it can be solved.
     * Then write the solution to System.out, correctly formatted.
     * @param problemSolver the problemSolver
     * @param problemInput the problem input
     * @param chosenProblem the problem that was chosen
     * @param inputStringToParse the input string
     * @param <P> the type of the Problem input
     */
    private static <P extends ProblemInput> void solveProblem(final ProblemSolver<P> problemSolver,
                                                              final P problemInput,
                                                              final ProblemType chosenProblem,
                                                              final String inputStringToParse) {
        if (problemSolver.canSolve(problemInput)) {
            System.out.println(
                String.format(
                    chosenProblem.getSolutionFormat(),
                    inputStringToParse,
                    problemSolver.solve(problemInput)
                )
            );
        }
    }
}
