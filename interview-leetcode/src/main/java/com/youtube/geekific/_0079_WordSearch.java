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

/*
 * Video Reference: https://youtu.be/yr-teDH0gR0
 * LeetCode Reference: https://leetcode.com/problems/word-search/
 */
public class _0079_WordSearch {

    public boolean exist(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;
        if (word.isEmpty()) return true;
        if (rows * cols < word.length()) return false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] != word.charAt(0)) {
                    continue;
                }
                if (dfs(board, new boolean[rows][cols], r, c, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, boolean[][] visitedBoard, int row, int col, String word, int wordIdx) {
        if ((row < 0 || row >= board.length) || (col < 0 || col >= board[0].length) || board[row][col] != word.charAt(wordIdx) || visitedBoard[row][col]) {
            return false;
        }
        if (wordIdx == word.length() - 1) {
            return true;
        }

        visitedBoard[row][col] = true;
        if (dfs(board, visitedBoard, row, col - 1, word, wordIdx + 1)
                || dfs(board, visitedBoard, row, col + 1, word, wordIdx + 1)
                || dfs(board, visitedBoard, row - 1, col, word, wordIdx + 1)
                || dfs(board, visitedBoard, row + 1, col, word, wordIdx + 1)) {
            return true;
        }
        visitedBoard[row][col] = false;
        return false;
    }

}
