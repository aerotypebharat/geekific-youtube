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
 * Video Reference: https://youtu.be/SdURPlHqc1g
 * LeetCode Reference: https://leetcode.com/problems/find-median-from-data-stream/
 */
public class _0295_FindMedianFromDataStream {

    static class MedianFinder_Sorting {
        private final List<Integer> list = new ArrayList<>();
        private boolean even = true;

        public void addNum(int num) {
            list.add(num);
            even = !even;
        }

        public double findMedian() {
            Collections.sort(list);
            int size = list.size();
            return even ? (list.get(size / 2 - 1) + list.get(size / 2)) / 2.0
                    : list.get(size / 2);
        }
    }

    static class MedianFinder_Heap {
        private final Queue<Integer> minHeap = new PriorityQueue<>();
        private final Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        private boolean even = true;

        public double findMedian() {
            return even ? (maxHeap.peek() + minHeap.peek()) / 2.0 : minHeap.peek();
        }

        public void addNum(int num) {
            if (even) {
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            } else {
                minHeap.offer(num);
                maxHeap.offer(minHeap.poll());
            }
            even = !even;
        }
    }


}
