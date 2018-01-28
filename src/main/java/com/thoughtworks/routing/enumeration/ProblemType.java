package com.thoughtworks.routing.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the type of problems available.
 */
@AllArgsConstructor
@Getter
public enum ProblemType {

    /** Represents a problem such as "The distance of the route A-B-C". */
    ROUTE_DISTANCE(
        "1. Route distance",
        "Please input the route in the hypen delimited format of A-B-C",
        "Distance found for %s: %s"),
    /** Represents a problem such as "The number of trips starting at C and ending at C with a maximum of 3 stops". */
    ROUTE_COUNT_STOPS(
        "2. Number of routes between two nodes with an inclusive upper limit of stops",
        "Please input the route in the format of 'C-C, maximum 3' or 'A-C, exactly 4'",
        "Number of trips found for %s: %s"),
    /** Represents a problem such as "The length of the shortest route (in terms of distance to travel) from A to C". */
    ROUTE_SHORTEST(
        "3. Length of the shortest route",
        "Please input the route in the format hypen delimited format of A-C",
        "Shortest distance found for %s: %s"),
    /**
     * Represents a problem such as "The number of different routes from C to C with a distance of less than 30".
     * Note that maximum 29 is synonymous with less than 30.
     */
    ROUTE_COUNT_DISTANCE(
        "4. Number of routes between two nodes with an inclusive upper limit of distance",
        "Please input the route in the format of 'C-C, maximum 29' or 'A-C, exactly 40'",
        "Routes found for %s: %s");

    /**
     * The explanation of this problem type.
     */
    private String explanation;

    /**
     * The input the user will see in the console.
     */
    private String requestInput;

    /**
     * The format of the solution to display.
     */
    private String solutionFormat;

}