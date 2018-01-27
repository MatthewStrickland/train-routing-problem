package com.thoughtworks.routing.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProblemOneInput implements ProblemInput {

    private String input;
    private DirectedGraph directedGraph;
    private String[] nodes;

    @Override
    public void parseInput() {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
