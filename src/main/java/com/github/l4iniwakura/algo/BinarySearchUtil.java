package com.github.l4iniwakura.algo;

public class BinarySearchUtil {
    public static void main(String[] args) {

    }

    public static int classicalBinarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -low - 1; // insertion position convenience
    }

    /*
          l r
    1,2,3,4|7,7,7,8,9,10

     Expected index = 4
     */
    public static int binarySearchFirstOccurrence(int[] arr, int target) {
        int low = -1, high = arr.length - 1;
        while (high - low > 1) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high; // insertion position convenience
    }

    /*
                l r
    1,2,3,4,7,7,7|8,9,10

     Expected index = 4
     */
    public static int binarySearchLastOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (high - low > 1) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }
}