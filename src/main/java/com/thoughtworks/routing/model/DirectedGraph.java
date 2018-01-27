package com.thoughtworks.routing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * A wrapper for an inputted graph string parsed to an accessible structure.
 */
@NoArgsConstructor
@Getter
@Log4j2
public class DirectedGraph {

    /** The valid regex validating elemts of the string, ie. AB5. */
    public static final Pattern VALID_GRAPH_PATTERN = Pattern.compile("[A-Za-z]{2}\\d+");
    /** The expected delimiter on the inputted graph string. */
    public static final String DELIMITER = ", ";

    /** The accessible map representing the graph structure. */
    Map<String, Map<String, Integer>> graphAttributes = new HashMap<>();

    /**
     * Create a DirectedGraph from a string
     * @param graphInputString the input string to parse
     * @return a graph wrapping a structure
     * @throws IllegalArgumentException when the string is invalid
     */
    public static DirectedGraph createGraphAttributes(final String graphInputString) {
        final DirectedGraph directedGraph = new DirectedGraph();
        Stream.of(graphInputString.split(DELIMITER)).forEach(info -> {
            final Matcher m = VALID_GRAPH_PATTERN.matcher(info);
            if (m.matches()) {
                final String start = info.substring(0, 1);
                final String dest = info.substring(1, 2);
                final Integer length = Integer.valueOf(info.substring(2, info.length()));
                directedGraph.getGraphAttributes()
                    .computeIfAbsent(start, key -> new HashMap<>())
                    .put(dest, length);
            } else {
                log.error("Graph " + graphInputString + " is invalid");
                throw new IllegalArgumentException("Unable to parse inputted graph");
            }
        });
        return directedGraph;
    }

    /**
     * Gets the possible node destinations for a given node.
     * @param node the node
     * @return the destinations for the node
     */
    public Map<String, Integer> get(final String node) {
        return this.graphAttributes.get(node);
    }

}
