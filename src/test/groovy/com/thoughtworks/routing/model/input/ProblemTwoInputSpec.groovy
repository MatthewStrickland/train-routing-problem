package com.thoughtworks.routing.model.input

import com.thoughtworks.routing.base.AbstractProblemTwoSpec
import com.thoughtworks.routing.enumeration.LimitType
import com.thoughtworks.routing.model.DirectedGraph
import spock.lang.Unroll

/**
 * Test class for ProblemTwoInput.
 */
class ProblemTwoInputSpec extends AbstractProblemTwoSpec {

    @Unroll
    def "input string #input is able to be parsed"(String input, List<String[]> expectedNodes,
                                                   LimitType expectedLimitType, int expectedLimit) {
        given: "a valid input string added to the object"
        def constructedInput = ProblemTwoInput.builder()
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
        QUESTION_1        | ['C', 'C']    | LimitType.MAXIMUM | 3
        QUESTION_2        | ['A', 'C']    | LimitType.EXACTLY | 4
        CUSTOM_QUESTION_3 | ['C', 'C']    | LimitType.EXACTLY | 2
        CUSTOM_QUESTION_4 | ['B', 'A']    | LimitType.MAXIMUM | 10
        CUSTOM_QUESTION_5 | ['A', 'D']    | LimitType.MAXIMUM | 5
    }

    @Unroll
    def "input string #input is able to be parsed, but fails validation"(String input) {
        given: "a valid input string added to the object, and parsed"
        def constructedInput = ProblemTwoInput.builder()
                .input(input)
                .directedGraph(new DirectedGraph())
                .build()

        when: "we attempt to validate the object"
        def valid = constructedInput.parseInput()

        then: "the object is not valid"
        !valid

        where:
        input                 | _
        TOO_MANY_HYPENS_INPUT | _
        BAD_CHARACTER_INPUT   | _
        NO_DELIMITER_INPUT_1  | _
    }

    @Unroll
    def "input string #input is not able to be parsed and throws exception #exception"(String input,
                                                                                       Class<Exception> expectedException) {
        given: "a valid input string added to the object, and parsed"
        def constructedInput = ProblemTwoInput.builder()
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
    def "object not valid when elements are null or empty"(ProblemTwoInput problemInput) {
        given: "a constructed problem object"

        when: "we attempt to validate the object"
        def valid = problemInput.isValid()

        then: "the object is not valid"
        !valid

        where:
        problemInput                                                                                   | _
        ProblemTwoInput.builder().build()                                                              | _
        ProblemTwoInput.builder().directedGraph(new DirectedGraph()).build()                           | _
        ProblemTwoInput.builder().directedGraph(new DirectedGraph()).nodes([] as String[]).build()     | _
        ProblemTwoInput.builder().directedGraph(new DirectedGraph()).nodes(["AB"] as String[]).build() | _
        ProblemTwoInput.builder().directedGraph(new DirectedGraph()).nodes(["A"] as String[]).build()  | _
    }

}
