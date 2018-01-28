package com.thoughtworks.routing.service.impl

import com.thoughtworks.routing.base.AbstractProblemTwoSpec
import com.thoughtworks.routing.model.DirectedGraph
import com.thoughtworks.routing.model.input.ProblemTwoInput
import spock.lang.Unroll
/**
 * Test class for ProblemTwoSolver.
 */
class ProblemTwoSolverSpec extends AbstractProblemTwoSpec {

    def classUnderTest = new ProblemTwoSolver()

    @Unroll
    def "solve a given problem input #inputString for graph #graph"(String graph, String inputString, String expectedSolution) {
        given: "a valid string to pass to the solver"
        def problemInput = ProblemTwoInput.builder()
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
        DEFAULT_INPUT_GRAPH | QUESTION_1        | "2"
        DEFAULT_INPUT_GRAPH | QUESTION_2        | "3"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_3 | "1"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_4 | "5"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_5 | "0"
        //Custom graph
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_1    | "1"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_2    | "0"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_3    | "0"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_4    | "2"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_5    | "2"
    }

}
