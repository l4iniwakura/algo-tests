package com.github;

import com.github.l4iniwakura.algo.TargetFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* <p>
 * Напиши функцию, принимающую массив из положительных неупорядоченных чисел первым аргументом
 * и положительное число вторым аргументом. Функция должна возвращать true, если в массиве
 * есть 2 числа, которые в сумме дают 2-й аргумент.
 * <p>
 * Input: ([10, 15, 3, 7], 17)
 * Output: true
 */
class TargetFinderTest {

    @Test
    void test1() {
        var exist = TargetFinder.hasPairWithSum(new int[]{10, 15, 3, 7}, 17);
        assertTrue(exist);
    }

    @Test
    void test2() {
        var exist = TargetFinder.hasPairWithSum(new int[]{10, 15, 3, 7}, 25);
        assertTrue(exist);
    }

    @Test
    void test3() {
        var exist = TargetFinder.hasPairWithSum(new int[]{10, 15, 3, 7}, 11);
        assertFalse(exist);
    }

    @Test
    void test4() {
        var exist = TargetFinder.hasPairWithSum(new int[]{5}, 10);
        assertFalse(exist);
    }
}