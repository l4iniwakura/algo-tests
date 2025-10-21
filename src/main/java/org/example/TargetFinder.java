package org.example;

import java.util.HashSet;
import java.util.Set;

/* <p>
 * Напиши функцию, принимающую массив из положительных неупорядоченных чисел первым аргументом
 * и положительное число вторым аргументом. Функция должна возвращать true, если в массиве
 * есть 2 числа, которые в сумме дают 2-й аргумент.
 * <p>
 * Input: ([10, 15, 3, 7], 17)
 * Output: true
 */
public class TargetFinder {
    public static boolean hasPairWithSum(int[] numbers, int target) {
        Set<Integer> occurredNums = new HashSet<>();
        for (int num : numbers) {
            if (occurredNums.contains(target - num)) {
                return true;
            }
            occurredNums.add(num);
        }
        return false;
    }
}


