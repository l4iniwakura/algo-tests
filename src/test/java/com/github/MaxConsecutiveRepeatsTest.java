package com.github;

import com.github.l4iniwakura.algo.MaxConsecutiveRepeats;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxConsecutiveRepeatsTest {

    /**
     * Функция принимает на вход строку и возвращает карту символов и максимального количества
     * их последовательных повторений.
     * <p>
     * Input: "aaffbaaaafcz"
     * Output: {a=4, b=1, c=1, f=2, z=1}
     */
    @Test
    void maxConsecutiveRepeats_shouldWorkCorrectly() {
        String testString = "kaaffbaaaafcztttt";
        var expected = Map.of('a', 4, 'b', 1, 'c', 1, 'f', 2, 'z', 1, 'k', 1, 't', 4);
        var output = MaxConsecutiveRepeats.maxConsecutiveRepeats(testString);
        assertEquals(expected, output);
    }
}