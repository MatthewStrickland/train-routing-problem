package com.thoughtworks.routing.service.impl

import com.thoughtworks.routing.base.AbstractProblemFourSpec
import com.thoughtworks.routing.model.DirectedGraph
import com.thoughtworks.routing.model.input.ProblemFourInput
import spock.lang.Unroll

/**
 * Test class for ProblemFourSolver.
 */
class ProblemFourSolverSpec extends AbstractProblemFourSpec {

    def classUnderTest = new ProblemFourSolver()

    @Unroll
    def "solve a given problem input #inputString for graph #graph"(String graph, String inputString, String expectedSolution) {
        given: "a valid string to pass to the solver"
        def problemInput = ProblemFourInput.builder()
                .directedGraph(DirectedGraph.createGraphAttributes(graph))
                .input(inputString)
                .build()
        problemInput.parseInput()

        when: "we solve this input"
        def solution = classUnderTest.solve(problemInput)

        then: "the solution is as expected"
        solution.startsWith(expectedSolution)

        where:
        graph               | inputString       | expectedSolution
        DEFAULT_INPUT_GRAPH | QUESTION_1        | "7"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_2 | "1"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_3 | "3"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_4 | "2"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_5 | "1"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_6 | "0"
        //Custom graph
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_1    | "1"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_2    | "0"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_3    | "2"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_4    | "0"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_5    | "1"
    }

}
