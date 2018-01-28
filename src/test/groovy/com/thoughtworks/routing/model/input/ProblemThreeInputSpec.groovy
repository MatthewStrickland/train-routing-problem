package com.thoughtworks.routing.model.input

import com.thoughtworks.routing.base.AbstractProblemThreeSpec
import com.thoughtworks.routing.model.DirectedGraph
import spock.lang.Unroll

/**
 * Test class for ProblemThreeInput.
 */
class ProblemThreeInputSpec extends AbstractProblemThreeSpec {

    @Unroll
    def "input string #input is able to be parsed" (String input, List<String[]> expectedNodes) {
        given: "a valid input string added to the object"
        def constructedInput = ProblemThreeInput.builder().input(input).build()

        when: "we attempt to parse the input"
        constructedInput.parseInput()

        then: "we are able to inspect the created object"
        constructedInput.nodes as List == expectedNodes

        where:
        input                  | expectedNodes
        QUESTION_1             | ['C', 'C']
        QUESTION_2             | ['A', 'C']
        CUSTOM_QUESTION_3      | ['C', 'D',]
        CUSTOM_QUESTION_4      | ['E', 'D']
        CUSTOM_QUESTION_5      | ['C', 'A']
        SINGLE_CHARACTER_INPUT | ['A']
    }

    @Unroll
    def "input string #input is able to be parsed, but fails validation"(String input) {
        given: "a valid input string added to the object, and parsed"
        def constructedInput = ProblemThreeInput.builder()
                .input(input)
                .directedGraph(new DirectedGraph())
                .build()
        constructedInput.parseInput()

        when: "we attempt to validate the object"
        def valid = constructedInput.parseInput()

        then: "the object is not valid"
        !valid

        where:
        input                 | _
        EMPTY_INPUT           | _
        NO_HYPHEN_INPUT       | _
        TOO_MANY_HYPENS_INPUT | _
        BAD_CHARACTER_INPUT   | _
        NO_DELIMITER_INPUT    | _
    }

    @Unroll
    def "object not valid when elements are null or empty"(ProblemThreeInput problemInput) {
        given: "a constructed problem object"

        when: "we attempt to validate the object"
        def valid = problemInput.isValid()

        then: "the object is not valid"
        !valid

        where:
        problemInput                                                                                     | _
        ProblemThreeInput.builder().build()                                                              | _
        ProblemThreeInput.builder().directedGraph(new DirectedGraph()).build()                           | _
        ProblemThreeInput.builder().directedGraph(new DirectedGraph()).nodes([] as String[]).build()     | _
        ProblemThreeInput.builder().directedGraph(new DirectedGraph()).nodes(["AB"] as String[]).build() | _
    }

}
