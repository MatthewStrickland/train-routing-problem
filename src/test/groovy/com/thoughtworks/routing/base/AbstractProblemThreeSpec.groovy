package com.thoughtworks.routing.base

import org.apache.commons.lang3.StringUtils
import spock.lang.Shared

/**
 * Abstract class for sample problem three inputs.
 */
class AbstractProblemThreeSpec extends AbstractGraphSpecification {

    //Valid inputs
    @Shared
            QUESTION_1 = "A-C"
    @Shared
            QUESTION_2 = "B-B"
    @Shared
            CUSTOM_QUESTION_3 = "C-D"
    @Shared
            CUSTOM_QUESTION_4 = "E-D"
    @Shared
            CUSTOM_QUESTION_5 = "C-A"
    @Shared
            SINGLE_CHARACTER_INPUT = "A"

    @Shared
            CUSTOM_ROUTE_1 = "Q-P"
    @Shared
            CUSTOM_ROUTE_2 = "R-T"
    @Shared
            CUSTOM_ROUTE_3 = "S-Q"
    @Shared
            CUSTOM_ROUTE_4 = "P-S"

    //Invalid inputs
    @Shared
            EMPTY_INPUT = StringUtils.EMPTY
    @Shared
            NO_HYPHEN_INPUT = "AD"
    @Shared
            TOO_MANY_HYPENS_INPUT = "A--C"
    @Shared
            BAD_CHARACTER_INPUT = "A-%"
    @Shared
            NO_DELIMITER_INPUT = "A B"
}
