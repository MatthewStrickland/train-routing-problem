package com.thoughtworks.routing.model

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
            INVALID_GRAPH_1 = "ABC5"
    @Shared
            INVALID_GRAPH_2 = "5"
    @Shared
            INVALID_GRAPH_3 = "5GF"
    @Shared
            INVALID_GRAPH_4 = "%PQ5"
    @Shared
            INVALID_GRAPH_5 = "AB5 BC4"
}
