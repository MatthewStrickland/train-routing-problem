package com.thoughtworks.routing.service.impl

import com.thoughtworks.routing.base.AbstractGraphSpecification
import com.thoughtworks.routing.enumeration.ProblemType
import com.thoughtworks.routing.model.DirectedGraph
import com.thoughtworks.routing.model.input.ProblemInput
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import spock.lang.Shared

/**
 * Test class for DefaultOrchestrator.
 */
class DefaultOrchestratorSpec extends AbstractGraphSpecification {

    @Shared
            DIRECTED_GRAPH = DirectedGraph.createGraphAttributes(DEFAULT_INPUT_GRAPH)

    @Rule
    public SystemOutRule systemOutLog = new SystemOutRule().enableLog().muteForSuccessfulTests();

    def problemOneSolverMock = Mock(ProblemOneSolver)
    def problemTwoSolverMock = Mock(ProblemTwoSolver)
    def problemThreeSolverMock = Mock(ProblemThreeSolver)
    def problemFourSolverMock = Mock(ProblemFourSolver)

    def classUnderTest = new DefaultOrchestrator(
            problemOneSolverMock, problemTwoSolverMock, problemThreeSolverMock, problemFourSolverMock
    )

    def cleanup() {
        systemOutLog.clearLog()
    }

    def "orchestration for problem one resolves to the correct mocks"() {
        given: "correct input values for orchestration"

        ProblemInput solveCheckInput
        ProblemInput solveInput

        def inputString = "A"

        when: "we attempt to orchestrate"
        classUnderTest.orchestrate(DIRECTED_GRAPH, ProblemType.ROUTE_DISTANCE, inputString)

        then: "the correct mock is called for both canSolve and solve"
        1 * problemOneSolverMock.canSolve(_) >> {
            arguments ->
                solveCheckInput = arguments[0]
                return true
        }
        1 * problemOneSolverMock.solve(_) >> { arguments -> solveInput = arguments[0] }
        0 * _

        solveCheckInput.directedGraph == DIRECTED_GRAPH
        solveCheckInput.input == inputString
        solveCheckInput == solveInput

        and: "log is as expected"
        systemOutLog.getLog().startsWith("Distance found for " + inputString)
    }

    def "orchestration for problem two resolves to the correct mocks"() {
        given: "correct input values for orchestration"

        ProblemInput solveCheckInput
        ProblemInput solveInput

        def inputString = "A"

        when: "we attempt to orchestrate"
        classUnderTest.orchestrate(DIRECTED_GRAPH, ProblemType.ROUTE_COUNT_STOPS, inputString)

        then: "the correct mock is called for both canSolve and solve"
        1 * problemTwoSolverMock.canSolve(_) >> {
            arguments ->
                solveCheckInput = arguments[0]
                return true
        }
        1 * problemTwoSolverMock.solve(_) >> { arguments -> solveInput = arguments[0] }
        0 * _

        solveCheckInput.directedGraph == DIRECTED_GRAPH
        solveCheckInput.input == inputString
        solveCheckInput == solveInput

        and: "log is as expected"
        systemOutLog.getLog().startsWith("Number of trips found for " + inputString)
    }

    def "orchestration for problem three resolves to the correct mocks"() {
        given: "correct input values for orchestration"

        ProblemInput solveCheckInput
        ProblemInput solveInput

        def inputString = "A"

        when: "we attempt to orchestrate"
        classUnderTest.orchestrate(DIRECTED_GRAPH, ProblemType.ROUTE_SHORTEST, inputString)

        then: "the correct mock is called for both canSolve and solve"
        1 * problemThreeSolverMock.canSolve(_) >> {
            arguments ->
                solveCheckInput = arguments[0]
                return true
        }
        1 * problemThreeSolverMock.solve(_) >> { arguments -> solveInput = arguments[0] }
        0 * _

        solveCheckInput.directedGraph == DIRECTED_GRAPH
        solveCheckInput.input == inputString
        solveCheckInput == solveInput

        and: "log is as expected"
        systemOutLog.getLog().startsWith("Shortest distance found for " + inputString)
    }

    def "orchestration for problem four resolves to the correct mocks"() {
        given: "correct input values for orchestration"

        ProblemInput solveCheckInput
        ProblemInput solveInput

        def inputString = "A"

        when: "we attempt to orchestrate"
        classUnderTest.orchestrate(DIRECTED_GRAPH, ProblemType.ROUTE_COUNT_DISTANCE, inputString)

        then: "the correct mock is called for both canSolve and solve"
        1 * problemFourSolverMock.canSolve(_) >> {
            arguments ->
                solveCheckInput = arguments[0]
                return true
        }
        1 * problemFourSolverMock.solve(_) >> { arguments -> solveInput = arguments[0] }
        0 * _

        solveCheckInput.directedGraph == DIRECTED_GRAPH
        solveCheckInput.input == inputString
        solveCheckInput == solveInput

        and: "log is as expected"
        systemOutLog.getLog().startsWith("Routes found for " + inputString)
    }

    def "orchestration for problem which cannot be solved prints nothing"() {
        given: "correct input values for orchestration"

        ProblemInput solveCheckInput

        def inputString = "A"

        when: "we attempt to orchestrate"
        classUnderTest.orchestrate(DIRECTED_GRAPH, ProblemType.ROUTE_COUNT_DISTANCE, inputString)

        then: "the correct mock is called for canSolve, but not solve"
        1 * problemFourSolverMock.canSolve(_) >> {
            arguments ->
                solveCheckInput = arguments[0]
                return false
        }
        0 * _

        solveCheckInput.directedGraph == DIRECTED_GRAPH
        solveCheckInput.input == inputString

        and: "log is empty expected"
        systemOutLog.getLog().isEmpty()
    }

}
