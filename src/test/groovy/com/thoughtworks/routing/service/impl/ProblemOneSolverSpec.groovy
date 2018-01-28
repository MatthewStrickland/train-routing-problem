package com.thoughtworks.routing.service.impl

import com.thoughtworks.routing.base.AbstractProblemOneSpec
import com.thoughtworks.routing.model.DirectedGraph
import com.thoughtworks.routing.model.input.ProblemOneInput
import spock.lang.Unroll

/**
 * Test class for ProblemOneSolver.
 */
class ProblemOneSolverSpec extends AbstractProblemOneSpec {

    def classUnderTest = new ProblemOneSolver()

    @Unroll
    def "solve a given problem input #inputString for graph #graph"(String graph,
                                                                    String inputString, String expectedSolution) {
        given: "a valid string to pass to the solver"
        def problemInput = ProblemOneInput.builder()
                .directedGraph(DirectedGraph.createGraphAttributes(graph))
                .input(inputString)
                .build()
        problemInput.parseInput()

        when: "we solve this input"
        def solution = classUnderTest.solve(problemInput)

        then: "the solution is as expected"
        solution == expectedSolution

        where:
        graph               | inputString    | expectedSolution
        DEFAULT_INPUT_GRAPH | QUESTION_1     | "9"
        DEFAULT_INPUT_GRAPH | QUESTION_2     | "5"
        DEFAULT_INPUT_GRAPH | QUESTION_3     | "13"
        DEFAULT_INPUT_GRAPH | QUESTION_4     | "22"
        DEFAULT_INPUT_GRAPH | QUESTION_5     | "NO SUCH ROUTE"
        //Custom graph
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_1 | "NO SUCH ROUTE"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_2 | "9"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_3 | "15"
        CUSTOM_INPUT_GRAPH  | CUSTOM_ROUTE_4 | "NO SUCH ROUTE"
    }

}
