package com.thoughtworks.routing.model.input

import com.thoughtworks.routing.base.AbstractProblemOneSpec
import com.thoughtworks.routing.model.DirectedGraph
import spock.lang.Unroll

/**
 * Test class for ProblemOneInput.
 */
class ProblemOneInputSpec extends AbstractProblemOneSpec {

    @Unroll
    def "input string #input is able to be parsed"(String input, List<String[]> expectedNodes) {
        given: "a valid input string added to the object"
        def constructedInput = ProblemOneInput.builder()
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
        input      | expectedNodes
        QUESTION_1 | ['A', 'B', 'C']
        QUESTION_2 | ['A', 'D']
        QUESTION_3 | ['A', 'D', 'C']
        QUESTION_4 | ['A', 'E', 'B', 'C', 'D']
        QUESTION_5 | ['A', 'E', 'D']
    }

    @Unroll
    def "input string #input is able to be parsed, but fails validation"(String input) {
        given: "a valid input string added to the object, and parsed"
        def constructedInput = ProblemOneInput.builder()
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
    def "object not valid when elements are null or empty"(ProblemOneInput problemInput) {
        given: "a constructed problem object"

        when: "we attempt to validate the object"
        def valid = problemInput.isValid()

        then: "the object is not valid"
        !valid

        where:
        problemInput                                                                                   | _
        ProblemOneInput.builder().build()                                                              | _
        ProblemOneInput.builder().directedGraph(new DirectedGraph()).build()                           | _
        ProblemOneInput.builder().directedGraph(new DirectedGraph()).nodes([] as String[]).build()     | _
        ProblemOneInput.builder().directedGraph(new DirectedGraph()).nodes(["AB"] as String[]).build() | _
    }

}
