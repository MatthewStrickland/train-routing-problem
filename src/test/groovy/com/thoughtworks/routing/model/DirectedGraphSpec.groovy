package com.thoughtworks.routing.model

import spock.lang.Unroll
/**
 * Test class for DirectedGraph.
 */
class DirectedGraphSpec extends AbstractGraphSpecification {

    @Unroll
    def "graph created successfully from valid input of #simulatedInput"(String simulatedInput) {
        given: "a graph with a valid structure"

        when: "I attempt to parse this graph"
        def graph = DirectedGraph.createGraphAttributes(simulatedInput)

        then: "no exceptions are thrown during parsing"
        noExceptionThrown()
        !graph.getGraphAttributes().isEmpty()

        where:
        simulatedInput      | _
        DEFAULT_INPUT_GRAPH | _
        SINGLE_EDGE_GRAPH   | _
        CUSTOM_INPUT_GRAPH  | _
    }

    @Unroll
    def "graph created unsuccessfully from invalid input of #simulatedInput"(String simulatedInput) {
        given: "a graph with an invalid structure"

        when: "I attempt to parse this graph"
        DirectedGraph.createGraphAttributes(simulatedInput)

        then: "parsing exception is thrown"
        IllegalArgumentException ex = thrown()
        ex.message == "Unable to parse inputted graph"

        where:
        simulatedInput  | _
        EMPTY_GRAPH     | _
        INVALID_GRAPH_1 | _
        INVALID_GRAPH_2 | _
        INVALID_GRAPH_3 | _
        INVALID_GRAPH_4 | _
        INVALID_GRAPH_5 | _
    }

}
