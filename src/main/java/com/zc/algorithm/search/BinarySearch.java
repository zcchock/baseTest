package com.zc.algorithm.search;

/**
 * @author chock
 * @since 2020/3/15
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 5, 7, 12, 15, 23, 31, 46, 78, 99};
        System.out.println(binarySearch(arr, 7));
    }

    private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
