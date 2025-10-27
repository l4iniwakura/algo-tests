package com.github.l4iniwakura.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RangeJoiner {

    /**
     * Дан список интов - только положительные и 0, повторяющихся элементов в списке нет.
     * Нужно преобразовать это множество в строку, сворачивая соседние по числовому ряду числа в диапазоны.
     * Примеры:
     * [1,4,5,2,3,9,8,11,0] => "0-5,8-9,11"
     * [1,4,3,2] => "1-4"
     * [1,4] => "1,4"


     * [0,1,2,3,4,5,8,9,11]
     *  ↑
     *  Complexity:
     *  time: O(n log n) - из-за сортировки
     *  space: O(n) - сортировка и сохранение результата
     */
    public String compress(int[] array) {
        Arrays.sort(array);

        List<String> result = new ArrayList<>();
        int start = 0, end = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] - 1 == array[end]) {
                end = i;
            } else {
                if (start == end) {
                    result.add(array[start] + "");
                } else {
                    result.add(array[start] + "-" + array[end]);
                }
                start = i;
                end = i;
            }
        }
        if (start == end) {
            result.add(array[start] + "");
        } else {
            result.add(array[start] + "-" + array[end]);
        }

        return String.join(",", result);
    }
}
