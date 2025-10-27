package com.github.l4iniwakura.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
 *
 * [1,3][1,4][2,10][15,18]
 *  ↑            ↑
 *  [[1,10][15,18]]
 */
public class IntervalMerger {

    /*
    Complexity:
    time: O(n log n)
    space: O(n)
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        var result = new ArrayList<int[]>();
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                result.add(prev);
                prev = interval;
            }
        }
        result.add(prev);
        return result.toArray(new int[result.size()][]);
    }
}
