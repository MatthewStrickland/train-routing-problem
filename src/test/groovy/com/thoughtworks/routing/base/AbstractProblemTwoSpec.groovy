package com.thoughtworks.routing.base

import org.apache.commons.lang3.StringUtils
import spock.lang.Shared

/**
 * Abstract class for sample problem two inputs.
 */
class AbstractProblemTwoSpec extends AbstractGraphSpecification {

    //Valid inputs
    @Shared
            QUESTION_1 = "C-C, maximum 3"
    @Shared
            QUESTION_2 = "A-C, exactly 4"
    @Shared
            CUSTOM_QUESTION_3 = "C-C, exactly 2"
    @Shared
            CUSTOM_QUESTION_4 = "B-A, maximum 10"
    @Shared
            CUSTOM_QUESTION_5 = "A-D, maximum 5"

    @Shared
            CUSTOM_ROUTE_1 = "Q-P, exactly 2"
    @Shared
            CUSTOM_ROUTE_2 = "R-T, maximum 10"
    @Shared
            CUSTOM_ROUTE_3 = "T-R, maximum 1"
    @Shared
            CUSTOM_ROUTE_4 = "S-S, exactly 2"
    @Shared
            CUSTOM_ROUTE_5 = "S-Q, maximum 3"

    //Invalid inputs
    @Shared
            EMPTY_INPUT = StringUtils.EMPTY
    @Shared
            ONLY_ROUTE_INPUT = "C-C"
    @Shared
            SHORTHAND_INPUT = "C-C, max 3"
    @Shared
            TOO_MANY_HYPENS_INPUT = "A--C, exactly 3"
    @Shared
            BAD_CHARACTER_INPUT = "A-%, maximum 4"
    @Shared
            NO_DELIMITER_INPUT_1 = "A B, exactly 3"
    @Shared
            NO_DELIMITER_INPUT_2 = "A-B exactly 3"
    @Shared
            WRONG_ORDER_INPUT = "exactly 3, A-B"
}
