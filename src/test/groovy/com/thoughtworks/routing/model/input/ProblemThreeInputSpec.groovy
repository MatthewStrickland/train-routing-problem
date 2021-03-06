package com.thoughtworks.routing.model.input

import com.thoughtworks.routing.base.AbstractProblemThreeSpec
import com.thoughtworks.routing.model.DirectedGraph
import spock.lang.Unroll

/**
 * Test class for ProblemThreeInput.
 */
class ProblemThreeInputSpec extends AbstractProblemThreeSpec {

    @Unroll
    def "input string #input is able to be parsed"(String input, List<String[]> expectedNodes) {
        given: "a valid input string added to the object"
        def constructedInput = ProblemThreeInput.builder()
                .input(input)
                .directedGraph(new DirectedGraph())
                .build()

        when: "we attempt to parse the input"
        constructedInput.parseInput()

        then: "we are able to inspect the created object"
        constructedInput.nodes as List == expectedNodes

        and: "the object is valid"
        constructedInput.isValid()

        where:
        input             | expectedNodes
        QUESTION_1        | ['A', 'C']
        QUESTION_2        | ['B', 'B']
        CUSTOM_QUESTION_3 | ['C', 'D',]
        CUSTOM_QUESTION_4 | ['E', 'D']
        CUSTOM_QUESTION_5 | ['C', 'A']
    }

    @Unroll
    def "input string #input is able to be parsed, but fails validation"(String input) {
        given: "a valid input string added to the object, and parsed"
        def constructedInput = ProblemThreeInput.builder()
                .input(input)
                .directedGraph(new DirectedGraph())
                .build()

        when: "we attempt to validate the object"
        def valid = constructedInput.parseInput()

        then: "the object is not valid"
        !valid

        where:
        input                  | _
        EMPTY_INPUT            | _
        NO_HYPHEN_INPUT        | _
        TOO_MANY_HYPENS_INPUT  | _
        BAD_CHARACTER_INPUT    | _
        NO_DELIMITER_INPUT     | _
        SINGLE_CHARACTER_INPUT | _
    }

    @Unroll
    def "object not valid when elements are null or empty"(ProblemThreeInput problemInput, String[] nodes) {
        given: "a constructed problem object"
        problemInput.setNodes(nodes)

        when: "we attempt to validate the object"
        def valid = problemInput.isValid()

        then: "the object is not valid"
        !valid

        where:
        problemInput                                                           | nodes
        ProblemThreeInput.builder().build()                                    | null
        ProblemThreeInput.builder().directedGraph(new DirectedGraph()).build() | null
        ProblemThreeInput.builder().directedGraph(new DirectedGraph()).build() | [] as String[]
        ProblemThreeInput.builder().directedGraph(new DirectedGraph()).build() | ["A", "AB"] as String[]
    }

}
