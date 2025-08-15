/*
 * MIT License
 *
 * Copyright (c) 2025 Geekific (https://www.youtube.com/c/Geekific)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice, Geekific's channel link and this permission notice
 * shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.youtube.geekific;

import java.util.*;

/*
 * Video Reference: https://youtu.be/5VjQD62gOYA
 * LeetCode Reference: https://leetcode.com/problems/sliding-window-maximum/
 */
public class _0239_SlidingWindowMaximum {

    public int[] maxSlidingWindow_Intuitive(int[] nums, int k) {
        if (k == 1) return nums;
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i <= nums.length - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    public int[] maxSlidingWindow_IntuitiveOptimized(int[] nums, int k) {
        if (k == 1) return nums;
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i <= nums.length - k; i++) {
            if (i > 0 && result[i - 1] != nums[i - 1]) {
                result[i] = Math.max(result[i - 1], nums[i + k - 1]);
            } else {
                int max = Integer.MIN_VALUE;
                for (int j = i; j - i < k; j++) {
                    max = Math.max(max, nums[j]);
                }
                result[i] = max;
            }
        }
        return result;
    }

    public int[] maxSlidingWindow_Deque(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int resIndex = 0;
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (!queue.isEmpty() && queue.peekFirst() == i - k) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (i >= k - 1) {
                result[resIndex++] = nums[queue.peekFirst()];
            }
        }
        return result;
    }

    public int[] maxSlidingWindow_Heaps(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        Arrays.stream(nums, 0, k).forEach(queue::add);
        result[0] = queue.peek();
        for (int i = k; i < nums.length; i++) {
            queue.remove(nums[i - k]);
            queue.add(nums[i]);
            result[i - k + 1] = queue.peek();
        }
        return result;
    }

}
