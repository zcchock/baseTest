package com.zc.algorithm.sort;

import java.util.Arrays;

/**
 * @author chock
 * @since 2020/3/15
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {3,1,6,9,12,4,55,23,11,2};
        arr = bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static int[] bubbleSort(int[] arr) {
        int i, j, temp;
        int len = arr.length;

        for (i = 0; i < len; i++) {
            for (j = 1; j < len - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
