package com.thoughtworks.routing.model.input

import com.thoughtworks.routing.base.AbstractProblemFourSpec
import com.thoughtworks.routing.enumeration.LimitType
import com.thoughtworks.routing.model.DirectedGraph
import spock.lang.Unroll

/**
 * Test class for ProblemFourInput.
 */
class ProblemFourInputSpec extends AbstractProblemFourSpec {

    @Unroll
    def "input string #input is able to be parsed"(String input, List<String[]> expectedNodes,
                                                   LimitType expectedLimitType, int expectedLimit) {
        given: "a valid input string added to the object"
        def constructedInput = ProblemFourInput.builder()
                .directedGraph(new DirectedGraph())
                .input(input)
                .build()

        when: "we attempt to parse the input"
        constructedInput.parseInput()

        then: "we are able to inspect the created object"
        constructedInput.nodes as List == expectedNodes
        constructedInput.limitType == expectedLimitType
        constructedInput.limit == expectedLimit

        and: "the object is valid"
        constructedInput.isValid()

        where:
        input             | expectedNodes | expectedLimitType | expectedLimit
        QUESTION_1        | ['C', 'C']    | LimitType.MAXIMUM | 29
        CUSTOM_QUESTION_2 | ['A', 'C']    | LimitType.EXACTLY | 9
        CUSTOM_QUESTION_3 | ['B', 'B']    | LimitType.MAXIMUM | 21
        CUSTOM_QUESTION_4 | ['B', 'B']    | LimitType.MAXIMUM | 18
        CUSTOM_QUESTION_5 | ['B', 'B']    | LimitType.MAXIMUM | 16
        CUSTOM_QUESTION_6 | ['A', 'D']    | LimitType.EXACTLY | 4
    }

    @Unroll
    def "input string #input is able to be parsed, but fails validation"(String input) {
        given: "a valid input string added to the object, and parsed"
        def constructedInput = ProblemFourInput.builder()
                .input(input)
                .directedGraph(new DirectedGraph())
                .build()

        when: "we attempt to validate the object"
        def valid = constructedInput.parseInput()

        then: "the object is not valid"
        !valid

        where:
        input                  | _
        TOO_MANY_HYPENS_INPUT  | _
        BAD_CHARACTER_INPUT    | _
        NO_DELIMITER_INPUT_1   | _
        SINGLE_CHARACTER_INPUT | _
    }

    @Unroll
    def "input string #input is not able to be parsed and throws exception #exception"(String input,
                                                                                       Class<Exception> expectedException) {
        given: "a valid input string added to the object, and parsed"
        def constructedInput = ProblemFourInput.builder()
                .input(input)
                .directedGraph(new DirectedGraph())
                .build()

        when: "we attempt to validate the object"
        constructedInput.parseInput()

        then: "expected exception was thrown"
        def ex = thrown(expectedException)
        ex.class == expectedException

        where:
        input                | expectedException
        EMPTY_INPUT          | ArrayIndexOutOfBoundsException
        ONLY_ROUTE_INPUT     | ArrayIndexOutOfBoundsException
        SHORTHAND_INPUT      | IllegalArgumentException
        NO_DELIMITER_INPUT_2 | ArrayIndexOutOfBoundsException
        WRONG_ORDER_INPUT    | IllegalArgumentException
    }

    @Unroll
    def "object not valid when elements are null or empty"(ProblemFourInput problemInput, String[] nodes) {
        given: "a constructed problem object"
        problemInput.setNodes(nodes)

        when: "we attempt to validate the object"
        def valid = problemInput.isValid()

        then: "the object is not valid"
        !valid

        where:
        problemInput                                                          | nodes
        ProblemFourInput.builder().build()                                    | null
        ProblemFourInput.builder().directedGraph(new DirectedGraph()).build() | null
        ProblemFourInput.builder().directedGraph(new DirectedGraph()).build() | [] as String[]
        ProblemFourInput.builder().directedGraph(new DirectedGraph()).build() | ["A", "AB"] as String[]
        ProblemFourInput.builder().directedGraph(new DirectedGraph()).build() | ["A"] as String[]
    }

}
