package com.thoughtworks.routing.service.impl

import com.thoughtworks.routing.base.AbstractProblemThreeSpec
import com.thoughtworks.routing.model.DirectedGraph
import com.thoughtworks.routing.model.input.ProblemThreeInput
import spock.lang.Unroll

/**
 * Test class for ProblemThreeSolver.
 */
class ProblemThreeSolverSpec extends AbstractProblemThreeSpec {

    def classUnderTest = new ProblemThreeSolver()

    @Unroll
    def "solve a given problem input #inputString for graph #graph"(String graph,
                                                                    String inputString, String expectedSolution) {
        given: "a valid string to pass to the solver"
        def problemInput = ProblemThreeInput.builder()
                .directedGraph(DirectedGraph.createGraphAttributes(graph))
                .input(inputString)
                .build()
        problemInput.parseInput()

        when: "we solve this input"
        def solution = classUnderTest.solve(problemInput)

        then: "the solution is as expected"
        solution == expectedSolution

        where:
        graph               | inputString       | expectedSolution
        DEFAULT_INPUT_GRAPH | QUESTION_1        | "9"
        DEFAULT_INPUT_GRAPH | QUESTION_2        | "9"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_3 | "8"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_4 | "15"
        DEFAULT_INPUT_GRAPH | CUSTOM_QUESTION_5 | "NO SUCH ROUTE"
        //Custom graph
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_1    | "8"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_2    | "NO SUCH ROUTE"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_3    | "5"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_4    | "NO SUCH ROUTE"
    }

}
