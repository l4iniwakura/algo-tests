package com.github;

import com.github.l4iniwakura.algo.RangeJoiner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeJoinerTest {

    private final RangeJoiner rangeJoiner = new RangeJoiner();

    /**
     * [1,4,5,2,3,9,8,11,0] => "0-5,8-9,11"
     */
    @Test
    void test1() {
        assertEquals("0-5,8-9,11", rangeJoiner.compress(new int[]{1, 4, 5, 2, 3, 9, 8, 11, 0}));
    }

    /**
     * [1,4,3,2] => "1-4"
     */
    @Test
    void test2() {
        assertEquals("1-4", rangeJoiner.compress(new int[]{1, 4, 3, 2}));
    }

    /**
     * [1,4] => "1,4"
     */
    @Test
    void test3() {
        assertEquals("1,4", rangeJoiner.compress(new int[]{1, 4}));
    }

}