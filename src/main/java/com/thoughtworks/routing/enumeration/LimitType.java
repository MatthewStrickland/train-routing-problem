package com.thoughtworks.routing.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Limit type enum, representing the max or equality values.
 */
@AllArgsConstructor
@Getter
public enum LimitType {

    /** The maximum value for this enum. */
    MAXIMUM,
    /** The exact value for this enum. */
    EXACTLY

}
