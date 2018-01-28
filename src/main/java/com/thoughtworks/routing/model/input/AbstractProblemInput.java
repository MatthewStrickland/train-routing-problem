package com.thoughtworks.routing.model.input;

import com.thoughtworks.routing.model.DirectedGraph;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO class to move and parse all information to the solver.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
abstract class AbstractProblemInput implements ProblemInput {

    /** The raw input string. */
    private String input;
    /** The directed graph. */
    private DirectedGraph directedGraph;
    /** The set of nodes we will traverse through the map. */
    private String[] nodes;

}
