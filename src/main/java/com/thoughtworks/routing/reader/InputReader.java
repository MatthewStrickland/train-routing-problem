package com.thoughtworks.routing.reader;

import com.thoughtworks.routing.enumeration.ProblemType;
import com.thoughtworks.routing.model.DirectedGraph;
import com.thoughtworks.routing.service.OrchestrationService;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Class to ask for, parse, and process user inputs.
 */
@AllArgsConstructor
public class InputReader {

    /** The orchestrator which pushes the inputs to a specific solver. */
    private OrchestrationService orchestrator;

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
        final ProblemType[] problemTypes = ProblemType.values();
        System.out.println(String.format("There are %d types of questions to ask:", problemTypes.length));
        for (final ProblemType problemType : problemTypes) {
            System.out.println(problemType.getExplanation());
        }
    }

    /**
     * Start asking for user inputs.
     * Firstly we ask for a valid graph.
     * @throws IOException when user input fails
     */
    private void getUserInput() throws IOException {
        final String graphInputString =
            ask("Please input a valid directed graph in the format of 'AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7'");
        final DirectedGraph directedGraph = DirectedGraph.createGraphAttributes(graphInputString);

        solveBasedOnProblemType(directedGraph);
    }

    /**
     * Ask for the problem type and then pass to the solvers.
     * @param directedGraph the computed graph
     * @throws IOException when user input fails
     */
    private void solveBasedOnProblemType(final DirectedGraph directedGraph) throws IOException {
        final String problemType = ask("Please input the type of problem you'd like to solve as a number, 1-4:");

        final ProblemType chosenProblem = ProblemType.values()[Integer.parseInt(problemType) - 1];
        final String inputStringToParse = ask(chosenProblem.getRequestInput());

        this.orchestrator.orchestrate(directedGraph, chosenProblem, inputStringToParse);

        newGraph(directedGraph);
    }

    /**
     * Once solved, ask the user to reuse the graph, or create a new one from a new string.
     * @param directedGraph the current graph
     * @throws IOException when user input fails
     */
    private void newGraph(final DirectedGraph directedGraph) throws IOException {
        final String newGraph = ask("Would you like to use a new graph (Y/N), or exit (E)?").toLowerCase();
        if ("n".equals(newGraph)) {
            solveBasedOnProblemType(directedGraph);
        } else if ("y".equals(newGraph)) {
            getUserInput();
        } else if (!"e".equals(newGraph)) {
            newGraph(directedGraph);
        }
    }

    /**
     * Ask the console with a message.
     *
     * @param message the message
     * @return the input
     * @throws IOException if the input fails
     */
    private static String ask(final String message) throws IOException {
        System.out.println(message);
        return new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset())).readLine();
    }

}
