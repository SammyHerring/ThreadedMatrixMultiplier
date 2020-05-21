package com.matrix.task3;

public class Task3_Thread implements Runnable {
    private int[][] A;
    private int[][] B;
    private int[][] Z;
    private int i;
    private int j;

    public Task3_Thread(int i, int j, int[][] A, int[][] B, int[][] Z) {
        this.A = A; //Matrix A Ref
        this.B = B; //Matrix B Ref
        this.Z = Z; //Matrix Z Ref (Product of AB)
        this.i = i; //Row Identifier
        this.j = j; //Column Identifier
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200); //Wait for 200ms as required by assignment

            //Perform cross-multiplication for given row/column
            for (int k = 0; k < A[0].length; k++) {
                Z[i][j] += (A[i][k] * B[k][j]);
            }

        } catch (Exception e) {
            e.printStackTrace(); //Handle potential errors gracefully
        }

    }

}
