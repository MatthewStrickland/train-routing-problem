package com.thoughtworks.routing.base

import org.apache.commons.lang3.StringUtils
import spock.lang.Shared

/**
 * Abstract class for sample problem one inputs.
 */
class AbstractProblemOneSpec extends AbstractGraphSpecification {

    //Valid inputs
    @Shared
            QUESTION_1 = "A-B-C"
    @Shared
            QUESTION_2 = "A-D"
    @Shared
            QUESTION_3 = "A-D-C"
    @Shared
            QUESTION_4 = "A-E-B-C-D"
    @Shared
            QUESTION_5 = "A-E-D"
    @Shared
            SINGLE_CHARACTER_INPUT = "A"

    @Shared
            CUSTOM_ROUTE_1 = "Q-P"
    @Shared
            CUSTOM_ROUTE_2 = "S-P-R"
    @Shared
            CUSTOM_ROUTE_3 = "S-T-Q-S-P"

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
