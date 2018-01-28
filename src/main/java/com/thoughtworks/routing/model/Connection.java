package com.thoughtworks.routing.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Wrapper object representing the connection that will be made from a node.
 */
@Builder
@Getter
public class Connection {

    /** The node this connection is going to. */
    private String toNode;
    /** The distance to the node. */
    private int distance;
}
