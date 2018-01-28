package com.thoughtworks.routing.model;

import lombok.Builder;
import lombok.Data;

/**
 * Wrapper object representing the connection that will be made from a node.
 */
@Data
@Builder
public class Connection {

    /** The node this connection is going to. */
    String toNode;
    /** The distance to the node. */
    int distance;
}
