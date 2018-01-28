package com.thoughtworks.routing.reader

import com.thoughtworks.routing.base.AbstractGraphSpecification
import com.thoughtworks.routing.enumeration.ProblemType
import com.thoughtworks.routing.model.DirectedGraph
import com.thoughtworks.routing.service.OrchestrationService
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream

/**
 * Test class for input reader.
 */
class InputReaderSpec extends AbstractGraphSpecification {

    @Rule
    public SystemOutRule systemOutLog = new SystemOutRule().enableLog().muteForSuccessfulTests();

    @Rule
    public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream()

    def orchestrationService = Mock(OrchestrationService)
    def classUnderTest = new InputReader(orchestrationService);

    def cleanup() {
        systemOutLog.clearLog()
    }

    def "reader accepts input and exits"() {

        given: "a mocked orchestrator and setting System.in"
        systemInMock.provideLines(DEFAULT_INPUT_GRAPH, '1', 'A', 'e')

        DirectedGraph captureGraph
        ProblemType captureProblemType
        String captureInputString

        when: "the reader is started"
        classUnderTest.start()

        then: "arguments passed to the orchestrator are as expected"
        1 * orchestrationService.orchestrate(*_) >> {arguments ->
            captureGraph = arguments[0]
            captureProblemType = arguments[1]
            captureInputString = arguments[2]
        }

        captureGraph.graphAttributes == DirectedGraph.createGraphAttributes(DEFAULT_INPUT_GRAPH).graphAttributes
        captureProblemType == ProblemType.ROUTE_DISTANCE
        captureInputString == 'A'

        and: "the user read an expected stream"
        systemOutLog.getLog().trim() == "Welcome to the train problem solver!" + System.lineSeparator() +
                "There are 4 types of questions to ask:" + System.lineSeparator() +
                "1. Route distance" + System.lineSeparator() +
                "2. Number of routes between two nodes with an inclusive upper limit of stops" + System.lineSeparator() +
                "3. Length of the shortest route" + System.lineSeparator() +
                "4. Number of routes between two nodes with an inclusive upper limit of distance" + System.lineSeparator() +
                "Please input a valid directed graph in the format of 'AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7'" + System.lineSeparator() +
                "Please input the type of problem you'd like to solve as a number, 1-4:" + System.lineSeparator() +
                "Please input the route in the hypen delimited format of A-B-C" + System.lineSeparator() +
                "Would you like to use a new graph (Y/N), or exit (E)?"
    }

    def "reader accepts input and requests new graph"() {

        given: "a mocked orchestrator and setting System.in"
        systemInMock.provideLines(DEFAULT_INPUT_GRAPH, '1', 'A', 'y', CUSTOM_INPUT_GRAPH, '2', 'A', 'e')

        DirectedGraph captureGraph
        ProblemType captureProblemType
        String captureInputString

        when: "the reader is started"
        classUnderTest.start()

        then: "arguments passed to the orchestrator are as expected, calling twice before exit"
        2 * orchestrationService.orchestrate(*_) >> {arguments ->
            captureGraph = arguments[0]
            captureProblemType = arguments[1]
            captureInputString = arguments[2]
        }

        captureGraph.graphAttributes == DirectedGraph.createGraphAttributes(CUSTOM_INPUT_GRAPH).graphAttributes
        captureProblemType == ProblemType.ROUTE_COUNT_STOPS
        captureInputString == 'A'
    }

    def "reader accepts input and does not request new graph"() {

        given: "a mocked orchestrator and setting System.in"
        systemInMock.provideLines(DEFAULT_INPUT_GRAPH, '1', 'A', 'n', '1', 'A', 'e')

        DirectedGraph captureGraph
        ProblemType captureProblemType
        String captureInputString

        when: "the reader is started"
        classUnderTest.start()

        then: "arguments passed to the orchestrator are as expected, calling twice before exit"
        2 * orchestrationService.orchestrate(*_) >> {arguments ->
            captureGraph = arguments[0]
            captureProblemType = arguments[1]
            captureInputString = arguments[2]
        }

        captureGraph.graphAttributes == DirectedGraph.createGraphAttributes(DEFAULT_INPUT_GRAPH).graphAttributes
        captureProblemType == ProblemType.ROUTE_DISTANCE
        captureInputString == 'A'
    }

    def "reader accepts input and requests wrong character"() {

        given: "a mocked orchestrator and setting System.in"
        systemInMock.provideLines(DEFAULT_INPUT_GRAPH, '1', 'A', 'f', 'e')

        DirectedGraph captureGraph
        ProblemType captureProblemType
        String captureInputString

        when: "the reader is started"
        classUnderTest.start()

        then: "arguments passed to the orchestrator are as expected"
        1 * orchestrationService.orchestrate(*_) >> {arguments ->
            captureGraph = arguments[0]
            captureProblemType = arguments[1]
            captureInputString = arguments[2]
        }

        captureGraph.graphAttributes == DirectedGraph.createGraphAttributes(DEFAULT_INPUT_GRAPH).graphAttributes
        captureProblemType == ProblemType.ROUTE_DISTANCE
        captureInputString == 'A'
    }
}
