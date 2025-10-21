package org.example;

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

import java.util.Comparator;
import java.util.List;

public class ConflictFinder {

    public boolean findConflicts(List<Event> events) {
        events.sort(Comparator.comparingLong(value -> value.startTime));
        for (int i = 1; i < events.size(); i++) {
            if (events.get(i).startTime <= events.get(i - 1).endTime) {
                return true;
            }
        }

        return false;
    }

    public record Event(String id, long startTime, long endTime) {
    }
}
