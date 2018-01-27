package com.thoughtworks.routing;

import com.thoughtworks.routing.reader.InputReader;
import com.thoughtworks.routing.service.impl.ProblemOneSolver;

import java.io.IOException;

/**
 * Main class. Takes arguments from a user input and runs until terminated.
 */
public class RoutingSolver {

    /**
     * Entry point to running code.
     * @param args any program arguements, none used
     * @throws IOException when user input fails
     */
    public static void main(final String[] args) throws IOException {
        InputReader inputReader = new InputReader();
        inputReader.setProblemOneSolver(new ProblemOneSolver());
        inputReader.start();
    }

}
