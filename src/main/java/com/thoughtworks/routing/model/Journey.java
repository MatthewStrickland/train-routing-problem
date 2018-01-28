package com.thoughtworks.routing.model;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Object representing the cumulative journey made/being made.
 */
@Builder(toBuilder = true)
@Getter
public class Journey {

    /** The node at the start of the journey. */
    private String startNode;
    /** The set of connections current traversed. */
    @Builder.Default
    private List<Connection> connections = new ArrayList<>();

    /**
     * Add a node to the set.
     * @param possibleDestination the possibleDestination to add
     * @return the created connection
     */
    public Connection addConnection(final Map.Entry<String, Integer> possibleDestination) {
        final Connection connection = Connection.builder()
            .toNode(possibleDestination.getKey())
            .distance(possibleDestination.getValue())
            .build();
        this.connections.add(connection);
        return connection;
    }

    /**
     * Publish this journey to a set of journeys.
     * @param toPublish the set of journeys to publish to
     */
    public void publish(final List<Journey> toPublish) {
        // Instantiate a new immutable list as this set of connections is now published
        // Use guava here as Collections library creates a view backed by the original list.
        final List<Connection> immutableConnections = ImmutableList.copyOf(getConnections());
        toPublish.add(this.toBuilder().connections(immutableConnections).build());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(getStartNode());
        Integer distance = 0;
        for (final Connection node : getConnections()) {
            builder.append("-");
            builder.append(node.getToNode());
            distance += node.getDistance();
        }
        builder.append(", distance: ");
        builder.append(distance);
        return builder.toString();
    }
}
