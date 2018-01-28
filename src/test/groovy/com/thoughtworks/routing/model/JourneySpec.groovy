package com.thoughtworks.routing.model

import com.thoughtworks.routing.base.AbstractGraphSpecification
import spock.lang.Unroll
/**
 * Test class for DirectedGraph.
 */
class JourneySpec extends AbstractGraphSpecification {

    @Unroll
    def "test to string for a Journey #journey"(Journey journey, String expectedToString) {
        given: "a journey with a valid structure"

        when: "we transform the obecct to a string"
        def toString = journey.toString()

        then: "no exceptions are thrown during parsing, and result is expected"
        noExceptionThrown()
        toString == expectedToString

        where:
        journey                                                                    | expectedToString
        Journey.builder().startNode("A").connections([] as List).build() | "A, distance: 0"
        Journey.builder()
                .startNode("A")
                .connections([Connection.builder()
                                      .toNode("B")
                                      .distance(3)
                                      .build()] as List).build()                   | "A-B, distance: 3"
    }

    def "publishing a journey creates a new list"() {
        given: "a journey with a valid structure"
        def connections = [Connection.builder()
                                   .toNode("B")
                                   .distance(3)
                                   .build()] as List
        def journey = Journey.builder()
                .startNode("A")
                .connections(connections).build()

        List<Journey> toPublish = [] as List

        when: "we publish the object"
        journey.publish(toPublish)

        then: "a new connection list is created in the publish list"
        toPublish.size() == 1
        !toPublish.get(0).connections.is(connections)
    }

    def "publishing a journey creates a new list and is immutable"() {
        given: "a journey with a valid structure"
        def connections = [Connection.builder()
                                   .toNode("B")
                                   .distance(3)
                                   .build()] as List
        def journey = Journey.builder()
                .startNode("A")
                .connections(connections).build()

        List<Journey> toPublish = [] as List

        and: "we publish the object"
        journey.publish(toPublish)

        when: "we attempt to add to the published list"
        toPublish.get(0).connections.add(Connection.builder().build())

        then: "an exception is thrown"
        thrown(UnsupportedOperationException)
    }

}
