package com.matrix.task2;

public class Task2_Thread extends Thread {
    private int[][] A;
    private int[][] B;
    private int i;
    private int j;
    private volatile int result;

    public Task2_Thread(int i, int j, int[][] A, int[][] B) {
        this.A = A;
        this.B = B;
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
        for (int k = 0; k < A[0].length; k++) {
            result += (A[i][k] * B[k][j]);
        }
    }

    public int getValue() {
        return result;
    }
}
