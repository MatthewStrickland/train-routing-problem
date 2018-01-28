package com.thoughtworks.routing.base

import org.apache.commons.lang3.StringUtils
import spock.lang.Shared

/**
 * Abstract class for sample problem four inputs.
 */
class AbstractProblemFourSpec extends AbstractGraphSpecification {

    //Valid inputs
    @Shared
            QUESTION_1 = "C-C, maximum 29"
    @Shared
            CUSTOM_QUESTION_2 = "A-C, exactly 9"
    @Shared
            CUSTOM_QUESTION_3 = "B-B, maximum 18"
    @Shared
            CUSTOM_QUESTION_4 = "B-B, maximum 17"
    @Shared
            CUSTOM_QUESTION_5 = "B-B, maximum 16"
    @Shared
            CUSTOM_QUESTION_6 = "A-D, exactly 4"

    @Shared
            CUSTOM_ROUTE_1 = "Q-P, exactly 8"
    @Shared
            CUSTOM_ROUTE_2 = "R-T, maximum 10"
    @Shared
            CUSTOM_ROUTE_3 = "T-R, exactly 13"
    @Shared
            CUSTOM_ROUTE_4 = "S-T, exactly 2"
    @Shared
            CUSTOM_ROUTE_5 = "S-Q, maximum 6"

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