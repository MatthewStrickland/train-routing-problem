package com.thoughtworks.routing.model.input

import com.thoughtworks.routing.base.AbstractProblemFourSpec
import com.thoughtworks.routing.model.DirectedGraph
import spock.lang.Unroll

/**
 * Test class for ProblemFourInput.
 */
class ProblemFourInputSpec extends AbstractProblemFourSpec {

    @Unroll
    def "input string #input is able to be parsed"(String input, List<String[]> expectedNodes,
                                                   boolean expectedExact, int expectedLimit) {
        given: "a valid input string added to the object"
        def constructedInput = ProblemFourInput.builder().input(input).build()

        when: "we attempt to parse the input"
        constructedInput.parseInput()

        then: "we are able to inspect the created object"
        constructedInput.nodes as List == expectedNodes
        constructedInput.exactly == expectedExact
        constructedInput.limit == expectedLimit

        where:
        input             | expectedNodes | expectedExact | expectedLimit
        QUESTION_1        | ['C', 'C']    | false         | 3
        CUSTOM_QUESTION_2 | ['A', 'C']    | true          | 4
        CUSTOM_QUESTION_3 | ['C', 'C']    | true          | 30
        CUSTOM_QUESTION_4 | ['B', 'A']    | false         | 10
        CUSTOM_QUESTION_5 | ['A', 'D']    | false         | 5
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
        input                 | _
        TOO_MANY_HYPENS_INPUT | _
        BAD_CHARACTER_INPUT   | _
        NO_DELIMITER_INPUT_1  | _
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
        input                 | expectedException
        EMPTY_INPUT           | ArrayIndexOutOfBoundsException
        ONLY_ROUTE_INPUT      | ArrayIndexOutOfBoundsException
        SHORTHAND_INPUT       | IllegalArgumentException
        NO_DELIMITER_INPUT_2  | ArrayIndexOutOfBoundsException
        WRONG_ORDER_INPUT     | IllegalArgumentException
    }

    @Unroll
    def "object not valid when elements are null or empty"(ProblemFourInput problemInput) {
        given: "a constructed problem object"

        when: "we attempt to validate the object"
        def valid = problemInput.isValid()

        then: "the object is not valid"
        !valid

        where:
        problemInput                                                                                    | _
        ProblemFourInput.builder().build()                                                              | _
        ProblemFourInput.builder().directedGraph(new DirectedGraph()).build()                           | _
        ProblemFourInput.builder().directedGraph(new DirectedGraph()).nodes([] as String[]).build()     | _
        ProblemFourInput.builder().directedGraph(new DirectedGraph()).nodes(["AB"] as String[]).build() | _
        ProblemFourInput.builder().directedGraph(new DirectedGraph()).nodes(["A"] as String[]).build()  | _
    }

}
