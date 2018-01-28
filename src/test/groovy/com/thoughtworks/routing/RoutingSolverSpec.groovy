package com.thoughtworks.routing

import com.thoughtworks.routing.base.AbstractGraphSpecification
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Unroll

/**
 * Full end to end test testing all 10 example problems.
 */
class RoutingSolverSpec extends AbstractGraphSpecification {

    @Rule
    public SystemOutRule systemOutLog = new SystemOutRule().enableLog().muteForSuccessfulTests()

    @Rule
    public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream()

    def cleanup() {
        systemOutLog.clearLog()
    }

    @Unroll
    def "For the default graph, calculate #problem"(String problem, String[] mockInput, String expectedOutput) {
        given: "we appropriately mock the system input"
        systemInMock.provideLines([DEFAULT_INPUT_GRAPH, *mockInput, 'e'] as String[])

        when: "we call our main class"
        RoutingSolver.main()

        then: "then the result is as expected"
        systemOutLog.getLog().readLines().get(9) == expectedOutput

        where:
        problem                                                                         | mockInput | expectedOutput
        "The distance of the route A-B-C"                                               |
                ["1", "A-B-C"] as String[]                                                          |
                "Distance found for A-B-C: 9"

        "The distance of the route A-D"                                                 |
                ["1", "A-D"] as String[]                                                            |
                "Distance found for A-D: 5"

        "The distance of the route A-D-C"                                               |
                ["1", "A-D-C"] as String[]                                                          |
                "Distance found for A-D-C: 13"

        "The distance of the route A-E-B-C-D"                                           |
                ["1", "A-E-B-C-D"] as String[]                                                      |
                "Distance found for A-E-B-C-D: 22"

        "The distance of the route A-E-D"                                               |
                ["1", "A-E-D"] as String[]                                                          |
                "Distance found for A-E-D: NO SUCH ROUTE"

        "The number of trips starting at C and ending at C with a maximum of 3 stops"   |
                ["2", "C-C, maximum 3"] as String[]                                                 |
                "Number of trips found for C-C, maximum 3: 2 " +
                "[C-D-C, distance: 16, C-E-B-C, distance: 9]"

        "The number of trips starting at A and ending at C with exactly 4 stops"        |
                ["2", "A-C, exactly 4"] as String[]                                                 |
                "Number of trips found for A-C, exactly 4: 3 " +
                "[A-B-C-D-C, distance: 25, A-D-C-D-C, distance: 29, A-D-E-B-C, distance: 18]"

        "The length of the shortest route (in terms of distance to travel) from A to C" |
                ["3", "A-C"] as String[]                                                            |
                "Shortest distance found for A-C: 9"

        "The length of the shortest route (in terms of distance to travel) from B to B" |
                ["3", "B-B"] as String[]                                                            |
                "Shortest distance found for B-B: 9"

        "The number of different routes from C to C with a distance of less than 30"    |
                ["4", "C-C, maximum 29"] as String[]                                                |
                "Routes found for C-C, maximum 29: 7 " +
                "[C-D-C, distance: 16, C-D-C-E-B-C, distance: 25, C-D-E-B-C, distance: 21, C-E-B-C, distance: 9, C-E-B-C-D-C, distance: 25, C-E-B-C-E-B-C, distance: 18, C-E-B-C-E-B-C-E-B-C, distance: 27]"
    }
}
