package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BitMapperTest {

    private final BitMapper bitMapper = new BitMapper();

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void findThePrefixCommonArray_shouldWorkCorrectly(int[] a, int[] b, int[] expected) {
        assertArrayEquals(expected, bitMapper.findThePrefixCommonArray(a, b));
    }

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void anotherFrequencyCounter_shouldWorkCorrectly(int[] a, int[] b, int[] expected) {
        assertArrayEquals(expected, bitMapper.anotherFrequencyCounter(a, b));
    }

    @Test
    void test() {
        assertEquals(1 | 2, 3);
        assertEquals(1 | 2 | 4, 7);
    }

    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{3, 4, 5, 6}, new int[]{0, 0, 1, 2}),
                Arguments.of(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}),
                Arguments.of(new int[]{10, 7, 9}, new int[]{1, 7, 3}, new int[]{0, 1, 1}),
                Arguments.of(new int[]{3, 4, 5, 6}, new int[]{1, 2, 3, 4}, new int[]{0, 0, 1, 2}),
                Arguments.of(new int[]{1}, new int[]{2}, new int[]{0})
        );
    }
}