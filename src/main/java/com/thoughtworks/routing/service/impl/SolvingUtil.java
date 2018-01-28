package com.thoughtworks.routing.service.impl;

import com.thoughtworks.routing.model.Connection;
import com.thoughtworks.routing.model.DirectedGraph;
import com.thoughtworks.routing.model.Journey;
import com.thoughtworks.routing.model.input.ProblemInput;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class
 */
public final class SolvingUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private SolvingUtil() {
        //Do nothing
    }

    /**
     * Find the possible journeys up to the limit value (inclusive), or exactly the limit value.
     * @param parameters the parameters holding node information
     * @param limit the limit (inclusive)
     * @param exactly true if we are to match the limit value only
     * @param limitFunction the function to determine the cumulative value to compare with the limit
     * @return the list of journeys found
     */
    public static List<Journey> findPossibleJourneys(final ProblemInput parameters,
                                                     final int limit,
                                                     final boolean exactly,
                                                     final Function<Journey, Integer> limitFunction) {

        final NextDepthParameter nextDepthParameter = NextDepthParameter.builder()
            .directedGraph(parameters.getDirectedGraph())
            .currentRoute(Journey.builder().startNode(parameters.getNodes()[0]).build())
            .startNode(Connection.builder().toNode(parameters.getNodes()[0]).build())
            .targetNode(parameters.getNodes()[1])
            .build();
        final List<Journey> foundRoutes = new ArrayList<>();
        traversePossibleRoutesUntilConditionMet(nextDepthParameter, limit, exactly, limitFunction, foundRoutes);
        return foundRoutes;
    }

    /**
     * Recursion function. Each iteration goes deeper into the directed graph. The end point is when there are
     * no further paths to get to (terminal node), or the limit has been reached.
     * @param nextDepthParameter the parameters holding node information
     * @param limit the limit (inclusive)
     * @param exactly true if we are to match the limit value only
     * @param limitFunction the function to determine the cumulative value to compare with the limit
     * @param foundRoutes the object holding the routes we have found
     */
    private static void traversePossibleRoutesUntilConditionMet(final NextDepthParameter nextDepthParameter,
                                                                final int limit, final boolean exactly,
                                                                final Function<Journey, Integer> limitFunction,
                                                                final List<Journey> foundRoutes) {
        // Get the relevant parameters
        final DirectedGraph directedGraph = nextDepthParameter.getDirectedGraph();
        final String toNode = nextDepthParameter.getStartNode().getToNode();
        final Map<String, Integer> possibleDestinations = directedGraph.get(toNode);
        final Journey currentRoute = nextDepthParameter.getCurrentRoute();
        final String targetNode = nextDepthParameter.getTargetNode();

        // Decide if publishable (add to foundRoutes - route satisfies limit constraints and end node)
        final Integer cumulativeLimit = limitFunction.apply(currentRoute);
        publishIfValid(limit, exactly, foundRoutes, toNode, currentRoute, targetNode, cumulativeLimit);

        // Recursively continue if it is possible to go deeper
        if (cumulativeLimit <= limit && possibleDestinations != null) {
            for (final Map.Entry<String, Integer> possibleDestination : possibleDestinations.entrySet()) {
                // Create the connection between this node and the last, and addd it to our current routing
                // Go deeper with the created connection as our new starting point
                traversePossibleRoutesUntilConditionMet(
                    nextDepthParameter.toBuilder()
                        .startNode(currentRoute.addConnection(possibleDestination))
                        .build(),
                    limit, exactly, limitFunction, foundRoutes);
                // Remove the created connection from the route as we have exhausted that path
                currentRoute.getConnections().remove(currentRoute.getConnections().size() -1);
            }
        }
    }

    /**
     * Determine if the current route is publisable (add to found routes).
     * @param limit the limit to check against
     * @param exactly true if we are to match the limit value only
     * @param foundRoutes the object holding the routes we have found
     * @param toNode the node we are currently at
     * @param currentRoute the current route we are on
     * @param targetNode the target node we terminate at
     * @param cumulativeLimit the value (distance, trips, etc) we are cumulatively at
     */
    private static void publishIfValid(final int limit, final boolean exactly, final List<Journey> foundRoutes,
                                       final String toNode, final Journey currentRoute, final String targetNode,
                                       final Integer cumulativeLimit) {
        final boolean publishable =
            cumulativeLimit <= limit &&
            (!exactly || limit == cumulativeLimit) &&
            !currentRoute.getConnections().isEmpty() &&
            toNode.equals(targetNode);

        if (publishable) {
            currentRoute.publish(foundRoutes);
        }
    }

    /**
     * Parameter object to pass through. Created mainly for refactoring signature length.
     */
    @Builder(toBuilder = true)
    @Getter
    private static class NextDepthParameter {

        /** The directed graph. */
        private DirectedGraph directedGraph;
        /** The start node. */
        private Connection startNode;
        /** The current node. */
        private Journey currentRoute;
        /** The target node. */
        private String targetNode;

    }
}
