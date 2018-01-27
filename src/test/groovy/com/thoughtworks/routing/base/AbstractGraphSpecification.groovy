package com.thoughtworks.routing.base

import org.apache.commons.lang3.StringUtils
import spock.lang.Shared
import spock.lang.Specification

/**
 * Abstract class for specifications to share different graph structures across tests.
 */
abstract class AbstractGraphSpecification extends Specification {

    //Valid Graphs
    @Shared
            DEFAULT_INPUT_GRAPH = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7"
    @Shared
            SINGLE_EDGE_GRAPH = "AB5"
    @Shared
            CUSTOM_INPUT_GRAPH = "PR3, QR7, QS2, SQ5, ST1, SP6, TS4, TQ6"

    //Invalid Graphs
    @Shared
            EMPTY_GRAPH = StringUtils.EMPTY
    @Shared
            TOO_MANY_NODES_GRAPH = "ABC5"
    @Shared
            SINGLE_DIGIT_GRAPH = "5"
    @Shared
            WRONG_ORDER_GRAPH = "5GF"
    @Shared
            BAD_CHARACTER_GRAPH = "%PQ5"
    @Shared
            NO_DELIMITER_GRAPH = "AB5 BC4"
}
