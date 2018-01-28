package com.thoughtworks.routing.service.impl;

import com.thoughtworks.routing.model.DirectedGraph;
import com.thoughtworks.routing.model.input.ProblemThreeInput;
import com.thoughtworks.routing.service.Solver;

import java.util.*;

/**
 * Solver implementation for problem three.
 */
public class ProblemThreeSolver implements Solver<ProblemThreeInput> {

    /** Return value when no route can be found. */
    private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    @Override
    public String solve(final ProblemThreeInput parameters) {
        final String[] nodes = parameters.getNodes();
        final DirectedGraph directedGraph = parameters.getDirectedGraph();
        final String start = nodes[0];
        final String destination = nodes[1];

        final Map<String, Integer> currentShortestPaths = createDijikstraTable(directedGraph);
        // Set the start node to 0
        currentShortestPaths.put(start, 0);

        final Set<String> visitedSet = new HashSet<>();
        simpleDijikstra(directedGraph, currentShortestPaths, start, visitedSet, destination);
        return Optional.ofNullable(currentShortestPaths.get(destination)).map(Object::toString).orElse(NO_SUCH_ROUTE);
    }

    /**
     * Creates the table needed that will constantly be updated.
     * @param directedGraph the directed graph
     * @return the map representing the tale
     */
    private static Map<String, Integer> createDijikstraTable(final DirectedGraph directedGraph) {
        final Map<String, Integer> currentShortestPaths = new HashMap<>();
        directedGraph.getGraphAttributes().keySet().forEach(key -> currentShortestPaths.put(key, null));
        return currentShortestPaths;
    }

    /**
     * Recursive function following a basic implementation of the Dijikstra algorithm.
     * @param directedGraph the directed graph
     * @param currentShortestPaths the dijikstra table
     * @param start the start node we are evaluating from
     * @param visitedSet the set of previously visited nodes
     * @param destination the destination node
     */
    private static void simpleDijikstra(final DirectedGraph directedGraph,
                                        final Map<String, Integer> currentShortestPaths,
                                        final String start, final Set<String> visitedSet, final String destination) {
        // Terminate when we have visited all the nodes or if our start value isn't in our dijikstra table
        if (containsAndNotNull(currentShortestPaths, start) && visitedSet.size() != currentShortestPaths.keySet().size()) {
            compareDistanceAndReplace(directedGraph, currentShortestPaths, start);
            visitedSet.add(start);
            // Calculate the next current smallest node that hasn't been visited and not infinity
            final Optional<Map.Entry<String, Integer>> optionalSmallest =
                currentShortestPaths.entrySet().stream()
                    .filter(node -> !visitedSet.contains(node.getKey()) && node.getValue() != null)
                    .min(Comparator.comparingInt(Map.Entry::getValue));
            // Continue to next node if there is one available
            optionalSmallest.ifPresent(smallest -> {
                // No need to continue if the next smallest node is our destination
                if (!smallest.getKey().equals(destination) ) {
                    simpleDijikstra(directedGraph, currentShortestPaths, smallest.getKey(), visitedSet, destination);
                }
            });
        }
    }

    /**
     * Compare the running total of this path to possibilities. If any are shorter replace the running total.
     * @param directedGraph the directed graph
     * @param currentShortestPaths the dijikstra table
     * @param start the start node
     */
    private static void compareDistanceAndReplace(final DirectedGraph directedGraph,
                                                  final Map<String, Integer> currentShortestPaths,
                                                  final String start) {
        final Map<String, Integer> possibleDestinations = directedGraph.get(start);
        if (possibleDestinations != null) {
            // For each possible edge to this node, check if the distance of this edge plus the value of how
            // we arrived at the start node is smaller than the current value in the table, if so then replace it
            possibleDestinations.forEach((toNode, distance) -> {
                final int valueToCompare = currentShortestPaths.get(start) + distance;
                final Integer currentShortest = currentShortestPaths.get(toNode);
                final boolean currentInfinity = currentShortestPaths.containsKey(toNode) && (currentShortest == null || currentShortest == 0);
                // If the to node we are currently looking at is infinity in our table, then always replace
                // or if there is a real value then compare it
                // and if the new route is shorter then replace it
                if (currentInfinity ||
                    (containsAndNotNull(currentShortestPaths, toNode) && (currentShortest > valueToCompare))) {
                    currentShortestPaths.put(toNode, valueToCompare);
                }
            });
        }
    }

    /**
     * Check if the given map contains the given node, and if it does than the value isn't set to 'infinity'.
     * @param map the map to check
     * @param node the node
     * @return true if the map contains a real value for this node
     */
    private static boolean containsAndNotNull(final Map<String,Integer> map, final String node) {
        return map.containsKey(node) && map.get(node) != null;
    }

}
