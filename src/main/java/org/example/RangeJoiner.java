package org.example;

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
     */
    public String compress(int[] array) {
        Arrays.sort(array);

        List<String> result = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[end]) { // продолжаем
                end = i;
            } else {
                if (start == end) {
                    result.add(array[start] + "");
                } else {
                    result.add(array[start] + "-" + array[end]);
                }
                // Начинаем новый диапазон
                start = i;
                end = i;
            }
        }


        return String.join(",", result);
    }
}
