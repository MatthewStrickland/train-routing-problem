package com.thoughtworks.routing.reader;

import com.thoughtworks.routing.model.DirectedGraph;
import com.thoughtworks.routing.service.Solver;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.Set;

/**
 * Class to ask for, parse, and process user inputs.
 */
public class InputReader {

    /**
     * Start reading user input and converse back and forth.
     * @param solvers the solvers which will be used based on input
     * @throws IOException when user input fails
     */
    public static void start(final Set<Solver> solvers) throws IOException {
        printWelcomeStatement();
        getUserInput(solvers);
    }

    /**
     * Prints the statements.
     * Forwards information to the user for later use in the asking process.
     */
    private static void printWelcomeStatement() {
        System.out.println("Welcome to the train problem solver!");
        System.out.println("There are 4 types of questions to ask:");
        System.out.println("1. Route distance");
        System.out.println("2. Number of routes between two nodes with an inclusive upper limit of stops");
        System.out.println("3. Length of the shortest route");
        System.out.println("4. Number of routes between two nodes with an inclusive upper limit of distance");
    }

    /**
     * Start asking for user inputs.
     * Firstly we ask for a valid graph.
     * @param solvers the injected solvers
     * @throws IOException when user input fails
     */
    private static void getUserInput(final Set<Solver> solvers) throws IOException {
        final String graphInputString = ConsoleAsker.ask("Please input a valid directed graph in the format of 'AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7'");
        final DirectedGraph directedGraph = DirectedGraph.createGraphAttributes(graphInputString);

        solveBasedOnProblemType(directedGraph, solvers);
    }

    /**
     * Ask for the problem type and then pass to the solvers.
     * @param directedGraph the computed graph
     * @param solvers the injected solvers
     * @throws IOException when user input fails
     */
    private static void solveBasedOnProblemType(final DirectedGraph directedGraph, final Set<Solver> solvers) throws IOException {
        final String problemType = ConsoleAsker.ask("Please input the type of problem you'd like to solve as a number, 1-4:");

        final ProblemType chosenProblem = ProblemType.values()[Integer.valueOf(problemType) - 1];
        final String inputStringToParse = ConsoleAsker.ask(chosenProblem.getRequestInput());

        //Solve here

        newGraph(directedGraph, solvers);
    }

    /**
     * Once solved, ask the user to reuse the graph, or create a new one from a new string.
     * @param directedGraph the current graph
     * @param solvers the injected solvers
     * @throws IOException when user input fails
     */
    private static void newGraph(final DirectedGraph directedGraph, final Set<Solver> solvers) throws IOException {
        final String newGraph = ConsoleAsker.ask("Would you like to use a new graph? Y/N").toLowerCase();
        if ("n".equals(newGraph)) {
            solveBasedOnProblemType(directedGraph, solvers);
        } else if ("y".equals(newGraph)) {
            getUserInput(solvers);
        } else {
            newGraph(directedGraph, solvers);
        }
    }

    /**
     * Enum representing the type of problems available.
     */
    @AllArgsConstructor
    @Getter
    private enum ProblemType {

        /** Represents a problem such as "The distance of the route A-B-C". */
        ROUTE_DISTANCE("Please input the route in the hypen delimited format of A-B-C"),
        /** Represents a problem such as "The number of trips starting at C and ending at C with a maximum of 3 stops". */
        ROUTE_COUNT_STOPS("Please input the route in the format of 'C-C, maximum 3' or 'A-C, exactly 4'"),
        /** Represents a problem such as "The length of the shortest route (in terms of distance to travel) from A to C". */
        ROUTE_SHORTEST("Please input the route in the format hypen delimited format of A-C"),
        /**
         * Represents a problem such as "The number of different routes from C to C with a distance of less than 30".
         * Note that maximum 29 is synonymous with less than 30.
         */
        ROUTE_COUNT_DISTANCE("Please input the route in the format of 'C-C, maximum 29' or 'A-C, exactly 40'");

        private String requestInput;

    }

    /**
     * Wrapper to ask the console for input.
     */
    private static class ConsoleAsker {

        /** The reader. */
        private static final java.io.BufferedReader READER
            = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        /**
         * Ask the console with a message.
         *
         * @param message the message
         * @return the input
         * @throws IOException if the input fails
         */
        static String ask(final String message) throws IOException {
            System.out.println(message);
            return READER.readLine();
        }
    }

}
