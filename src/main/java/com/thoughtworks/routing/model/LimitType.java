package com.thoughtworks.routing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum LimitType {

    MAXIMUM("maximum"),
    EXACTLY("exactly");

    private String operation;

    public static LimitType fromOperation(final String operation) {
        return Stream.of(LimitType.values())
            .filter(enumeration -> enumeration.getOperation().equals(operation))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Not a valid operation"));
    }

}
