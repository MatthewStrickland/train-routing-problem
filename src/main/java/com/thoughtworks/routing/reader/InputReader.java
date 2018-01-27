package com.thoughtworks.routing.reader;

import com.thoughtworks.routing.model.DirectedGraph;
import com.thoughtworks.routing.model.ProblemOneInput;
import com.thoughtworks.routing.service.impl.ProblemOneSolver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * Class to ask for, parse, and process user inputs.
 */
public class InputReader {

    @Setter
    private ProblemOneSolver problemOneSolver;

    /**
     * Start reading user input and converse back and forth.
     * @throws IOException when user input fails
     */
    public void start() throws IOException {
        printWelcomeStatement();
        getUserInput();
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
     * @throws IOException when user input fails
     */
    private void getUserInput() throws IOException {
        final String graphInputString = ConsoleAsker.ask("Please input a valid directed graph in the format of 'AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7'");
        final DirectedGraph directedGraph = DirectedGraph.createGraphAttributes(graphInputString);

        solveBasedOnProblemType(directedGraph);
    }

    /**
     * Ask for the problem type and then pass to the solvers.
     * @param directedGraph the computed graph
     * @throws IOException when user input fails
     */
    private void solveBasedOnProblemType(final DirectedGraph directedGraph) throws IOException {
        final String problemType = ConsoleAsker.ask("Please input the type of problem you'd like to solve as a number, 1-4:");

        final ProblemType chosenProblem = ProblemType.values()[Integer.valueOf(problemType) - 1];
        final String inputStringToParse = ConsoleAsker.ask(chosenProblem.getRequestInput());

        if (ProblemType.ROUTE_DISTANCE == chosenProblem) {
            final ProblemOneInput build = ProblemOneInput.builder()
                .directedGraph(directedGraph)
                .input(inputStringToParse)
                .build();
            if (this.problemOneSolver.canSolve(build)) {
                System.out.println(String.format(chosenProblem.getSolutionFormat(),
                    inputStringToParse,
                    this.problemOneSolver.solve(build)));
            }
        }
        newGraph(directedGraph);
    }

    /**
     * Once solved, ask the user to reuse the graph, or create a new one from a new string.
     * @param directedGraph the current graph
     * @throws IOException when user input fails
     */
    private void newGraph(final DirectedGraph directedGraph) throws IOException {
        final String newGraph = ConsoleAsker.ask("Would you like to use a new graph? Y/N").toLowerCase();
        if ("n".equals(newGraph)) {
            solveBasedOnProblemType(directedGraph);
        } else if ("y".equals(newGraph)) {
            getUserInput();
        } else {
            newGraph(directedGraph);
        }
    }

    /**
     * Enum representing the type of problems available.
     */
    @AllArgsConstructor
    @Getter
    private enum ProblemType {

        /** Represents a problem such as "The distance of the route A-B-C". */
        ROUTE_DISTANCE("Please input the route in the hypen delimited format of A-B-C",
            "Distance found for %s: %s"),
        /** Represents a problem such as "The number of trips starting at C and ending at C with a maximum of 3 stops". */
        ROUTE_COUNT_STOPS("Please input the route in the format of 'C-C, maximum 3' or 'A-C, exactly 4'",
            "Number of trips found for %s: %s"),
        /** Represents a problem such as "The length of the shortest route (in terms of distance to travel) from A to C". */
        ROUTE_SHORTEST("Please input the route in the format hypen delimited format of A-C",
            "Shortest distance found for %s: %s"),
        /**
         * Represents a problem such as "The number of different routes from C to C with a distance of less than 30".
         * Note that maximum 29 is synonymous with less than 30.
         */
        ROUTE_COUNT_DISTANCE("Please input the route in the format of 'C-C, maximum 29' or 'A-C, exactly 40'",
            "Routes found for %s: %s");

        /**
         * The input the user will see in the console.
         */
        private String requestInput;

        /**
         * The format of the solution to display.
         */
        private String solutionFormat;

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
