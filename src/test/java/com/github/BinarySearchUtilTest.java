package com.github;

import com.github.l4iniwakura.algo.BinarySearchUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchUtilTest {

    @ParameterizedTest(
            name = """
                     Классический бинарный поиск должен возвращать\s
                     индекс искомого элемента если он присутствует\s
                     в исходном отсортированном массиве
                    \s""" + ParameterizedTest.ARGUMENTS_PLACEHOLDER
    )
    @ValueSource(ints = {10, 20, 30, 40, 50})
    void classicalBinarySearch_shouldReturnCorrectIndex_whenFindingExistingTarget(int n) {
        var array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        var target = new Random().nextInt(n);
        var index = BinarySearchUtil.classicalBinarySearch(array, target);

        assertEquals(index, target - 1);
    }

    @Test
    @Timeout(value = 1)
    void classicalBinarySearch_shouldReturnConvenienceInsertIndex_whenTargetNotExist() {
        var array = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
        var index = BinarySearchUtil.classicalBinarySearch(array, 5);

        assertTrue(index < 0);
        assertEquals(4, -index - 1);
    }

    // fixe me please
    @Test
    @Timeout(value = 1)
    void binarySearchFirstOccurrence_shouldReturnCorrectIndexOfFirstOccurrence_whenFindingExistingTarget() {
        var array = new int[]{1};
        var expected = 3;
        var actual = BinarySearchUtil.binarySearchFirstOccurrence(array, 2);
        assertEquals(expected, actual);
    }

    // fixe me please
    @Test
    @Timeout(value = 1)
    void binarySearchLastOccurrence_shouldReturnCorrectIndexOfLastOccurrence_whenFindingExistingTarget() {
        var array = new int[]{1,2,3,4,7,7,7,8,9,10};
        var expected = 6;
        var actual = BinarySearchUtil.binarySearchLastOccurrence(array, 2);
        assertEquals(expected, actual);
    }


}