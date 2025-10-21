package org.example;

import java.util.HashMap;
import java.util.Map;


public class BitMapper {
    /*
    BitMap
    00000001 = 1
    00000010 = 2
    00000011 = 3

    Complexity:
    time: O(n)
    space: O(n)
    */
    public int[] findThePrefixCommonArray(int[] a, int[] b) {
        int[] answer = new int[a.length];
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            final int finalI = i;
            frequency.compute(a[i], (_, bitMap) -> {
                if (bitMap == null) {
                    bitMap = 0;
                }
                return bitMap | 1;
            });
            frequency.compute(b[i], (_, bitMap) -> {
                if (bitMap == null) {
                    bitMap = 0;
                }
                return bitMap | 2;
            });
            frequency.computeIfPresent(a[i], (_, bitMap) -> {
                if (bitMap == 3) {
                    bitMap = bitMap | 4;
                    answer[finalI]++;
                }
                return bitMap;
            });
            frequency.computeIfPresent(b[i], (_, bitMap) -> {
                if (bitMap == 3) {
                    bitMap = bitMap | 4;
                    answer[finalI]++;
                }
                return bitMap;
            });

            answer[i] = answer[i] + (i - 1 < 0 ? 0 : answer[i - 1]);
        }
        return answer;
    }

    /*
    BitMap
    00000001 = 1
    00000010 = 2
    00000011 = 3

    Complexity:
    time: O(n)
    space: O(n)
    */
    public int[] anotherFrequencyCounter(int[] a, int[] b) {
        final int FIRST_OCCURRENCE = 0;
        final int OCCURRED_IN_A = 1;
        final int OCCURRED_IN_B = 2;
        final int OCCURRED_IN_BOTH = OCCURRED_IN_A | OCCURRED_IN_B;
        final int ALREADY_ACCOUNTED = 4;
        int[] answer = new int[a.length];
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            frequency.put(a[i], frequency.getOrDefault(a[i], FIRST_OCCURRENCE) | OCCURRED_IN_A);
            frequency.put(b[i], frequency.getOrDefault(b[i], FIRST_OCCURRENCE) | OCCURRED_IN_B);
            // Проверяем оба элемента одновременно
            if ((frequency.get(a[i]) ^ OCCURRED_IN_BOTH) == OCCURRED_IN_BOTH) {
                answer[i]++;
                frequency.put(a[i], frequency.get(a[i]) | ALREADY_ACCOUNTED);
            }
            if (a[i] != b[i] && (frequency.get(b[i]) ^ OCCURRED_IN_BOTH) == FIRST_OCCURRENCE) {
                answer[i]++;
                frequency.put(b[i], frequency.get(b[i]) | ALREADY_ACCOUNTED);
            }

            // Накопительная сумма
            answer[i] = answer[i] + (i > 0 ? answer[i - 1] : 0);
        }
        return answer;
    }
}
