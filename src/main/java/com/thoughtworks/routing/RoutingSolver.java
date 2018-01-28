package com.thoughtworks.routing;

import com.thoughtworks.routing.reader.InputReader;
import com.thoughtworks.routing.service.impl.DefaultOrchestrator;
import com.thoughtworks.routing.service.impl.ProblemFourSolver;
import com.thoughtworks.routing.service.impl.ProblemOneSolver;
import com.thoughtworks.routing.service.impl.ProblemThreeSolver;
import com.thoughtworks.routing.service.impl.ProblemTwoSolver;

import java.io.IOException;

/**
 * Main class. Takes arguments from a user input and runs until terminated.
 */
public final class RoutingSolver {

    /**
     * Private constructor to prevent instantiation.
     */
    private RoutingSolver() {
        //Do nothing
    }

    /**
     * Entry point to running code.
     * @param args any program arguments, none used
     * @throws IOException when user input fails
     */
    public static void main(final String[] args) throws IOException {
        final DefaultOrchestrator orchestrator = new DefaultOrchestrator(
            new ProblemOneSolver(),
            new ProblemTwoSolver(),
            new ProblemThreeSolver(),
            new ProblemFourSolver()
        );
        final InputReader inputReader = new InputReader(orchestrator);
        inputReader.start();
    }

}
