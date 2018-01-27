package com.thoughworks.routing.model;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class DirectedGraph {

    Map<String, Map<String, Integer>> graphAttributes = new HashMap<>();

    public static DirectedGraph createGraphAttributes(final String graphInputString) {
        return new DirectedGraph();
    }

}
