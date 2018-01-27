package com.thoughtworks.routing;

import com.thoughtworks.routing.reader.InputReader;
import com.thoughtworks.routing.service.Solver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
        InputReader.start(getSolvers());
    }

    /**
     * Create all solvers for this application to inject them where needed.
     * @return the solvers in use
     */
    private static Set<Solver> getSolvers() {
        return new HashSet<>();
    }

}
