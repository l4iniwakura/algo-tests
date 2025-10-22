package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class IntervalMergerTest {

    private final IntervalMerger intervalMerger = new IntervalMerger();

    /*
    Example 1:

    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
     */
    @Test
    void test() {
        var result = intervalMerger.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        var expected = new int[][]{{1, 6}, {8, 10}, {15, 18}};
        assertArrayEquals(expected, result);
    }

    /*
    Example 2:

    Input: intervals = [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     */
    @Test
    void test2() {
        var result = intervalMerger.merge(new int[][]{{1, 4}, {4, 5}});
        var expected = new int[][]{{1, 5}};
        assertArrayEquals(expected, result);
    }

    /*
    Example 3:

    Input: intervals = [[4,7],[1,4]]
    Output: [[1,7]]
    Explanation: Intervals [1,4] and [4,7] are considered overlapping.
     */
    @Test
    void test3() {
        var result = intervalMerger.merge(new int[][]{{4, 7}, {1, 4}});
        var expected = new int[][]{{1, 7}};
        assertArrayEquals(expected, result);
    }

    /*
    Example 4:

    Input: intervals = [[1,4],[2,3]]
    Output: [[1,4]]
     */
    @Test
    void test4() {
        var result = intervalMerger.merge(new int[][]{{1, 4}, {2, 3}});
        var expected = new int[][]{{1, 4}};
        assertArrayEquals(expected, result);
    }

    /*
    Example 5:

    Input: intervals = [[2,4],[5,6],[7,8],[9,10]][1,11]
    Output: [[1,11]]
     */
    @Test
    void test5() {
        var result = intervalMerger.merge(new int[][]{{2, 4}, {5, 6}, {7, 8}, {9, 10}, {1, 11}});
        var expected = new int[][]{{1, 11}};
        assertArrayEquals(expected, result);
    }

}