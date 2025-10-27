package org.example;

import java.util.*;

public class MaxConsecutiveRepeats {

    /**
     * Функция принимает на вход строку и возвращает карту символов и максимального количества
     * их последовательных повторений.
     *
     * @param input Входная строка.
     * @return Карта, где ключ — уникальный символ строки, а значение —
     * максимальное количество его последовательных повторений.
     * <p>
     * Input: "aaffbaaaafcz"
     * Output: {a=4, b=1, c=1, f=2, z=1}
     *
     * Complexity:
     * time: O(n)
     * space: O(n)
     */
    public static Map<Character, Integer> maxConsecutiveRepeats(String input) {
        var result = new HashMap<Character, Integer>();
        if (input == null || input.isEmpty()) {
            return result;
        }
        int l = 0, r = 0;
        while (l < input.length()) {
            if (r < input.length() - 1 && input.charAt(r + 1) == input.charAt(l)) {
                r++;
                continue;
            }
            result.merge(input.charAt(l), r - l + 1, Math::max);
            r++;
            l = r;
        }
        return result;
    }
}
