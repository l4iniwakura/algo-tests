package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConflictFinderTest {

    private final ConflictFinder conflictFinder = new ConflictFinder();

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void findConflicts_shouldWorkCorrectly(List<ConflictFinder.Event> events, boolean expected) {
        var result = conflictFinder.findConflicts(events);
        assertEquals(expected, result);
    }


    /*
     * Есть список событий, у которых в наличии поля с id этого события, временем старта события и временем завершения события.
     * Нужно составить алгоритм выяснения, есть ли среди этих событий конфликтующие, то есть интервалы времени которых пересекаются.
     * Если время старта одного события совпадает со временем окончания другого, то такие события будем считать пересекающимися.
     * Примеры:
     *
     * Input: [{id="1", startTime=1, endTime=5}, {id="2", startTime=4, endTime=7}]
     * Output: true
     *
     * Input: [{id="1", startTime=1, endTime=5}, {id="2", startTime=7, endTime=9}, {id="3", startTime=4, endTime=7}]
     * Output: true
     *
     * Input: [{id="1", startTime=1, endTime=5}, {id="2", startTime=6, endTime=7}]
     * Output: false
     *
     * Input: []
     * Output: false
     */
    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                eventsWithConflict(
                        event("1", 1, 5),
                        event("2", 4, 7)
                ),
                eventsWithConflict(
                        event("1", 1, 5),
                        event("2", 7, 9),
                        event("3", 4, 7)
                ),
                eventsWithoutConflict(
                        event("1", 1, 5),
                        event("2", 6, 7)
                ),
                Arguments.of(new ArrayList<>(), false)
        );
    }

    private static Arguments eventsWithConflict(ConflictFinder.Event... events) {
        return Arguments.of(new ArrayList<>(List.of(events)), true);
    }

    private static Arguments eventsWithoutConflict(ConflictFinder.Event... events) {
        return Arguments.of(new ArrayList<>(List.of(events)), false);
    }

    private static ConflictFinder.Event event(String id, long start, long end) {
        return new ConflictFinder.Event(id, start, end);
    }

}