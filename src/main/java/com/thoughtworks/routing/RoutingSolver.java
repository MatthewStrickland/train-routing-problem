package com.thoughtworks.routing;

import com.thoughtworks.routing.reader.InputReader;
import com.thoughtworks.routing.service.impl.*;

import java.io.IOException;

/**
 * Main class. Takes arguments from a user input and runs until terminated.
 */
public class RoutingSolver {

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
        System.exit(0);
    }

}
